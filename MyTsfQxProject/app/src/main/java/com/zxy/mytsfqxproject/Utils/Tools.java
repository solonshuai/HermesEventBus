package com.zxy.mytsfqxproject.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.zxy.mytsfqxproject.mvp.entity.SaveGoodMaterialBean;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.yanzhenjie.album.task.LocalImageLoader.calculateInSampleSize;

public class Tools {
    public static RequestBody getRequestBody(HashMap<String, Object> hashMap) {
        StringBuilder data = new StringBuilder();
        if (hashMap != null && hashMap.size() > 0) {
            for (Object o : hashMap.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                Object val = entry.getValue();
                data.append(key).append("=").append(val).append("&");
            }
        }
        String jso = data.substring(0, data.length() - 1);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), jso);
        return requestBody;
    }

    /**
     * Str 转body
     *
     * @param param
     * @return
     */
    public static RequestBody convertToRequestBody(String param) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), param);
        return requestBody;
    }

    public static RequestBody getRequestBodys(HashMap<String, Object> hashMap) {
        String route = new Gson().toJson(hashMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
        return body;
    }

    public static void ArrayToString(SaveGoodMaterialBean saveGoodMaterialBean) {
        try {
            String jsonStr = new Gson().toJson(saveGoodMaterialBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析城市列表
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static File getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(filePath, options);
        if (bm == null) {
            return null;
        }
        int degree = readPictureDegree(filePath);
        bm = rotateBitmap(bm, degree);
        return saveBitmap(bm, filePath, 70);
    }

    /**
     * 读取照片exif信息中的旋转角度
     *
     * @param path 照片路径
     * @return角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param bitmap
     * @param rotate
     * @return
     */
    private static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {
        if (bitmap == null)
            return null;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix mtx = new Matrix();
        mtx.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }

    /**
     * 添加图片到sd卡并规定压缩比例，100默认原图
     */
    public static File saveBitmap(Bitmap bitmap, String savePath, int quality) {
        if (bitmap == null)
            return null;
        try {
            File f = new File(savePath);
            if (f.exists()) f.delete();
            FileOutputStream fos = new FileOutputStream(f);
            f.createNewFile();
            // 把Bitmap对象解析成流
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            fos.flush();
            fos.close();
            bitmap.recycle();
            return f;
        } catch (IOException e) {
            e.printStackTrace();
            bitmap.recycle();
            return null;
        }
    }

    /**
     * 根据地址压缩图片
     *
     * @param pathName
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromFd(String pathName, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        //图片质量的压缩比
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return src;
    }

    public static Bitmap getBitmap(String filePath) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = Math.max(1, (int) Math.ceil(Math.max(
                (double) options.outWidth / 1024f,
                (double) options.outHeight / 1024f)));
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(filePath, options);
        return bitmap;
    }

    /**
     * 获取当前这个月有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthOfDay(int year, int month) {
        int day = 0;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            day = 29;
        } else {
            day = 28;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return day;

        }

        return 0;
    }

    /**
     * 截取字符串
     *
     * @param days
     * @return
     */
    public static String[] indexString(String days) {
        String[] strings = null;
        strings = days.split("-");
        return strings;
    }

    /**
     * int 转 String
     *
     * @param str
     * @return
     */
    public static String intByStr(int str) {
        return String.valueOf(str);
    }

    /**
     * String 转 int
     *
     * @param str
     * @return
     */
    public static int strByInt(String str) throws Exception {
        if (str.contains(".")) {
            double value = Double.parseDouble(str);
            return (int) value;
        }
        return Integer.parseInt(str);
    }

    /**
     * 格式化时间
     *
     * @param times
     * @param type  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getTime(int times, String type) {
        String dateTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        long timeLong = Long.valueOf(times);
        dateTime = simpleDateFormat.format(new Date(timeLong * 1000L));
        return dateTime;
//        if (times == 0) {
//            return "";
//        }
//        String res;
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
//        long lt = (long) times;
//        Date date = new Date(lt);
//        res = simpleDateFormat.format(date);
//        return res;
    }

    public static boolean isJson(String content) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 日志打印
     *
     * @param val
     */
    public static void logger(String val) {
        Log.e("打印", val + ".");
    }

    /**
     * 获取手机联系人
     */
    public static List<HashMap<String, String>> getAllContactInfo(Context context) {
        SystemClock.sleep(3000);
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        // 1.获取内容解析者
        ContentResolver resolver = context.getContentResolver();
        // 2.获取内容提供者的地址:com.android.contacts
        // raw_contacts表的地址 :raw_contacts
        // view_data表的地址 : data
        // 3.生成查询地址
        Uri raw_uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri date_uri = Uri.parse("content://com.android.contacts/data");
        // 4.查询操作,先查询raw_contacts,查询contact_id
        // projection : 查询的字段
        Cursor cursor = resolver.query(raw_uri, new String[]{"contact_id"},
                null, null, null);
        // 5.解析cursor
        while (cursor.moveToNext()) {
            // 6.获取查询的数据
            String contact_id = cursor.getString(0);
            // cursor.getString(cursor.getColumnIndex("contact_id"));//getColumnIndex
            // : 查询字段在cursor中索引值,一般都是用在查询字段比较多的时候
            // 判断contact_id是否为空
            if (!TextUtils.isEmpty(contact_id)) {//null   ""
                // 7.根据contact_id查询view_data表中的数据
                // selection : 查询条件
                // selectionArgs :查询条件的参数
                // sortOrder : 排序
                // 空指针: 1.null.方法 2.参数为null
                Cursor c = resolver.query(date_uri, new String[]{"data1",
                                "mimetype"}, "raw_contact_id=?",
                        new String[]{contact_id}, null);
                HashMap<String, String> map = new HashMap<String, String>();
                // 8.解析c
                assert c != null;
                while (c.moveToNext()) {
                    // 9.获取数据
                    String data1 = c.getString(0);
                    String mimetype = c.getString(1);
                    // 10.根据类型去判断获取的data1数据并保存
                    if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                        // 电话
                        map.put("phone", data1);
                    } else if (mimetype.equals("vnd.android.cursor.item/name")) {
                        // 姓名
                        map.put("name", data1);
                    }
                }
                // 11.添加到集合中数据
                list.add(map);
                // 12.关闭cursor
                c.close();
            }
        }
        // 12.关闭cursor
        cursor.close();
        return list;
    }

}
