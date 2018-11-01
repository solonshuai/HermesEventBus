package com.etop.vincode;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etop.utils.VinFullScreenWidthUtil;
import com.etop.utils.VinInfoConfig;
import com.etop.utils.VinUserIdUtil;
import com.etop.view.VinViewfinderView;
import com.etop.vin.VINAPI;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class EtVinScanActivity extends Activity implements SurfaceHolder.Callback, Camera.PreviewCallback {
    private static final String PATH = Environment.getExternalStorageDirectory() + "/alpha/VinCode/";
    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private RelativeLayout mainRl;
    private SurfaceHolder mSurfaceHolder;
    private ImageButton ibBack;
    private ImageButton ibFlash;
    //private ImageButton ibTakePic;
    private TextView mTvResult;
    private TextView mTvRemind;
    private ImageView mIvShowBmp;
    private VinViewfinderView myView;
    private VINAPI vinApi;
    //private Bitmap bitmap;
    private int preWidth = 0;
    private int preHeight = 0;
    private boolean isROI = false;
    private int screenWidth;
    private int screenHeight;
    private Vibrator mVibrator;
    private byte[] tackData;
    private int[] m_ROI = {0, 0, 0, 0};
    private boolean bInitKernal = false;
    private double screenInches;
    private boolean bP = false;
    private int widgetW;
    private int widgetH;
    private int orientation = 0;
    private int rotationPic = 0;
    private OrientationEventListener mScreenOrientationEventListener;
    private TextView mTvSlogan;
    private int rotationWidget = 0;
    private String strCaptureFilePath;
    private ImageView mScanHorizontalLineImageView;
    private ImageView mScanVerticalLineImageView;
    private TranslateAnimation verticalAnimation;
    private TranslateAnimation horizontalAnimation;
    private Boolean isAlter = true;
    private int sign = -1;
    private Bitmap bitmap;
    private ImageButton ibChange;
    private Boolean isLock = false;
    private String UserID = VinUserIdUtil.getUserId();
    private VinInfoConfig vincodeConfig;
    private Boolean isSaveImage;
    private String saveImagePATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Configuration cf = this.getResources().getConfiguration();
        int noriention = cf.orientation;
        if (noriention == cf.ORIENTATION_LANDSCAPE) {
            initKernal();//初始化识别核心
        }

        vincodeConfig = (VinInfoConfig) getIntent().getExtras().get(VinUserIdUtil.INTENT_VIN_CONFIG);
        if (vincodeConfig == null) {
            vincodeConfig = new VinInfoConfig();
        }
        isSaveImage = vincodeConfig.getIsSaveImage();
        saveImagePATH = Environment.getExternalStorageDirectory() + vincodeConfig.getStrSaveImagePath();
        if (saveImagePATH != null) {
            File file = new File(saveImagePATH);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
        } else {
            Toast.makeText(this, "路径不正确", Toast.LENGTH_SHORT).show();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.etop_vin_activity_scan);

        findView();

        mScreenOrientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int i) {
                String mScreenExifOrientation = "无法监听方向";
                if (75 <= i && i < 105) {
                    mScreenExifOrientation = "反横向";
                    if (isLock) {
                        orientation = 2;
                        rotationPic = 180;
                        rotationWidget = 180;
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                        layoutParams.bottomMargin = (int) (widgetH * 0.08);
                        mTvRemind.setLayoutParams(layoutParams);
                        layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                        layoutParams.bottomMargin = (int) (widgetH * 0.2);
                        mTvResult.setLayoutParams(layoutParams);
                        mTvRemind.setRotation(180);
                        mTvResult.setRotation(180);
                        ibBack.setRotation(90);
                        ibFlash.setRotation(90);
                        ibChange.setRotation(180);
                    }
                } else if (165 <= i && i < 195) {
                    mScreenExifOrientation = "反竖向";
                    if (isLock) {
                        orientation = 3;
                        rotationPic = 270;
                        rotationWidget = 90;
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                        layoutParams.rightMargin = (int) (widgetH * 0.08);
                        mTvRemind.setLayoutParams(layoutParams);
                        layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                        layoutParams.rightMargin = (int) (widgetH * 0.2);
                        mTvResult.setLayoutParams(layoutParams);
                        mTvRemind.setRotation(90);
                        mTvResult.setRotation(90);
                        ibBack.setRotation(0);
                        ibFlash.setRotation(0);
                        ibChange.setRotation(90);
                    }
                } else if (235 <= i && i < 285) {
                    mScreenExifOrientation = "横向";
                    if (isLock) {
                        orientation = 0;
                        rotationPic = 0;
                        rotationWidget = 0;
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                        layoutParams.topMargin = (int) (widgetH * 0.08);
                        mTvRemind.setLayoutParams(layoutParams);
                        layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                        layoutParams.topMargin = (int) (widgetH * 0.2);
                        mTvResult.setLayoutParams(layoutParams);
                        mTvRemind.setRotation(0);
                        mTvResult.setRotation(0);
                        ibBack.setRotation(90);
                        ibFlash.setRotation(90);
                        ibChange.setRotation(0);
                    }
                } else if ((i >= 345 && i <= 360) || (i >= 0 && i < 15)) {
                    mScreenExifOrientation = "竖向";
                    if (isLock) {
                        orientation = 1;
                        rotationPic = 90;
                        rotationWidget = 270;
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                        layoutParams.leftMargin = (int) (widgetH * 0.08);
                        mTvRemind.setLayoutParams(layoutParams);
                        layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                        layoutParams.leftMargin = (int) (widgetH * 0.2);
                        mTvResult.setLayoutParams(layoutParams);
                        mTvRemind.setRotation(270);
                        mTvResult.setRotation(270);
                        ibBack.setRotation(0);
                        ibFlash.setRotation(0);
                        ibChange.setRotation(270);
                    }
                }

                if (sign != orientation) isAlter = true;
                if (isAlter) {
                    if (orientation == 1 || orientation == 3) {
                        VerAnimation();
                    } else {
                        horAnimation();
                    }
                }
                isAlter = false;
                sign = orientation;
                //Log.e("mScreenExifOrientation",mScreenExifOrientation);
                mScreenOrientationEventListener.enable();
            }
        };

    }

    /**
     * 横屏动画
     */
    private void horAnimation() {//水平移动
        if (verticalAnimation != null) {//如果已开启垂直动画，则，关闭，隐藏控件
            verticalAnimation.cancel();
            mScanVerticalLineImageView.clearAnimation();
            mScanVerticalLineImageView.invalidate();
            mScanVerticalLineImageView.setVisibility(View.GONE);
        }
        horizontalAnimation = new TranslateAnimation( // y轴移动距离
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, -0.14f, Animation.RELATIVE_TO_PARENT, 0.14f);
        horizontalAnimation.setDuration(1000); // 动画持续时间
        horizontalAnimation.setRepeatMode(Animation.REVERSE);//反转位移
        horizontalAnimation.setRepeatCount(Animation.INFINITE); // 无限循环
        //播放动画
        mScanHorizontalLineImageView.startAnimation(horizontalAnimation);
        mScanHorizontalLineImageView.setVisibility(View.VISIBLE);//显示动画
    }

    private void VerAnimation() {//垂直动画
        if (horizontalAnimation != null) {//如果已开启水平动画，则，关闭，隐藏控件
            horizontalAnimation.cancel();
            mScanHorizontalLineImageView.clearAnimation();
            mScanHorizontalLineImageView.invalidate();
            mScanHorizontalLineImageView.setVisibility(View.GONE);
        }
        verticalAnimation = new TranslateAnimation(// x轴移动距离
                Animation.RELATIVE_TO_PARENT, -0.05f, Animation.RELATIVE_TO_PARENT, 0.05f,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        verticalAnimation.setDuration(1000); // 动画持续时间
        verticalAnimation.setRepeatMode(Animation.REVERSE);//反转位移
        verticalAnimation.setRepeatCount(Animation.INFINITE); // 无限循环
        // 播放动画
        mScanVerticalLineImageView.startAnimation(verticalAnimation);
        mScanVerticalLineImageView.setVisibility(View.VISIBLE);//显示动画
    }

    private void findView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.etop_sv);
        mainRl = (RelativeLayout) findViewById(R.id.etop_rl_main);
        ibBack = (ImageButton) findViewById(R.id.etop_ib_back);
        ibFlash = (ImageButton) findViewById(R.id.etop_ib_flash);
        //ibTakePic = (ImageButton) findViewById(R.id.etop_ib_tackpic);
        ibChange = (ImageButton) findViewById(R.id.etop_ib_change);
        mTvResult = (TextView) findViewById(R.id.etop_tv_result);
        mTvRemind = (TextView) findViewById(R.id.etop_tv_remind);
        mTvSlogan = (TextView) findViewById(R.id.etop_tv_slogan);
        mIvShowBmp = (ImageView) findViewById(R.id.etop_iv_showbmp);
        mScanHorizontalLineImageView = (ImageView) findViewById(R.id.scan_line);
        mScanVerticalLineImageView = (ImageView) findViewById(R.id.scanVerticalLineImageView);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
        screenHeight = metric.heightPixels;
        float densityx = metric.xdpi;
        float densityy = metric.ydpi;
        double wx = screenWidth / densityx;
        double hy = screenHeight / densityy;
        screenInches = Math.sqrt(Math.pow(wx, 2) + Math.pow(hy, 2));

        if (screenInches > 7) {//判断是否是7英寸以上的平板
            //如果是7英寸平板，则以6英寸屏幕为标准缩放
            widgetW = (int) (screenWidth * 6 / screenInches);
            widgetH = (int) (screenHeight * 6 / screenInches);
        } else {
            widgetW = screenWidth;
            widgetH = screenHeight;
        }

        mIvShowBmp.setVisibility(View.GONE);

        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(EtVinScanActivity.this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mScanHorizontalLineImageView.getLayoutParams();
        lp.width = screenWidth - screenWidth / 3;
        mScanHorizontalLineImageView.setLayoutParams(lp);

        ibBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ibFlash.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                    String mess = getResources().getString(R.string.toast_flash);
                    Toast.makeText(EtVinScanActivity.this, mess, Toast.LENGTH_LONG).show();
                } else {
                    if (mCamera != null) {
                        Camera.Parameters parameters = mCamera.getParameters();
                        String flashMode = parameters.getFlashMode();
                        if (flashMode.equals(Camera.Parameters.FLASH_MODE_TORCH)) {
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            parameters.setExposureCompensation(0);
                        } else {
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            parameters.setExposureCompensation(-1);
                        }
                        try {
                            mCamera.setParameters(parameters);
                        } catch (Exception e) {
                            String mess = getResources().getString(R.string.toast_flash);
                            Toast.makeText(EtVinScanActivity.this, mess, Toast.LENGTH_LONG).show();
                        }
                        mCamera.startPreview();
                    }
                }
            }
        });
        ibChange.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLock) {
                    isLock = false;
                    ibChange.setBackgroundResource(R.drawable.etop_vin_lock_off);
                } else {
                    isLock = true;
                    ibChange.setBackgroundResource(R.drawable.etop_vin_lock_on);
                }
                mScreenOrientationEventListener.enable();
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mCamera == null) {
            try {
                mCamera = Camera.open();
                Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
                mCamera.setParameters(mParameters);
            } catch (Exception e) {
                e.printStackTrace();
                if (mCamera != null) {
                    mCamera.release();
                    mCamera = null;
                }
                String mess = getResources().getString(R.string.toast_camera);
                Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        initCamera(holder);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();//相机用完，资源释放
    }

    private void initKernal() {
        if (vinApi == null) {
            vinApi = new VINAPI();
            String cacheDir = (this.getExternalCacheDir()).getPath();
            String userIdPath = cacheDir + "/" + UserID + ".lic";
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            int nRet = vinApi.VinKernalInit("", userIdPath, UserID, 0x01, 0x02, telephonyManager, this);
            if (nRet != 0) {
                Toast.makeText(getApplicationContext(), "激活失败", Toast.LENGTH_SHORT).show();
                bInitKernal = false;
            } else {
                bInitKernal = true;

                String endTime = vinApi.VinGetEndTime();//获取一个授权结束的日期（2018-3-31）
                String[] time = endTime.split("-");
                int year1 = Integer.parseInt(time[0]);
                int month1 = Integer.parseInt(time[1]);
                int day1 = Integer.parseInt(time[2]);
                //Toast.makeText(getApplicationContext(), endTime, Toast.LENGTH_SHORT).show();

                Time timeSystem = new Time();
                timeSystem.setToNow(); // 取得系统时间。
                int year = timeSystem.year;//年
                int month = timeSystem.month + 1;//月
                int day = timeSystem.monthDay;//日

                if (year1 == year && month1 == month) {//说明年月相同
                    int endDay = day1 - day + 1;
                    if (endDay <= 7 && endDay >= 0) {
                        Toast.makeText(EtVinScanActivity.this, "授权将于" + endDay + "天后到期", Toast.LENGTH_SHORT).show();
                    }
                    //说明年份相同月份不同，且授权截止日期在下月7号之前
                } else if (year1 == year && month1 - month == 1 && day1 < 7) {
                    int days = getDays(year, month);//返回当月天数
                    int endDay = days + day1 - day + 1;
                    if (endDay <= 7 && endDay >= 0) {
                        Toast.makeText(EtVinScanActivity.this, "授权将于" + endDay + "天后到期", Toast.LENGTH_SHORT).show();
                    }
                    //跨年，授权截止日期在1月份，并且在下月7号之前
                } else if (year1 - year == 1 && month1 == 1 && day1 < 7) {
                    int endDay = 32 + day1 - day;
                    if (endDay <= 7 && endDay >= 0) {
                        Toast.makeText(EtVinScanActivity.this, "授权将于" + endDay + "天后到期", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }


    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTvRemind.setVisibility(View.GONE);
            mTvResult.setVisibility(View.GONE);
            mIvShowBmp.setVisibility(View.GONE);
            isROI = false;
            bP = false;
            mScreenOrientationEventListener.enable();
            if (mCamera != null) mCamera.startPreview();
        }
        return true;
    }

    @TargetApi(14)
    private void initCamera(SurfaceHolder holder) {
        Camera.Parameters parameters = mCamera.getParameters();
        List<Size> list = parameters.getSupportedPreviewSizes();
        Size previewSize = getAdapterPreviewSize(list, VinFullScreenWidthUtil.getWidthDpi(this), screenHeight);
        if (previewSize != null) {
            preWidth = previewSize.width;
            preHeight = previewSize.height;
        } else {
            previewSize = getAdapterPreviewSize(list, 16, 9);
            if (previewSize != null) {
                preWidth = previewSize.width;
                preHeight = previewSize.height;
            }else {
                Toast.makeText(this, "无法启用相机！ErrorCode:100", Toast.LENGTH_LONG).show();
                return;
            }
        }

        if (!isROI) {
            isROI = true;
            int viewW = widgetW;
            int viewH = widgetH;
            if (screenInches > 7) {//判断是否是7英寸以上的平板
                //如果是7英寸平板，则以6英寸屏幕为标准缩放
                viewW = (int) (preWidth * 6 / screenInches);
                viewH = (int) (preHeight * 6 / screenInches);
            }

            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mIvShowBmp.getLayoutParams();
            int showbitmap_w = viewW - (viewW / 3);
            int showbitmap_h = (viewW - viewW / 3) / 4;
            lp.width = showbitmap_w;
            lp.height = showbitmap_h;
            lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            mIvShowBmp.setLayoutParams(lp);
        }
        parameters.setPictureFormat(PixelFormat.JPEG);
        parameters.setJpegQuality(100);
        parameters.setPreviewSize(preWidth, preHeight);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        parameters.setAntibanding(Camera.Parameters.ANTIBANDING_50HZ);

        mCamera.setPreviewCallback(this);
        if (parameters.isZoomSupported()) {
            parameters.setZoom(2);
        }
        myView = new VinViewfinderView(EtVinScanActivity.this, screenWidth, screenHeight);
        mainRl.addView(myView);
        mCamera.setParameters(parameters);
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();

    }

    public String savePicture(Bitmap bitmap, String tag) {
        strCaptureFilePath = PATH + tag + "_VIN_" + pictureName() + ".jpg";
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(strCaptureFilePath);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "图像存储失败", Toast.LENGTH_SHORT).show();
        }
        return strCaptureFilePath;
    }

    public String pictureName() {
        String str = "";
        Time t = new Time();
        t.setToNow();
        int year = t.year;
        int month = t.month + 1;
        int date = t.monthDay;
        int hour = t.hour; // 0-23
        int minute = t.minute;
        int second = t.second;
        if (month < 10)
            str = String.valueOf(year) + "0" + String.valueOf(month);
        else {
            str = String.valueOf(year) + String.valueOf(month);
        }
        if (date < 10)
            str = str + "0" + String.valueOf(date + "_");
        else {
            str = str + String.valueOf(date + "_");
        }
        if (hour < 10)
            str = str + "0" + String.valueOf(hour);
        else {
            str = str + String.valueOf(hour);
        }
        if (minute < 10)
            str = str + "0" + String.valueOf(minute);
        else {
            str = str + String.valueOf(minute);
        }
        if (second < 10)
            str = str + "0" + String.valueOf(second);
        else {
            str = str + String.valueOf(second);
        }
        return str;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

        myView.setViewRotation(orientation);
        mTvSlogan.setRotation(rotationWidget);

        int t, b, l, r;
        if (orientation == 0 || orientation == 2) {//横向
            l = preWidth / 6;
            r = preWidth - l;
            int ntmp = (preWidth - 2 * l) / 4;
            t = (preHeight - ntmp) / 2;
            b = t + ntmp;
            int borders[] = {l, t, r, b};
            m_ROI[0] = l;
            m_ROI[1] = t;
            m_ROI[2] = r;
            m_ROI[3] = b;
            vinApi.VinSetROI(borders, preWidth, preHeight);
        } else {//纵向
            l = 0;
            r = preHeight;
            t = preWidth / 2 - 100;
            b = preWidth / 2 + 100;
            int borders[] = {l, t, r, b};
            m_ROI[0] = l;
            m_ROI[1] = t;
            m_ROI[2] = r;
            m_ROI[3] = b;
            vinApi.VinSetROI(borders, preHeight, preWidth);
        }

        tackData = data;
        int buffl = 30;
        char recogval[] = new char[buffl];
        int pLineWarp[] = new int[32000];
        if (mTvRemind.getVisibility() == View.GONE && !bP) {
            int rot = vinApi.VinRecognizeNV21Android(data, preWidth, preHeight, recogval, buffl, pLineWarp, orientation);
            Log.e("rot", rot + "");
            if (rot == 0) {
                mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
                mVibrator.vibrate(100);
                camera.stopPreview();
                bP = true;
                mScreenOrientationEventListener.disable();
                String recogResult = vinApi.VinGetResult();
                mTvRemind.setVisibility(View.VISIBLE);

                if (isSaveImage) {
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    opts.inInputShareable = true;
                    opts.inPurgeable = true;
                    if (orientation == 0 || orientation == 2) {
                        int[] datas = convertYUV420_NV21toARGB8888(tackData, preWidth, preHeight);
                        bitmap = Bitmap.createBitmap(datas, preWidth, preHeight, Bitmap.Config.RGB_565);
                        bitmap = Bitmap.createBitmap(bitmap, m_ROI[0], m_ROI[1], m_ROI[2] - m_ROI[0], m_ROI[3] - m_ROI[1]);
                        //bitmap = Bitmap.createBitmap(bitmap, m_ROI[0], m_ROI[1], m_ROI[2] - m_ROI[0], m_ROI[3] - m_ROI[1]);
                        if (orientation == 2) {
                            int bmpWidth = bitmap.getWidth();
                            int bmpHeight = bitmap.getHeight();
                            Matrix matrix = new Matrix();
                            matrix.setRotate(rotationPic);  //旋转-90度
                            bitmap = bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight, matrix, true);
                        }
                    } else {
                        int[] datas = convertYUV420_NV21toARGB8888(tackData, preWidth, preHeight);
                        bitmap = Bitmap.createBitmap(datas, preWidth, preHeight, Bitmap.Config.RGB_565);
                        bitmap = Bitmap.createBitmap(bitmap, m_ROI[1], m_ROI[0], m_ROI[3] - m_ROI[1], m_ROI[2] - m_ROI[0]);
                        int bmpWidth = bitmap.getWidth();
                        int bmpHeight = bitmap.getHeight();
                        Matrix matrix = new Matrix();
                        matrix.setRotate(rotationPic);  //旋转-90度
                        bitmap = bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight, matrix, true);
                    }
                    Bitmap bitmap = Bitmap.createBitmap(pLineWarp, 400, 80, Bitmap.Config.RGB_565);
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        savePicture(bitmap, "V");
                    } else {
                        Toast.makeText(this, "SD卡异常", Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("recogResult", recogResult);
                EtVinScanActivity.this.setResult(RESULT_OK, intent);
                EtVinScanActivity.this.finish();
            }
        }
    }

    public static int[] convertYUV420_NV21toARGB8888(byte[] data, int width, int height) {
        int size = width * height;
        int offset = size;
        int[] pixels = new int[size];
        int u, v, y1, y2, y3, y4;
        for (int i = 0, k = 0; i < size; i += 2, k += 2) {
            y1 = data[i] & 0xff;
            y2 = data[i + 1] & 0xff;
            y3 = data[width + i] & 0xff;
            y4 = data[width + i + 1] & 0xff;

            u = data[offset + k] & 0xff;
            v = data[offset + k + 1] & 0xff;
            u = u - 128;
            v = v - 128;

            pixels[i] = convertYUVtoARGB(y1, u, v);
            pixels[i + 1] = convertYUVtoARGB(y2, u, v);
            pixels[width + i] = convertYUVtoARGB(y3, u, v);
            pixels[width + i + 1] = convertYUVtoARGB(y4, u, v);

            if (i != 0 && (i + 2) % width == 0)
                i += width;
        }

        return pixels;
    }

    private static int convertYUVtoARGB(int y, int u, int v) {
        int r, g, b;

        r = y + (int) 1.402f * u;
        g = y - (int) (0.344f * v + 0.714f * u);
        b = y + (int) 1.772f * v;
        r = r > 255 ? 255 : r < 0 ? 0 : r;
        g = g > 255 ? 255 : g < 0 ? 0 : g;
        b = b > 255 ? 255 : b < 0 ? 0 : b;
        return 0xff000000 | (r << 16) | (g << 8) | b;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initKernal();
        bP = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseCamera();//释放相机
    }

    @Override
    protected void onDestroy() {
        if (vinApi != null) {
            vinApi.VinKernalUnInit();
            vinApi = null;
        }
        if (mScreenOrientationEventListener!=null) {
            mScreenOrientationEventListener.disable();
            mScreenOrientationEventListener = null;
        }
        super.onDestroy();

    }

    /**************释放相机***************/
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    //返回当月天数
    int getDays(int year, int month) {
        int days;
        int FebDay = 28;
        if (isLeap(year))
            FebDay = 29;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;
    }

    private boolean isLeap(int year) {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
            return true;
        else
            return false;
    }

    /********************适配预览分辨率大小**********************/
    private Size getAdapterPreviewSize(List<Size> list, int screenWidth, int screenHeight) {
        double ASPECT_TOLERANCE = 0.026;//允许的比例误差
        double targetRatio = (double) screenHeight / screenWidth;
        if (targetRatio > 1) {
            targetRatio = (double) screenWidth / screenHeight;
        }
        if (targetRatio < 0.5) targetRatio = 0.5;
        Size optimalSize = null;
        for (Size size : list) {
            double ratio = (double) size.height / size.width;
            if (ratio > 1) {
                ratio = (double) size.width / size.height;
            }
            if (size.height < 700) continue;
            if (size.height > 1200) continue;
            if (ratio == targetRatio) {
                if (optimalSize != null) {
                    if (optimalSize.width > size.width || optimalSize.height > size.height) {
                        optimalSize = size;
                    }
                } else {
                    optimalSize = size;
                }
            }
        }

        if (optimalSize==null) {
            for (Size size : list) {
                double ratio = (double) size.height / size.width;
                if (ratio > 1) {
                    ratio = (double) size.width / size.height;
                }
                if (size.height < 600) continue;
                if (Math.abs(ratio - targetRatio) < ASPECT_TOLERANCE) {
                    if (optimalSize != null) {
                        if (optimalSize.width > size.width || optimalSize.height > size.height) {
                            optimalSize = size;
                        }
                    } else {
                        optimalSize = size;
                    }
                }
            }
        }
        return optimalSize;
    }
}
