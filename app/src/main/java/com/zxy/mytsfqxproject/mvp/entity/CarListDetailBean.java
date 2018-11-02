package com.zxy.mytsfqxproject.mvp.entity;

public class CarListDetailBean {

    /**
     * code : 200
     * errmsg :
     * result : {"username":"AAAAs","phone":"15881181168","car_number":"川AD47258","car_vin":"","create_date":"2018-08-07 00:51:00","car_brand_name":"特斯拉","car_eno":"","insurance_over_time":0,"client_id":43,"brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-162.jpg","buy_money":"0.00","car_photo":"","sex":"男","client_grade":1}
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

    public static class ResultBean {
        /**
         * username : AAAAs
         * phone : 15881181168
         * car_number : 川AD47258
         * car_vin :
         * create_date : 2018-08-07 00:51:00
         * car_brand_name : 特斯拉
         * car_eno :
         * insurance_over_time : 0
         * client_id : 43
         * brand_logo : http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-162.jpg
         * buy_money : 0.00
         * car_photo :
         * sex : 男
         * client_grade : 1
         */

        private String username;
        private String phone;
        private String car_number;
        private String car_vin;
        private String create_date;
        private String car_brand_name;
        private String car_eno;
        private int insurance_over_time;
        private int client_id;
        private String brand_logo;
        private String buy_money;
        private String car_photo;
        private String sex;
        private int client_grade;

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

        public String getCar_number() {
            return car_number;
        }

        public void setCar_number(String car_number) {
            this.car_number = car_number;
        }

        public String getCar_vin() {
            return car_vin;
        }

        public void setCar_vin(String car_vin) {
            this.car_vin = car_vin;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getCar_brand_name() {
            return car_brand_name;
        }

        public void setCar_brand_name(String car_brand_name) {
            this.car_brand_name = car_brand_name;
        }

        public String getCar_eno() {
            return car_eno;
        }

        public void setCar_eno(String car_eno) {
            this.car_eno = car_eno;
        }

        public int getInsurance_over_time() {
            return insurance_over_time;
        }

        public void setInsurance_over_time(int insurance_over_time) {
            this.insurance_over_time = insurance_over_time;
        }

        public int getClient_id() {
            return client_id;
        }

        public void setClient_id(int client_id) {
            this.client_id = client_id;
        }

        public String getBrand_logo() {
            return brand_logo;
        }

        public void setBrand_logo(String brand_logo) {
            this.brand_logo = brand_logo;
        }

        public String getBuy_money() {
            return buy_money;
        }

        public void setBuy_money(String buy_money) {
            this.buy_money = buy_money;
        }

        public String getCar_photo() {
            return car_photo;
        }

        public void setCar_photo(String car_photo) {
            this.car_photo = car_photo;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getClient_grade() {
            return client_grade;
        }

        public void setClient_grade(int client_grade) {
            this.client_grade = client_grade;
        }
    }
}
