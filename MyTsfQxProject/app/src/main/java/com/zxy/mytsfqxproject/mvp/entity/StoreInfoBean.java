package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class StoreInfoBean {

    /**
     * code : 200
     * errmsg :
     * result : {"store_id":8,"company_name":"陈中培asdfasd","store_name":"钛师傅店","store_phone":"028-5711540","store_type":10,"open_time":"10:35","close_time":"10:35","store_hotline":"028-5711540","store_zy":"1,2","store_serve":"4,10","store_address":"四川省成都市锦江区柳江街道南三环路三段158号东风风神及雷诺4S店","facility":"自动化洗车","open_name":"","bank_name":"asdfasd","bank_account":"12345678900000009","store_logo":null,"store_slide":null,"wx_qrcode":"0","store_type_txt":"修理保养","brand_names":"奥迪,阿斯顿马丁","brand_list":[{"brand_id":1,"brand_name":"奥迪"},{"brand_id":2,"brand_name":"阿斯顿马丁"}]}
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
         * store_id : 8
         * company_name : 陈中培asdfasd
         * store_name : 钛师傅店
         * store_phone : 028-5711540
         * store_type : 10
         * open_time : 10:35
         * close_time : 10:35
         * store_hotline : 028-5711540
         * store_zy : 1,2
         * store_serve : 4,10
         * store_address : 四川省成都市锦江区柳江街道南三环路三段158号东风风神及雷诺4S店
         * facility : 自动化洗车
         * open_name :
         * bank_name : asdfasd
         * bank_account : 12345678900000009
         * store_logo : null
         * store_slide : null
         * wx_qrcode : 0
         * store_type_txt : 修理保养
         * brand_names : 奥迪,阿斯顿马丁
         * brand_list : [{"brand_id":1,"brand_name":"奥迪"},{"brand_id":2,"brand_name":"阿斯顿马丁"}]
         */
        private int store_id;
        private String company_name;
        private String store_name;
        private String store_phone;
        private int store_type;
        private String open_time;
        private String close_time;
        private String store_hotline;
        private String store_zy;
        private String store_serve;
        private String store_address;
        private String facility;
        private String open_name;
        private String bank_name;
        private String bank_account;
        private Object store_logo;
        private Object store_slide;
        private String wx_qrcode;
        private String store_type_txt;
        private String brand_names;
        private List<BrandListBean> brand_list;
        private int staff_id;
        private String username;
        private String phone;
        private String sex;
        private String employee_number;
        private String home_address;
        private String type_work;
        private String birth_day;
        private String join_date;
        private String contract_end_date;
        private String card;
        private String department_id;
        private String we_chat;
        private String photo;
        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_phone() {
            return store_phone;
        }

        public void setStore_phone(String store_phone) {
            this.store_phone = store_phone;
        }

        public int getStore_type() {
            return store_type;
        }

        public void setStore_type(int store_type) {
            this.store_type = store_type;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public String getClose_time() {
            return close_time;
        }

        public void setClose_time(String close_time) {
            this.close_time = close_time;
        }

        public String getStore_hotline() {
            return store_hotline;
        }

        public void setStore_hotline(String store_hotline) {
            this.store_hotline = store_hotline;
        }

        public String getStore_zy() {
            return store_zy;
        }

        public void setStore_zy(String store_zy) {
            this.store_zy = store_zy;
        }

        public String getStore_serve() {
            return store_serve;
        }

        public void setStore_serve(String store_serve) {
            this.store_serve = store_serve;
        }

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }

        public String getFacility() {
            return facility;
        }

        public void setFacility(String facility) {
            this.facility = facility;
        }

        public String getOpen_name() {
            return open_name;
        }

        public void setOpen_name(String open_name) {
            this.open_name = open_name;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_account() {
            return bank_account;
        }

        public void setBank_account(String bank_account) {
            this.bank_account = bank_account;
        }

        public Object getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(Object store_logo) {
            this.store_logo = store_logo;
        }

        public Object getStore_slide() {
            return store_slide;
        }

        public void setStore_slide(Object store_slide) {
            this.store_slide = store_slide;
        }

        public String getWx_qrcode() {
            return wx_qrcode;
        }

        public void setWx_qrcode(String wx_qrcode) {
            this.wx_qrcode = wx_qrcode;
        }

        public String getStore_type_txt() {
            return store_type_txt;
        }

        public void setStore_type_txt(String store_type_txt) {
            this.store_type_txt = store_type_txt;
        }

        public String getBrand_names() {
            return brand_names;
        }

        public void setBrand_names(String brand_names) {
            this.brand_names = brand_names;
        }

        public List<BrandListBean> getBrand_list() {
            return brand_list;
        }

        public void setBrand_list(List<BrandListBean> brand_list) {
            this.brand_list = brand_list;
        }

        public static class BrandListBean {
            /**
             * brand_id : 1
             * brand_name : 奥迪
             */

            private int brand_id;
            private String brand_name;

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }
        }

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
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

        public String getEmployee_number() {
            return employee_number;
        }

        public void setEmployee_number(String employee_number) {
            this.employee_number = employee_number;
        }

        public String getHome_address() {
            return home_address;
        }

        public void setHome_address(String home_address) {
            this.home_address = home_address;
        }

        public String getType_work() {
            return type_work;
        }

        public void setType_work(String type_work) {
            this.type_work = type_work;
        }

        public String getBirth_day() {
            return birth_day;
        }

        public void setBirth_day(String birth_day) {
            this.birth_day = birth_day;
        }

        public String getJoin_date() {
            return join_date;
        }

        public void setJoin_date(String join_date) {
            this.join_date = join_date;
        }

        public String getContract_end_date() {
            return contract_end_date;
        }

        public void setContract_end_date(String contract_end_date) {
            this.contract_end_date = contract_end_date;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(String department_id) {
            this.department_id = department_id;
        }

        public String getWe_chat() {
            return we_chat;
        }

        public void setWe_chat(String we_chat) {
            this.we_chat = we_chat;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
