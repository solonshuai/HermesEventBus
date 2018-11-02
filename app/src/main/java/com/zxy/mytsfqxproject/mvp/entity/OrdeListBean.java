package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class OrdeListBean {


    /**
     * code : 200
     * errmsg : 获取成功
     * result : {"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"bespeak_id":1,"client_id":37,"username":"C","phone":"18780024125","car_id":31,"car_number":"川A55555","car_series_name":"宝马3系","serve_project":"机电维修","remark":"","bespeak_time":1539139440,"create_time":1538966650,"state":10,"state_txt":"待接待","client_grade":"A级"}]}
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
         * total : 1
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"bespeak_id":1,"client_id":37,"username":"C","phone":"18780024125","car_id":31,"car_number":"川A55555","car_series_name":"宝马3系","serve_project":"机电维修","remark":"","bespeak_time":1539139440,"create_time":1538966650,"state":10,"state_txt":"待接待","client_grade":"A级"}]
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
             * bespeak_id : 1
             * client_id : 37
             * username : C
             * phone : 18780024125
             * car_id : 31
             * car_number : 川A55555
             * car_series_name : 宝马3系
             * serve_project : 机电维修
             * remark :
             * bespeak_time : 1539139440
             * create_time : 1538966650
             * state : 10
             * state_txt : 待接待
             * client_grade : A级
             */

            private int bespeak_id;
            private int client_id;
            private String username;
            private String phone;
            private int car_id;
            private String car_number;
            private String car_series_name;
            private String serve_project;
            private String remark;
            private int bespeak_time;
            private int create_time;
            private int state;
            private String state_txt;
            private String client_grade;

            public int getBespeak_id() {
                return bespeak_id;
            }

            public void setBespeak_id(int bespeak_id) {
                this.bespeak_id = bespeak_id;
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

            public int getCar_id() {
                return car_id;
            }

            public void setCar_id(int car_id) {
                this.car_id = car_id;
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

            public String getServe_project() {
                return serve_project;
            }

            public void setServe_project(String serve_project) {
                this.serve_project = serve_project;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getBespeak_time() {
                return bespeak_time;
            }

            public void setBespeak_time(int bespeak_time) {
                this.bespeak_time = bespeak_time;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getState_txt() {
                return state_txt;
            }

            public void setState_txt(String state_txt) {
                this.state_txt = state_txt;
            }

            public String getClient_grade() {
                return client_grade;
            }

            public void setClient_grade(String client_grade) {
                this.client_grade = client_grade;
            }
        }
    }
}
