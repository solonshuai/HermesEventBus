package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class ChooseTechBean {

    /**
     * code : 200
     * errmsg :
     * result : {"total":2,"per_page":15,"current_page":1,"last_page":1,"data":[{"staff_id":27,"employee_number":"","username":"18780024125","phone":"18780024125","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"2018-10-11 10:22:06","department_id":"","is_login_allow":1,"is_admin":1,"roles":"36","login_allow":"允许"},{"staff_id":7,"employee_number":"","username":"13881790335","phone":"18888888888","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"2018-10-08 10:38:13","department_id":"","is_login_allow":1,"is_admin":1,"roles":"25","login_allow":"允许"}]}
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
         * total : 2
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"staff_id":27,"employee_number":"","username":"18780024125","phone":"18780024125","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"2018-10-11 10:22:06","department_id":"","is_login_allow":1,"is_admin":1,"roles":"36","login_allow":"允许"},{"staff_id":7,"employee_number":"","username":"13881790335","phone":"18888888888","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"2018-10-08 10:38:13","department_id":"","is_login_allow":1,"is_admin":1,"roles":"25","login_allow":"允许"}]
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

        public static class DataBean {
            /**
             * staff_id : 27
             * employee_number :
             * username : 18780024125
             * phone : 18780024125
             * sex : 男
             * card :
             * birth_day : 0000-00-00
             * join_date : 0000-00-00
             * contract_end_date : 0000-00-00
             * last_login_time : 2018-10-11 10:22:06
             * department_id :
             * is_login_allow : 1
             * is_admin : 1
             * roles : 36
             * login_allow : 允许
             */

            private int staff_id;
            private String employee_number;
            private String username;
            private String phone;
            private String sex;
            private String card;
            private String birth_day;
            private String join_date;
            private String contract_end_date;
            private String last_login_time;
            private String department_id;
            private int is_login_allow;
            private int is_admin;
            private String roles;
            private String login_allow;

            public int getStaff_id() {
                return staff_id;
            }

            public void setStaff_id(int staff_id) {
                this.staff_id = staff_id;
            }

            public String getEmployee_number() {
                return employee_number;
            }

            public void setEmployee_number(String employee_number) {
                this.employee_number = employee_number;
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

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
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

            public String getLast_login_time() {
                return last_login_time;
            }

            public void setLast_login_time(String last_login_time) {
                this.last_login_time = last_login_time;
            }

            public String getDepartment_id() {
                return department_id;
            }

            public void setDepartment_id(String department_id) {
                this.department_id = department_id;
            }

            public int getIs_login_allow() {
                return is_login_allow;
            }

            public void setIs_login_allow(int is_login_allow) {
                this.is_login_allow = is_login_allow;
            }

            public int getIs_admin() {
                return is_admin;
            }

            public void setIs_admin(int is_admin) {
                this.is_admin = is_admin;
            }

            public String getRoles() {
                return roles;
            }

            public void setRoles(String roles) {
                this.roles = roles;
            }

            public String getLogin_allow() {
                return login_allow;
            }

            public void setLogin_allow(String login_allow) {
                this.login_allow = login_allow;
            }
        }
    }
}
