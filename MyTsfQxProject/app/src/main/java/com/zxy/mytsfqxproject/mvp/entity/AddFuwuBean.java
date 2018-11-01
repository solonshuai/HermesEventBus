package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class AddFuwuBean {

    /**
     * code : 200
     * errmsg : 成功
     * result : [{"rg_id":167,"goods_type":20,"goods_name":"美孚1号","goods_code":"RH-001","goods_num":1,"goods_saleprice":"169.00","goods_id":19,"goods_amount":"169","builder_user":" ","received_num":1},{"rg_id":174,"goods_type":20,"goods_name":"道达尔","goods_code":"LT-56","goods_num":2,"goods_saleprice":"200.00","goods_id":18,"goods_amount":"400","builder_user":" ","received_num":1},{"rg_id":177,"goods_type":10,"goods_name":"A级保养","goods_code":"AJBY","goods_num":1,"goods_saleprice":"200.00","goods_id":22,"goods_amount":"50","builder_user":"","received_num":0},{"rg_id":179,"goods_type":20,"goods_name":"靠垫","goods_code":"ZD-56","goods_num":2,"goods_saleprice":"200.00","goods_id":20,"goods_amount":"300","builder_user":" ","received_num":0}]
     */

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
         * goods_name : 美孚1号
         * goods_type : 20
         * goods_code : RH-001
         * goods_num : 1
         * goods_saleprice : 169.00
         * goods_amount : 169
         * rg_id : 167
         * goods_id : 19
         * builder_user :
         * received_num : 1 已领
         */

        private int rg_id;
        private int goods_type;
        private int goods_id;
        private String goods_code;
        private String goods_name;
        private String goods_saleprice;
        private int goods_num;
        private String goods_amount;
        private String builder_user;
        private int received_num;
        private int builder_uid;
        private int is_card;
        private String goods_oe;
        private String goods_unit;
        private String goods_price;
        private float goods_discount;
        private String total_amount;
        private int sale_uid;
        private String sale_user;
        private String remark;
        private boolean isEdit = false;

        public ResultBean(int rg_id, int goods_id, int goods_type, String goods_name, String goods_code,
                          int goods_num, String goods_saleprice, String goods_amount, String builder_user,
                          int builder_uid, int is_card, String goods_oe, String goods_unit,
                          String goods_price, float goods_discount, String total_amount, int sale_uid, String sale_user,
                          String remark, int received_num) {
            this.rg_id = rg_id;
            this.goods_id = goods_id;
            this.goods_type = goods_type;
            this.goods_name = goods_name;
            this.goods_code = goods_code;
            this.goods_num = goods_num;
            this.goods_saleprice = goods_saleprice;
            this.goods_amount = goods_amount;
            this.builder_user = builder_user;
            this.builder_uid = builder_uid;
            this.is_card = is_card;
            this.goods_oe = goods_oe;
            this.goods_unit = goods_unit;
            this.goods_price = goods_price;
            this.goods_discount = goods_discount;
            this.total_amount = total_amount;
            this.sale_uid = sale_uid;
            this.sale_user = sale_user;
            this.remark = remark;
            this.received_num = received_num;

        }

        public ResultBean(int rg_id, int goods_id, int goods_type, String goods_name, String goods_code,
                          int goods_num, String goods_saleprice, String goods_amount, String builder_user,
                          int builder_uid, int is_card, String goods_oe, String goods_unit,
                          String goods_price, float goods_discount, String total_amount, int sale_uid, String sale_user,
                          String remark) {
            this.rg_id = rg_id;
            this.goods_id = goods_id;
            this.goods_type = goods_type;
            this.goods_name = goods_name;
            this.goods_code = goods_code;
            this.goods_num = goods_num;
            this.goods_saleprice = goods_saleprice;
            this.goods_amount = goods_amount;
            this.builder_user = builder_user;
            this.builder_uid = builder_uid;
            this.is_card = is_card;
            this.goods_oe = goods_oe;
            this.goods_unit = goods_unit;
            this.goods_price = goods_price;
            this.goods_discount = goods_discount;
            this.total_amount = total_amount;
            this.sale_uid = sale_uid;
            this.sale_user = sale_user;
            this.remark = remark;
        }

        public ResultBean(int rg_id, int goods_id, int goods_type, String goods_name, String goods_code, int goods_num, String goods_saleprice, String goods_amount, int received_num) {
            this.rg_id = rg_id;
            this.goods_id = goods_id;
            this.goods_type = goods_type;
            this.goods_name = goods_name;
            this.goods_code = goods_code;
            this.goods_num = goods_num;
            this.goods_saleprice = goods_saleprice;
            this.goods_amount = goods_amount;
            this.received_num = received_num;
        }

        public boolean isEdit() {
            return isEdit;
        }

        public void setEdit(boolean edit) {
            isEdit = edit;
        }

        public int getRg_id() {
            return rg_id;
        }

        public void setRg_id(int rg_id) {
            this.rg_id = rg_id;
        }

        public int getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(int goods_type) {
            this.goods_type = goods_type;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_code() {
            return goods_code;
        }

        public void setGoods_code(String goods_code) {
            this.goods_code = goods_code;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public String getGoods_saleprice() {
            return goods_saleprice;
        }

        public void setGoods_saleprice(String goods_saleprice) {
            this.goods_saleprice = goods_saleprice;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(String goods_amount) {
            this.goods_amount = goods_amount;
        }

        public String getBuilder_user() {
            return builder_user;
        }

        public void setBuilder_user(String builder_user) {
            this.builder_user = builder_user;
        }

        public int getReceived_num() {
            return received_num;
        }

        public void setReceived_num(int received_num) {
            this.received_num = received_num;
        }

        public int getBuilder_uid() {
            return builder_uid;
        }

        public void setBuilder_uid(int builder_uid) {
            this.builder_uid = builder_uid;
        }

        public int getIs_card() {
            return is_card;
        }

        public void setIs_card(int is_card) {
            this.is_card = is_card;
        }

        public String getGoods_oe() {
            return goods_oe;
        }

        public void setGoods_oe(String goods_oe) {
            this.goods_oe = goods_oe;
        }

        public String getGoods_unit() {
            return goods_unit;
        }

        public void setGoods_unit(String goods_unit) {
            this.goods_unit = goods_unit;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public float getGoods_discount() {
            return goods_discount;
        }

        public void setGoods_discount(float goods_discount) {
            this.goods_discount = goods_discount;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public int getSale_uid() {
            return sale_uid;
        }

        public void setSale_uid(int sale_uid) {
            this.sale_uid = sale_uid;
        }

        public String getSale_user() {
            return sale_user;
        }

        public void setSale_user(String sale_user) {
            this.sale_user = sale_user;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
