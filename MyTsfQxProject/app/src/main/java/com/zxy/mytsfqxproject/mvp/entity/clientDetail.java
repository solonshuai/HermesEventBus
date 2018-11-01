package com.zxy.mytsfqxproject.mvp.entity;

import java.io.Serializable;
import java.util.List;

public class clientDetail implements Serializable{

    /**
     * code : 200
     * errmsg : 获取成功
     * result : {"client_id":43,"username":"AAAAs","phone":"15881181168","sex":"男","birthday":"0000-00-00","client_grade":1,"client_type":10,"client_from":0,"create_time":1533574313,"address":"","card_list":[],"car_list":[{"id":42,"car_number":"川AD47258","car_vin":"","car_eno":"","car_brand_name":"特斯拉 Model S","repair_count":0,"repair_amount":"0.00","no_settlement_amount":"0.00","brand_logo":"http://v3.taishifu.com.cn/store/data/upload/shop/car_brand/car-brand-pic-162.jpg","last_repair_date":0,"create_date":"2018-08-07 00:51:00"}],"repair_list":[]}
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

    public static class ResultBean implements Serializable {
        /**
         * client_id : 43
         * username : AAAAs
         * phone : 15881181168
         * sex : 男
         * birthday : 0000-00-00
         * client_grade : 1
         * client_type : 10
         * client_from : 0
         * create_time : 1533574313
         * address :
         * card_list : []
         * car_list : [{"id":42,"car_number":"川AD47258","car_vin":"","car_eno":"","car_brand_name":"特斯拉 Model S","repair_count":0,"repair_amount":"0.00","no_settlement_amount":"0.00","brand_logo":"http://v3.taishifu.com.cn/store/data/upload/shop/car_brand/car-brand-pic-162.jpg","last_repair_date":0,"create_date":"2018-08-07 00:51:00"}]
         * repair_list : []
         */

        private int client_id;
        private String username;
        private String phone;
        private String sex;
        private String birthday;
        private int client_grade;
        private int client_type;
        private int client_from;
        private int create_time;
        private String address;
        private List<CardListBean> card_list;
        private List<CarListBean> car_list;
        private List<?> repair_list;


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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getClient_grade() {
            return client_grade;
        }

        public void setClient_grade(int client_grade) {
            this.client_grade = client_grade;
        }

        public int getClient_type() {
            return client_type;
        }

        public void setClient_type(int client_type) {
            this.client_type = client_type;
        }

        public int getClient_from() {
            return client_from;
        }

        public void setClient_from(int client_from) {
            this.client_from = client_from;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<CardListBean> getCard_list() {
            return card_list;
        }

        public void setCard_list(List<CardListBean> card_list) {
            this.card_list = card_list;
        }

        public List<CarListBean> getCar_list() {
            return car_list;
        }

        public void setCar_list(List<CarListBean> car_list) {
            this.car_list = car_list;
        }

        public List<?> getRepair_list() {
            return repair_list;
        }

        public void setRepair_list(List<?> repair_list) {
            this.repair_list = repair_list;
        }

        public static class CarListBean implements Serializable {
            /**
             * id : 42
             * car_number : 川AD47258
             * car_vin :
             * car_eno :
             * car_brand_name : 特斯拉 Model S
             * repair_count : 0
             * repair_amount : 0.00
             * no_settlement_amount : 0.00
             * brand_logo : http://v3.taishifu.com.cn/store/data/upload/shop/car_brand/car-brand-pic-162.jpg
             * last_repair_date : 0
             * create_date : 2018-08-07 00:51:00
             */

            private int id;
            private String car_number;
            private String car_vin;
            private String car_eno;
            private String car_brand_name;
            private int repair_count;
            private String repair_amount;
            private String no_settlement_amount;
            private String brand_logo;
            private int last_repair_date;
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

            public String getCar_vin() {
                return car_vin;
            }

            public void setCar_vin(String car_vin) {
                this.car_vin = car_vin;
            }

            public String getCar_eno() {
                return car_eno;
            }

            public void setCar_eno(String car_eno) {
                this.car_eno = car_eno;
            }

            public String getCar_brand_name() {
                return car_brand_name;
            }

            public void setCar_brand_name(String car_brand_name) {
                this.car_brand_name = car_brand_name;
            }

            public int getRepair_count() {
                return repair_count;
            }

            public void setRepair_count(int repair_count) {
                this.repair_count = repair_count;
            }

            public String getRepair_amount() {
                return repair_amount;
            }

            public void setRepair_amount(String repair_amount) {
                this.repair_amount = repair_amount;
            }

            public String getNo_settlement_amount() {
                return no_settlement_amount;
            }

            public void setNo_settlement_amount(String no_settlement_amount) {
                this.no_settlement_amount = no_settlement_amount;
            }

            public String getBrand_logo() {
                return brand_logo;
            }

            public void setBrand_logo(String brand_logo) {
                this.brand_logo = brand_logo;
            }

            public int getLast_repair_date() {
                return last_repair_date;
            }

            public void setLast_repair_date(int last_repair_date) {
                this.last_repair_date = last_repair_date;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }
        }

        public static class CardListBean  implements Serializable{
            private int mem_id;
            private String card_number;
            private String card_title;
            private String create_time;
            private String money;
            private int project_num;

            public int getMem_id() {
                return mem_id;
            }

            public void setMem_id(int mem_id) {
                this.mem_id = mem_id;
            }

            public String getCard_number() {
                return card_number;
            }

            public void setCard_number(String card_number) {
                this.card_number = card_number;
            }

            public String getCard_title() {
                return card_title;
            }

            public void setCard_title(String card_title) {
                this.card_title = card_title;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getProject_num() {
                return project_num;
            }

            public void setProject_num(int project_num) {
                this.project_num = project_num;
            }
        }
    }
}
