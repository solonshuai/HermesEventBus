package com.etop.vin;

import android.content.Context;
import android.telephony.TelephonyManager;

public class VINAPI {
	static {
		System.loadLibrary("AndroidVinKernal");
	}
	
	public native int VinKernalInit(String szSysPath,String FilePath,String CommpanyName,int nProductType,int nAultType,TelephonyManager telephonyManager,Context context);
	public native void VinKernalUnInit();
	public native void VinSetROI(int[] borders, int imgWidth, int imgHeight);
	public native int VinRecognizeMemory(int[] pImageBuffer, int nWidth, int nHeight, int nBitCount, char[] szBuffer, int BufferLen);
	public native int VinRecognizeNV21(byte[] ImageStreamNV21, int Width, int Height, char[] Buffer, int BufferLen);
	public native int VinRecognizeNV21Ex(byte[] ImageStreamNV21, int Width, int Height, char[] Buffer, int BufferLen,int []pLine);
	public native int VinRecognizeNV21Android(byte[] ImageStreamNV21, int Width, int Height, char[] Buffer, int BufferLen,int []pLine,int type);
    public native int VinSaveImage(String imagePath);
    public native String VinGetResult();
    public native String VinGetEndTime();//获取授权截止时间
    public native int VinFindVIN();
    public native int VinRecognizeImageFile(String filepath);


	/*public native int VinKernalInit(String szSysPath,String FilePath,String CommpanyName,int nProductType,int nAultType,TelephonyManager telephonyManager,Context context);
	public native void VinKernalUnInit();
	public native void VinSetROI(int[] borders, int imgWidth, int imgHeight);
	public native int VinRecognizeMemory(int[] pImageBuffer, int nWidth, int nHeight, int nBitCount, char[] szBuffer, int BufferLen);
	public native int VinRecognizeNV21(byte[] ImageStreamNV21, int Width, int Height, char[] Buffer, int BufferLen);
	public native int VinRecognizeNV21Ex(byte[] ImageStreamNV21, int Width, int Height, char[] Buffer, int BufferLen,int []pLine);
	public native int VinRecognizeNV21Android(byte[] ImageStreamNV21, int Width, int Height, char[] Buffer, int BufferLen,int []pLine,int type);
	public native int VinSaveImage(String imagePath);
	public native String VinGetResult();
	public native int VinFindVIN();
	public native int VinRecognizeImageFile(String filepath);

	public static synchronized VINAPI getVinApi(Context context){
		if(null == vinApi){
			vinApi = new VINAPI();
			String cacheDir = (context.getExternalCacheDir()).getPath();
			String userIdPath = cacheDir + "/" + VinUserIdUtil.UserID + ".lic";
			TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			int nRet = vinApi.VinKernalInit("", userIdPath, VinUserIdUtil.UserID,
					0x01, 0x02, telephonyManager, context);
			if (nRet != 0) {
				Toast.makeText(context, "激活失败", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(context, "激活成功", Toast.LENGTH_SHORT).show();
			}
		}
		return vinApi;
	}
	private static VINAPI vinApi = null;
	private VINAPI(){}*/
}
