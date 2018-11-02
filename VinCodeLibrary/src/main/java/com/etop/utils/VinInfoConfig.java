package com.etop.utils;

import java.io.Serializable;

/**
 * VIN码页面配置类
 */

public class VinInfoConfig implements Serializable{
    //是否保存银行卡图像
    private Boolean isSaveImage = false;
    //保存图像的路径
    private String strSaveImagePath = "/alpha/VinCode/";

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
