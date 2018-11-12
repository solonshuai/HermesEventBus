package com.zxy.mytsfqxproject.http;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;
import java.util.Objects;

public class DownLoadService extends Service {
    private DownloadManager manager;
    private DownloadCompleteReceiver receiver;
    private String url;
    private String DOWNLOADPATH = "/demo/apk/";
    private LocalBroadcastManager localBroadcastManager;

    private void initDownManager() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        receiver = new DownloadCompleteReceiver();
        DownloadManager.Request down = new DownloadManager.Request(Uri.parse(url));
        down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                | DownloadManager.Request.NETWORK_WIFI);
        down.setAllowedOverRoaming(false);
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        down.setMimeType(mimeString);
        down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        down.setVisibleInDownloadsUi(true);
        down.setDestinationInExternalPublicDir(DOWNLOADPATH, "tsf.apk");
        down.setTitle("钛师傅");
        manager.enqueue(down);
        localBroadcastManager.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        url = intent.getStringExtra("downloadurl");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + DOWNLOADPATH + "tsf.apk";
        File file = new File(path);
        if (file.exists()) {
            deleteFileWithPath(path);
        }
        try {
            initDownManager();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "下载失败", Toast.LENGTH_SHORT).show();
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            localBroadcastManager.unregisterReceiver(receiver);
        }
    }

    class DownloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                localBroadcastManager.sendBroadcast(new Intent("close"));
                long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (manager.getUriForDownloadedFile(downId) != null) {
                    installAPK(context, getRealFilePath(context, manager.getUriForDownloadedFile(downId)));
                } else {
                    Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
                }
                DownLoadService.this.stopSelf();
            }
        }

        private void installAPK(Context context, String path) {
            File file = new File(path);
            if (file.exists()) {
                openFile(file, context);
            } else {
                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 重点在这里
     */
    public void openFile(File var0, Context var1) {
        try {
            Intent var2 = new Intent();
            var2.setAction(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                String authority = "com.zycf.chege.FileProvider";
                Uri uriForFile = FileProvider.getUriForFile(var1, authority, var0);
                var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                var2.setDataAndType(uriForFile, var1.getContentResolver().getType(uriForFile));
            } else {
                var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                var2.setDataAndType(Uri.fromFile(var0), getMIMEType(var0));
            }
            var1.startActivity(var2);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(var1, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
        }
    }

    public String getMIMEType(File var0) {
        String var1;
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }

    public void deleteFileWithPath(String filePath) {
        SecurityManager checker = new SecurityManager();
        File f = new File(filePath);
        checker.checkDelete(filePath);
        if (f.isFile()) f.delete();
    }
}
