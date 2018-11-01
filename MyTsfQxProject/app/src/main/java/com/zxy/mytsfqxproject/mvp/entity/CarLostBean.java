package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class CarLostBean {


    private int code;
    private String errmsg;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * card_id : 16
         * card_title : 维修王者卡
         * project_data_all : 保养5折 机修5折 美容5折 深度保养5折 喷漆5折 5折
         * project_data_show : 保养5折 等
         * material_data_all : 工具及耗材5折 电器5折 机修配件5折
         * material_data_show : 工具及耗材5折 等
         * special_project_all :
         * special_project_show :  等
         * special_material_all :
         * special_material_show :  等
         * remark :
         */

        private int card_id;
        private String card_title;
        private String project_data_all;
        private String project_data_show;
        private String material_data_all;
        private String material_data_show;
        private String special_project_all;
        private String special_project_show;
        private String special_material_all;
        private String special_material_show;
        private String remark;

        public int getCard_id() {
            return card_id;
        }

        public void setCard_id(int card_id) {
            this.card_id = card_id;
        }

        public String getCard_title() {
            return card_title;
        }

        public void setCard_title(String card_title) {
            this.card_title = card_title;
        }

        public String getProject_data_all() {
            return project_data_all;
        }

        public void setProject_data_all(String project_data_all) {
            this.project_data_all = project_data_all;
        }

        public String getProject_data_show() {
            return project_data_show;
        }

        public void setProject_data_show(String project_data_show) {
            this.project_data_show = project_data_show;
        }

        public String getMaterial_data_all() {
            return material_data_all;
        }

        public void setMaterial_data_all(String material_data_all) {
            this.material_data_all = material_data_all;
        }

        public String getMaterial_data_show() {
            return material_data_show;
        }

        public void setMaterial_data_show(String material_data_show) {
            this.material_data_show = material_data_show;
        }

        public String getSpecial_project_all() {
            return special_project_all;
        }

        public void setSpecial_project_all(String special_project_all) {
            this.special_project_all = special_project_all;
        }

        public String getSpecial_project_show() {
            return special_project_show;
        }

        public void setSpecial_project_show(String special_project_show) {
            this.special_project_show = special_project_show;
        }

        public String getSpecial_material_all() {
            return special_material_all;
        }

        public void setSpecial_material_all(String special_material_all) {
            this.special_material_all = special_material_all;
        }

        public String getSpecial_material_show() {
            return special_material_show;
        }

        public void setSpecial_material_show(String special_material_show) {
            this.special_material_show = special_material_show;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
