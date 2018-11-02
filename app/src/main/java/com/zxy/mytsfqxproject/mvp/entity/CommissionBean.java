package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

/**
 * 获取提成员工的列表
 */
public class CommissionBean {

    /**
     * code : 200
     * errmsg :
     * result : [{"staff_id":96,"employee_number":"YGBH123","username":"潘帅","phone":"18030839218","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"2018-09-30 14:25:48","department_id":"17","is_login_allow":1,"is_admin":0},{"staff_id":58,"employee_number":"001","username":"王帅","phone":"13881790339","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"2018-09-27 10:06:30","department_id":"3","is_login_allow":1,"is_admin":1},{"staff_id":49,"employee_number":"215243","username":"不知道","phone":"18888888889","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"2018-09-30 10:32:54","department_id":"148","is_login_allow":1,"is_admin":0},{"staff_id":44,"employee_number":"002","username":"李总","phone":"13822769156","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"0000-00-00 00:00:00","department_id":"16","is_login_allow":1,"is_admin":0},{"staff_id":40,"employee_number":"003","username":"原新纪","phone":"18629532948","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"2019-12-31","last_login_time":"2018-08-22 15:00:18","department_id":"18","is_login_allow":1,"is_admin":1},{"staff_id":39,"employee_number":"004","username":"钟玲","phone":"18629532949","sex":"女","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"0000-00-00 00:00:00","department_id":"3","is_login_allow":1,"is_admin":0},{"staff_id":36,"employee_number":"005","username":"李总","phone":"18030490797","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"2018-08-31","last_login_time":"2018-09-10 20:14:38","department_id":"3","is_login_allow":1,"is_admin":0},{"staff_id":22,"employee_number":"006","username":"刘总","phone":"13555555555","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"2018-09-03 15:12:27","department_id":"3","is_login_allow":1,"is_admin":0},{"staff_id":4,"employee_number":"007","username":"李永龙","phone":"13881904379","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"2018-09-29 11:33:19","department_id":"3","is_login_allow":1,"is_admin":0},{"staff_id":3,"employee_number":"008","username":"袁涛","phone":"13881790333","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"0000-00-00","last_login_time":"2018-07-31 09:21:57","department_id":"4","is_login_allow":1,"is_admin":0},{"staff_id":2,"employee_number":"009","username":"赵总","phone":"18582494674","sex":"男","card":"","birth_day":"0000-00-00","join_date":"0000-00-00","contract_end_date":"2018-09-29","last_login_time":"2018-09-10 19:29:55","department_id":"17","is_login_allow":1,"is_admin":0}]
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
         * staff_id : 96
         * employee_number : YGBH123
         * username : 潘帅
         * phone : 18030839218
         * sex : 男
         * card :
         * birth_day : 0000-00-00
         * join_date : 0000-00-00
         * contract_end_date : 0000-00-00
         * last_login_time : 2018-09-30 14:25:48
         * department_id : 17
         * is_login_allow : 1
         * is_admin : 0
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
    }
}
