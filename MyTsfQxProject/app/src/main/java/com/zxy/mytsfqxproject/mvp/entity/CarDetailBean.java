package com.zxy.mytsfqxproject.mvp.entity;

public class CarDetailBean {

    /**
     * code : 200
     * errmsg :
     * result : {"car_number":"川A22222","car_brand_name":"本田","car_id":150,"car_vin":"WERAAXCVBN1234567","client_id":111,"car_series_name":"锋范","phone":"18780024125","username":"czp","sex":"男"}
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
         * car_number : 川A22222
         * car_brand_name : 本田
         * car_id : 150
         * car_vin : WERAAXCVBN1234567
         * client_id : 111
         * car_series_name : 锋范
         * phone : 18780024125
         * username : czp
         * sex : 男
         */

        private String car_number;
        private String car_brand_name="";
        private int car_id;
        private String car_vin;
        private int client_id;
        private String car_series_name="";
        private String phone;
        private String username;
        private String sex;

        public String getCar_number() {
            return car_number;
        }

        public void setCar_number(String car_number) {
            this.car_number = car_number;
        }

        public String getCar_brand_name() {
            return car_brand_name;
        }

        public void setCar_brand_name(String car_brand_name) {
            this.car_brand_name = car_brand_name;
        }

        public int getCar_id() {
            return car_id;
        }

        public void setCar_id(int car_id) {
            this.car_id = car_id;
        }

        public String getCar_vin() {
            return car_vin;
        }

        public void setCar_vin(String car_vin) {
            this.car_vin = car_vin;
        }

        public int getClient_id() {
            return client_id;
        }

        public void setClient_id(int client_id) {
            this.client_id = client_id;
        }

        public String getCar_series_name() {
            return car_series_name;
        }

        public void setCar_series_name(String car_series_name) {
            this.car_series_name = car_series_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
