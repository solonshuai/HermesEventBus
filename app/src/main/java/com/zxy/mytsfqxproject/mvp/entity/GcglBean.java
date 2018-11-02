package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

/**
 * 过程管理首页
 */
public class GcglBean {

    /**
     * code : 200
     * errmsg : 成功
     * result : [{"repair_id":85,"repair_state":"已领料","username":"葛小伦","car_number":"蒙A66666","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg","reception_user":"13881790335","create_time":1539052785,"expect_complete_time":0,"assgin":"not","goods_name":["道达尔"]},{"repair_id":87,"repair_state":"待领料","username":"C","car_number":"川A55555","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-9.jpg","reception_user":"18780024125","create_time":1539052941,"expect_complete_time":1539225720,"assgin":"yes","goods_name":["A级保养","道达尔"]},{"repair_id":88,"repair_state":"待领料","username":"C","car_number":"川A11111","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg","reception_user":"18780024125","create_time":1539054253,"expect_complete_time":1539100800,"assgin":"yes","goods_name":["美孚1号","道达尔","美孚1号","A级保养","靠垫"]},{"repair_id":102,"repair_state":null,"username":"C","car_number":"川A33333","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-8.jpg","reception_user":"18780024125","create_time":1539158993,"expect_complete_time":0,"assgin":"yes","goods_name":[]},{"repair_id":117,"repair_state":null,"username":"C","car_number":"川A22222","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-7.jpg","reception_user":"18780024125","create_time":1539310365,"expect_complete_time":1539396720,"assgin":"yes","goods_name":[]},{"repair_id":118,"repair_state":"待领料","username":"B","car_number":"川A89999","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg","reception_user":"18780024125","create_time":1539310510,"expect_complete_time":1539483240,"assgin":"yes","goods_name":["A级保养","道达尔","美孚1号"]}]
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
         * repair_id : 85
         * repair_state : 已领料
         * username : 葛小伦
         * car_number : 蒙A66666
         * brand_logo : http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg
         * reception_user : 13881790335
         * create_time : 1539052785
         * expect_complete_time : 0
         * assgin : not
         * goods_name : ["道达尔"]
         */

        private int repair_id;
        private String repair_state;
        private String username;
        private String car_number;
        private String brand_logo;
        private String reception_user;
        private int create_time;
        private int expect_complete_time;
        private String assgin;
        private List<String> goods_name;

        public int getRepair_id() {
            return repair_id;
        }

        public void setRepair_id(int repair_id) {
            this.repair_id = repair_id;
        }

        public String getRepair_state() {
            return repair_state;
        }

        public void setRepair_state(String repair_state) {
            this.repair_state = repair_state;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCar_number() {
            return car_number;
        }

        public void setCar_number(String car_number) {
            this.car_number = car_number;
        }

        public String getBrand_logo() {
            return brand_logo;
        }

        public void setBrand_logo(String brand_logo) {
            this.brand_logo = brand_logo;
        }

        public String getReception_user() {
            return reception_user;
        }

        public void setReception_user(String reception_user) {
            this.reception_user = reception_user;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getExpect_complete_time() {
            return expect_complete_time;
        }

        public void setExpect_complete_time(int expect_complete_time) {
            this.expect_complete_time = expect_complete_time;
        }

        public String getAssgin() {
            return assgin;
        }

        public void setAssgin(String assgin) {
            this.assgin = assgin;
        }

        public List<String> getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(List<String> goods_name) {
            this.goods_name = goods_name;
        }
    }
}
