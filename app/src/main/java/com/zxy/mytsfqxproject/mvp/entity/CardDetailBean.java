package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class CardDetailBean {

    /**
     * code : 200
     * errmsg :
     * result : {"mem_id":4,"client_id":41,"card_number":"2018103505","username":"ps","phone":"14725803691","card_title":"普通会员卡","is_limit":0,"money":"4000.00","create_time":1539245294,"project_data":null,"material_data":null,"special_project_data":null,"special_material_data":null,"project_list":[],"recharge_list":[{"recharge_id":5,"recharge_num":"CZ18101110155985","recharge_type":10,"total_amount":"2000.00","staff_user":"无视","rake_staff_user":"","create_time":1539245294,"pay_name":"支付宝","project_data":""}]}
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
         * mem_id : 4
         * client_id : 41
         * card_number : 2018103505
         * username : ps
         * phone : 14725803691
         * card_title : 普通会员卡
         * is_limit : 0
         * money : 4000.00
         * create_time : 1539245294
         * project_data : null
         * material_data : null
         * special_project_data : null
         * special_material_data : null
         * project_list : []
         * recharge_list : [{"recharge_id":5,"recharge_num":"CZ18101110155985","recharge_type":10,"total_amount":"2000.00","staff_user":"无视","rake_staff_user":"","create_time":1539245294,"pay_name":"支付宝","project_data":""}]
         */

        private int mem_id;
        private int client_id;
        private String card_number;
        private String username;
        private String phone;
        private String card_title;
        private int is_limit;
        private String money;
        private int create_time;
        private Object project_data;
        private Object material_data;
        private Object special_project_data;
        private Object special_material_data;
        private List<?> project_list;
        private List<RechargeListBean> recharge_list;

        public int getMem_id() {
            return mem_id;
        }

        public void setMem_id(int mem_id) {
            this.mem_id = mem_id;
        }

        public int getClient_id() {
            return client_id;
        }

        public void setClient_id(int client_id) {
            this.client_id = client_id;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
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

        public String getCard_title() {
            return card_title;
        }

        public void setCard_title(String card_title) {
            this.card_title = card_title;
        }

        public int getIs_limit() {
            return is_limit;
        }

        public void setIs_limit(int is_limit) {
            this.is_limit = is_limit;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public Object getProject_data() {
            return project_data;
        }

        public void setProject_data(Object project_data) {
            this.project_data = project_data;
        }

        public Object getMaterial_data() {
            return material_data;
        }

        public void setMaterial_data(Object material_data) {
            this.material_data = material_data;
        }

        public Object getSpecial_project_data() {
            return special_project_data;
        }

        public void setSpecial_project_data(Object special_project_data) {
            this.special_project_data = special_project_data;
        }

        public Object getSpecial_material_data() {
            return special_material_data;
        }

        public void setSpecial_material_data(Object special_material_data) {
            this.special_material_data = special_material_data;
        }

        public List<?> getProject_list() {
            return project_list;
        }

        public void setProject_list(List<?> project_list) {
            this.project_list = project_list;
        }

        public List<RechargeListBean> getRecharge_list() {
            return recharge_list;
        }

        public void setRecharge_list(List<RechargeListBean> recharge_list) {
            this.recharge_list = recharge_list;
        }

        public static class RechargeListBean {
            /**
             * recharge_id : 5
             * recharge_num : CZ18101110155985
             * recharge_type : 10
             * total_amount : 2000.00
             * staff_user : 无视
             * rake_staff_user :
             * create_time : 1539245294
             * pay_name : 支付宝
             * project_data :
             */

            private int recharge_id;
            private String recharge_num;
            private int recharge_type;
            private String total_amount;
            private String staff_user;
            private String rake_staff_user;
            private int create_time;
            private String pay_name;
            private String project_data;

            public int getRecharge_id() {
                return recharge_id;
            }

            public void setRecharge_id(int recharge_id) {
                this.recharge_id = recharge_id;
            }

            public String getRecharge_num() {
                return recharge_num;
            }

            public void setRecharge_num(String recharge_num) {
                this.recharge_num = recharge_num;
            }

            public int getRecharge_type() {
                return recharge_type;
            }

            public void setRecharge_type(int recharge_type) {
                this.recharge_type = recharge_type;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getStaff_user() {
                return staff_user;
            }

            public void setStaff_user(String staff_user) {
                this.staff_user = staff_user;
            }

            public String getRake_staff_user() {
                return rake_staff_user;
            }

            public void setRake_staff_user(String rake_staff_user) {
                this.rake_staff_user = rake_staff_user;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getPay_name() {
                return pay_name;
            }

            public void setPay_name(String pay_name) {
                this.pay_name = pay_name;
            }

            public String getProject_data() {
                return project_data;
            }

            public void setProject_data(String project_data) {
                this.project_data = project_data;
            }
        }
    }
}
