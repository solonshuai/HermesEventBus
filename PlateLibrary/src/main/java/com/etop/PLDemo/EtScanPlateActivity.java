package com.etop.PLDemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.etop.plate.PlateAPI;
import com.etop.utils.PlateFullScreenHeightUtil;
import com.etop.utils.PlateInfoConfig;
import com.etop.utils.PlateStreamEmpowerFileUtil;
import com.etop.utils.PlateUserIdUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EtScanPlateActivity extends Activity implements SurfaceHolder.Callback, Camera.PreviewCallback {

    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private RelativeLayout mainRl;
    private SurfaceHolder surfaceHolder;
    private PlateAPI plApi = null;
    private Bitmap bitmap;
    private int preWidth = 0;
    private int preHeight = 0;
    private int screenWidth;
    private int screenHeight;
    private Vibrator mVibrator;
    private PLViewfinderView myView;
    private long recogTime;
    private boolean isFatty = false;
    private boolean bInitKernal = false;
    private int[] m_ROI = {0, 0, 0, 0};
    private boolean isROI = false;
    private ImageButton ibnBack;
    private ImageButton ibnFlash;
    private boolean baddView = false;
    private SeekBar mSeekBar;
    private TextView mTvRoll;
    private String UserID = PlateUserIdUtil.getUserId();
    private PlateInfoConfig plateInfoConfig;
    private Boolean isSaveImage;
    private String saveImagePATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        // // 屏幕常亮
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_scan_plate);

        plateInfoConfig = (PlateInfoConfig) getIntent().getExtras().get(PlateUserIdUtil.INTENT_PLATE_CONFIG);
        if (plateInfoConfig == null) {
            plateInfoConfig = new PlateInfoConfig();
        }
        isSaveImage = plateInfoConfig.getIsSaveImage();
        saveImagePATH = Environment.getExternalStorageDirectory() + plateInfoConfig.getStrSaveImagePath();
        if (saveImagePATH != null) {
            File file = new File(saveImagePATH);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
        } else {
            Toast.makeText(this, "路径不正确", Toast.LENGTH_SHORT).show();
        }

        findView();
    }

    private void findView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.etop_sv_main);
        mainRl = (RelativeLayout) findViewById(R.id.etop_rl_main);
        ibnBack = (ImageButton) findViewById(R.id.etop_ibn_back);
        ibnFlash = (ImageButton) findViewById(R.id.etop_ibn_flash);
        mSeekBar = (SeekBar) findViewById(R.id.etop_seekbar);
        mTvRoll = (TextView) findViewById(R.id.etop_tv_roll);

        mSeekBar.setOnSeekBarChangeListener(onZoomChangeListener);

        try {
            PlateStreamEmpowerFileUtil.copyDataBase(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
        Configuration cf = this.getResources().getConfiguration(); //获取设置的配置信息
        int noriention = cf.orientation;
        if (noriention == cf.ORIENTATION_PORTRAIT)
            initOCRKernal();//初始化识别核心

        surfaceHolder = mSurfaceView.getHolder();
        surfaceHolder.addCallback(EtScanPlateActivity.this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels; // 屏幕宽度（像素）
        screenHeight = metric.heightPixels; // 屏幕高度（像素）
        if (screenWidth * 3 == screenHeight * 4) {
            isFatty = true;
        }
        mOnClick();

        mSurfaceView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mCamera.cancelAutoFocus();
                Camera.Parameters params = mCamera.getParameters();
                params.setFocusMode(Camera.Parameters.FLASH_MODE_AUTO);
                mCamera.setParameters(params);
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            Camera.Parameters params = mCamera.getParameters();
                            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                            mCamera.setParameters(params);
                        }
                    }
                });
            }
        });
    }

    private void mOnClick() {
        ibnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ibnFlash.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                    String mess = "当前设备不支持闪光灯";
                    Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
                } else {
                    if (mCamera != null) {
                        Camera.Parameters parameters = mCamera.getParameters();
                        String flashMode = parameters.getFlashMode();
                        if (flashMode.equals(Camera.Parameters.FLASH_MODE_TORCH)) {
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            parameters.setExposureCompensation(0);
                        } else {
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);// 闪光灯常亮
                            parameters.setExposureCompensation(-1);
                        }
                        try {
                            mCamera.setParameters(parameters);
                        } catch (Exception e) {
                            String mess = "当前设备不支持闪光灯";
                            Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
                        }
                        mCamera.startPreview();
                    }
                }
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initOCRKernal();//如果初始化核心失败，重新初始化
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

        releaseCamera();//相机资源释放
        //卸载识别核心
        if (plApi != null) {
            plApi.ETUnInitPlateKernal();
            plApi = null;
        }
    }

    /********************初始化识别核心**********************/
    private void initOCRKernal() {
        if (plApi == null) {
            plApi = new PlateAPI();
            String cacheDir = (this.getExternalCacheDir()).getPath();
            String userIdPath = cacheDir + "/" + UserID + ".lic";
//            String path = this.getFileStreamPath("UserID" + ".lic").getAbsolutePath();
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            int nRet = plApi.ETInitPlateKernal("", userIdPath, UserID, 0x06, 0x02, telephonyManager, this);
            if (nRet != 0) {
                Toast.makeText(getApplicationContext(), "激活失败,错误状态码：" + nRet, Toast.LENGTH_SHORT).show();
                System.out.print("nRet=" + nRet);
                bInitKernal = false;
            } else {
                System.out.print("nRet=" + nRet);
                bInitKernal = true;

                String endTime = plApi.GetEndTime();//获取一个授权结束的日期（2018-3-31）
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
                        Toast.makeText(EtScanPlateActivity.this, "授权将于" + endDay + "天后到期", Toast.LENGTH_SHORT).show();
                    }
                    //说明年份相同月份不同，且授权截止日期在下月7号之前
                } else if (year1 == year && month1 - month == 1 && day1 < 7) {
                    int days = getDays(year, month);//返回当月天数
                    int endDay = days + day1 - day + 1;
                    if (endDay <= 7 && endDay >= 0) {
                        Toast.makeText(EtScanPlateActivity.this, "授权将于" + endDay + "天后到期", Toast.LENGTH_SHORT).show();
                    }
                    //跨年，授权截止日期在1月份，并且在下月7号之前
                } else if (year1 - year == 1 && month1 == 1 && day1 < 7) {
                    int endDay = 32 + day1 - day;
                    if (endDay <= 7 && endDay >= 0) {
                        Toast.makeText(EtScanPlateActivity.this, "授权将于" + endDay + "天后到期", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @TargetApi(14)
    private void initCamera(SurfaceHolder holder) {
        Camera.Parameters parameters = mCamera.getParameters();
        List<Size> list = parameters.getSupportedPreviewSizes();
        Size previewSize = getAdapterPreviewSize(list, PlateFullScreenHeightUtil.getHeightDpi(this), screenWidth);
        if (previewSize != null) {
            preWidth = previewSize.width;
            preHeight = previewSize.height;
        } else {//全屏适配失败，则按16:9去适配
            previewSize = getAdapterPreviewSize(list, 16, 9);
            if (previewSize != null) {
                preWidth = previewSize.width;
                preHeight = previewSize.height;
            }
        }
        if (!isROI && plApi != null) {
            int t;
            int b;
            int l;
            int r;
            l = screenHeight / 5;
            r = screenHeight * 3 / 5;
            t = 4;
            b = screenWidth - 4;
            double proportion = (double) screenHeight / (double) preWidth;
            l = (int) (l / proportion);
            t = 0;
            r = (int) (r / proportion);
            b = preHeight;
            int borders[] = {l, t, r, b};
            m_ROI[0] = l;
            m_ROI[1] = t;
            m_ROI[2] = r;
            m_ROI[3] = b;
            plApi.ETSetPlateROI(borders, preWidth, preHeight);
            isROI = true;
        }
        if (!baddView) {
            if (isFatty)
                myView = new PLViewfinderView(this, screenWidth, screenHeight, isFatty);
            else
                myView = new PLViewfinderView(this, screenWidth, screenHeight);
            mainRl.addView(myView);
            baddView = true;
        }

        parameters.setPreviewSize(preWidth, preHeight);
        Log.e("preWidth:" + preWidth, "preHeight:" + preHeight);
        if (parameters.getSupportedFocusModes().contains(
                parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }

        mCamera.setPreviewCallback(this);
        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
    }

    private SeekBar.OnSeekBarChangeListener onZoomChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Camera.Parameters parameters = mCamera.getParameters();
            List<Integer> zoom = parameters.getZoomRatios();
            int maxZoom = zoom.size() - 1;//可以设置的最大放大倍数
            int pro = progress / 10;
            int multiple = progress * maxZoom / 100;
            parameters.setZoom(multiple);
            mSeekBar.setProgress(pro * 10);
            mTvRoll.setText(pro + ".0x");
            mCamera.setParameters(parameters);
        }
    };

    public String pictureName() {
        String str = "";
        Time t = new Time();
        t.setToNow(); // 取得系统时间。
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

    private boolean isRecog = false;

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        int buffl = 256;
        char recogval[] = new char[buffl];
        Long timeStart = System.currentTimeMillis();
        if (plApi != null) {
            int pLineWarp[] = new int[800 * 45];
            int nv21Width = parameters.getPreviewSize().width;
            int nv21Height = parameters.getPreviewSize().height;
            int r = plApi.RecognizePlateNV21(data, 1, nv21Width, nv21Height, recogval, buffl, pLineWarp);
            Long timeEnd = System.currentTimeMillis();
            if (r == 0 && !isRecog) {
                isRecog = true;
                // 震动
                recogTime = (timeEnd - timeStart);
                String plateNo = plApi.GetRecogResult(0);
                String plateColor = plApi.GetRecogResult(1);
                mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
                mVibrator.vibrate(50);
                if (isSaveImage) {
                    String strFilePath = saveImagePATH + "Plate_" + pictureName() + ".jpg";
                    String strFileRROIPath = saveImagePATH + "Plate_ROI_" + pictureName() + ".jpg";
                    plApi.SavePlateImg(strFilePath, 0);
                    plApi.SavePlateImg(strFileRROIPath, 1);
                }
                ArrayList<String> listResult = new ArrayList<>();
                listResult.add(plateNo);
                listResult.add(plateColor);
                Intent intent = new Intent();
                intent.putStringArrayListExtra("listResult", listResult);
                EtScanPlateActivity.this.setResult(RESULT_OK, intent);
                EtScanPlateActivity.this.finish();

            }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.etop_plate_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }

        releaseCamera();//释放相机资源

        /************释放识别核心************/
        if (plApi != null) {
            plApi.ETUnInitPlateKernal();
            plApi = null;
        }
    }

    /************相机用完资源释放*************/
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @TargetApi(14)
    private void NewApis(Camera.Parameters parameters) {
        if (Build.VERSION.SDK_INT >= 14) {
            parameters.setFocusMode(parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
        }
    }

    //适配相机预览分辨率
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

        if (optimalSize == null) {
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


