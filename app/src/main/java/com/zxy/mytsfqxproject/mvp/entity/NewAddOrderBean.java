package com.zxy.mytsfqxproject.mvp.entity;

import java.io.Serializable;
import java.util.List;

public class NewAddOrderBean implements Serializable{

    /**
     * code : 200
     * errmsg : 车辆列表
     * result : {"total":4,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":31,"car_number":"川A55555","car_series_name":"宝马3系","car_vin":"QAZWSXEDCF1234567","car_eno":null,"brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-9.jpg","client_id":37,"username":"C","phone":"18780024125","repair_count":5,"last_repair_date":"2018-10-07 16:30","create_date":"0000-00-00 00:00:00"},{"id":27,"car_number":"川A33333","car_series_name":"奔驰E级","car_vin":"qwertyuiop1234564","car_eno":"qwertyu123","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-8.jpg","client_id":0,"username":"","phone":"","repair_count":0,"last_repair_date":"","create_date":"2018-10-07 16:51:10"},{"id":26,"car_number":"川A22222","car_series_name":"思迪","car_vin":"POIUYTREWQ1234567","car_eno":"QWERTYUIO9","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-7.jpg","client_id":0,"username":"","phone":"","repair_count":0,"last_repair_date":"","create_date":"2018-10-07 16:19:10"},{"id":25,"car_number":"川A11111","car_series_name":"奥迪A3","car_vin":"AQWSRTYUIO1234567","car_eno":"QWERTYUIO91","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg","client_id":37,"username":"C","phone":"18780024125","repair_count":1,"last_repair_date":"2018-10-08 09:35","create_date":"2018-10-07 16:14:10"}]}
     */

    private int code;
    private String errmsg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * total : 4
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"id":31,"car_number":"川A55555","car_series_name":"宝马3系","car_vin":"QAZWSXEDCF1234567","car_eno":null,"brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-9.jpg","client_id":37,"username":"C","phone":"18780024125","repair_count":5,"last_repair_date":"2018-10-07 16:30","create_date":"0000-00-00 00:00:00"},{"id":27,"car_number":"川A33333","car_series_name":"奔驰E级","car_vin":"qwertyuiop1234564","car_eno":"qwertyu123","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-8.jpg","client_id":0,"username":"","phone":"","repair_count":0,"last_repair_date":"","create_date":"2018-10-07 16:51:10"},{"id":26,"car_number":"川A22222","car_series_name":"思迪","car_vin":"POIUYTREWQ1234567","car_eno":"QWERTYUIO9","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-7.jpg","client_id":0,"username":"","phone":"","repair_count":0,"last_repair_date":"","create_date":"2018-10-07 16:19:10"},{"id":25,"car_number":"川A11111","car_series_name":"奥迪A3","car_vin":"AQWSRTYUIO1234567","car_eno":"QWERTYUIO91","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg","client_id":37,"username":"C","phone":"18780024125","repair_count":1,"last_repair_date":"2018-10-08 09:35","create_date":"2018-10-07 16:14:10"}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable {
            /**
             * id : 31
             * car_number : 川A55555
             * car_series_name : 宝马3系
             * car_vin : QAZWSXEDCF1234567
             * car_eno : null
             * brand_logo : http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-9.jpg
             * client_id : 37
             * username : C
             * phone : 18780024125
             * repair_count : 5
             * last_repair_date : 2018-10-07 16:30
             * create_date : 0000-00-00 00:00:00
             */

            private int id;
            private String car_number;
            private String car_series_name;
            private String car_vin;
            private Object car_eno;
            private String brand_logo;
            private int client_id;
            private String username;
            private String phone;
            private int repair_count;
            private String last_repair_date;
            private String create_date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCar_number() {
                return car_number;
            }

            public void setCar_number(String car_number) {
                this.car_number = car_number;
            }

            public String getCar_series_name() {
                return car_series_name;
            }

            public void setCar_series_name(String car_series_name) {
                this.car_series_name = car_series_name;
            }

            public String getCar_vin() {
                return car_vin;
            }

            public void setCar_vin(String car_vin) {
                this.car_vin = car_vin;
            }

            public Object getCar_eno() {
                return car_eno;
            }

            public void setCar_eno(Object car_eno) {
                this.car_eno = car_eno;
            }

            public String getBrand_logo() {
                return brand_logo;
            }

            public void setBrand_logo(String brand_logo) {
                this.brand_logo = brand_logo;
            }

            public int getClient_id() {
                return client_id;
            }

            public void setClient_id(int client_id) {
                this.client_id = client_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getRepair_count() {
                return repair_count;
            }

            public void setRepair_count(int repair_count) {
                this.repair_count = repair_count;
            }

            public String getLast_repair_date() {
                return last_repair_date;
            }

            public void setLast_repair_date(String last_repair_date) {
                this.last_repair_date = last_repair_date;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }
        }
    }
}
