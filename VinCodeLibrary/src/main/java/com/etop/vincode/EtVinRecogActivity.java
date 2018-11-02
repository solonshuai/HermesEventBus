package com.etop.vincode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.etop.utils.VinUserIdUtil;
import com.etop.vin.VINAPI;

public class EtVinRecogActivity extends Activity {
    private boolean bInitKernal = false;
    private VINAPI vinApi;
    private long startData;
    private String UserID = VinUserIdUtil.getUserId();
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final String imageFilePath = intent.getStringExtra("imageFilePath");

        //初始化识别核心
        if (!bInitKernal) {
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
                }
            }
        }

        try {
            startData = System.currentTimeMillis();
            progress = ProgressDialog.show(EtVinRecogActivity.this, "", "正在识别...");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //调用识别接口
                    int nRet = vinApi.VinRecognizeImageFile(imageFilePath);
                    String strResult = "识别失败";
                    if (nRet == 0) {
                        //获取识别结果
                        strResult = vinApi.VinGetResult();
                    }else {
                        strResult = "识别失败！ErrorCode："+nRet;
                    }
                    final String finalStrResult = strResult;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progress.dismiss();
                            if (vinApi != null) {
                                vinApi.VinKernalUnInit();
                                vinApi = null;
                            }
                            Intent intent = new Intent();
                            intent.putExtra("recogResult", finalStrResult);
                            EtVinRecogActivity.this.setResult(RESULT_OK, intent);
                            EtVinRecogActivity.this.finish();
                        }
                    });

                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progress!=null) {
            progress.dismiss();
            progress=null;
        }
        if (vinApi != null) {
            vinApi.VinKernalUnInit();
            vinApi = null;
        }
    }
}
