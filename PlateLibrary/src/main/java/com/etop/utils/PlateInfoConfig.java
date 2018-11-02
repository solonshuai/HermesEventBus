package com.etop.utils;

import java.io.Serializable;

/**
 * 车牌页面配置类
 */

public class PlateInfoConfig implements Serializable{
    //设置识别框位置
    private int [] rectPlace;
    //是否保存车牌图像
    private Boolean isSaveImage = false;
    //保存图像的路径
    private String strSaveImagePath = "/alpha/Plate/";

    public String getStrSaveImagePath() {
        return strSaveImagePath;
    }

    public void setStrSaveImagePath(String strSaveImagePath) {
        this.strSaveImagePath = strSaveImagePath;
    }

    public void setIsSaveImage(Boolean isSaveImage) {
        this.isSaveImage = isSaveImage;
    }

    public Boolean getIsSaveImage() {
        return isSaveImage;
    }
}
