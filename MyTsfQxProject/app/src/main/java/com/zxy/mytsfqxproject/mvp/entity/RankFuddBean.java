package com.zxy.mytsfqxproject.mvp.entity;

import java.io.Serializable;
import java.util.List;

public class RankFuddBean implements Serializable{

    /**
     * code : 200
     * errmsg :
     * result : {"total":18,"per_page":15,"current_page":1,"last_page":2,"data":[{"repair_id":81,"order_amount":"220.00","car_id":31,"car_number":"川A55555","remark":"23rtgrfeewf","expect_complete_time":1539224280,"create_time":1539051529,"reception_user":"18780024125","username":"C","client_id":37,"client_grade":10,"repair_state":20,"order_state":10,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png","goods_name":["A级保养","道达尔"]},{"repair_id":80,"order_amount":"200.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1539051087,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["道达尔"]},{"repair_id":75,"order_amount":"55.80","car_id":37,"car_number":"川A12354","remark":"shjs","expect_complete_time":1538928000,"create_time":1538989818,"reception_user":"18780024125","username":"ps","client_id":41,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png","goods_name":["A级保养","道达尔"]},{"repair_id":74,"order_amount":"200.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538989384,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":70,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["道达尔"]},{"repair_id":73,"order_amount":"20.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538988592,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["A级保养"]},{"repair_id":72,"order_amount":"20.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538987203,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["A级保养"]},{"repair_id":71,"order_amount":"20.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538987087,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["A级保养"]},{"repair_id":68,"order_amount":"189.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538983750,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["A级保养","美孚1号"]},{"repair_id":67,"order_amount":"338.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538977755,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["美孚1号"]},{"repair_id":66,"order_amount":"200.00","car_id":34,"car_number":"蒙A88888","remark":"","expect_complete_time":0,"create_time":1538969040,"reception_user":"13881790335","username":"天使颜","client_id":39,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["道达尔"]},{"repair_id":65,"order_amount":"369.00","car_id":31,"car_number":"川A55555","remark":"","expect_complete_time":0,"create_time":1538968446,"reception_user":"13881790335","username":"C","client_id":37,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png","goods_name":["道达尔","美孚1号"]},{"repair_id":64,"order_amount":"0.00","car_id":25,"car_number":"川A11111","remark":"12rqwer","expect_complete_time":1538967660,"create_time":1538964099,"reception_user":"18780024125","username":"C","client_id":37,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":[]},{"repair_id":62,"order_amount":"0.00","car_id":25,"car_number":"川A11111","remark":"23123","expect_complete_time":1539048900,"create_time":1538962515,"reception_user":"18780024125","username":"C","client_id":37,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":[]},{"repair_id":60,"order_amount":"20.00","car_id":31,"car_number":"川A55555","remark":"asdfasd","expect_complete_time":1538987400,"create_time":1538901014,"reception_user":"18780024125","username":"C","client_id":37,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png","goods_name":["A级保养"]},{"repair_id":59,"order_amount":"0.00","car_id":31,"car_number":"川A55555","remark":"asdfasd","expect_complete_time":1538987340,"create_time":1538900956,"reception_user":"18780024125","username":"C","client_id":37,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png","goods_name":[]}]}
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
         * total : 18
         * per_page : 15
         * current_page : 1
         * last_page : 2
         * data : [{"repair_id":81,"order_amount":"220.00","car_id":31,"car_number":"川A55555","remark":"23rtgrfeewf","expect_complete_time":1539224280,"create_time":1539051529,"reception_user":"18780024125","username":"C","client_id":37,"client_grade":10,"repair_state":20,"order_state":10,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png","goods_name":["A级保养","道达尔"]},{"repair_id":80,"order_amount":"200.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1539051087,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["道达尔"]},{"repair_id":75,"order_amount":"55.80","car_id":37,"car_number":"川A12354","remark":"shjs","expect_complete_time":1538928000,"create_time":1538989818,"reception_user":"18780024125","username":"ps","client_id":41,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png","goods_name":["A级保养","道达尔"]},{"repair_id":74,"order_amount":"200.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538989384,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":70,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["道达尔"]},{"repair_id":73,"order_amount":"20.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538988592,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["A级保养"]},{"repair_id":72,"order_amount":"20.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538987203,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["A级保养"]},{"repair_id":71,"order_amount":"20.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538987087,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["A级保养"]},{"repair_id":68,"order_amount":"189.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538983750,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["A级保养","美孚1号"]},{"repair_id":67,"order_amount":"338.00","car_id":35,"car_number":"蒙A66666","remark":"","expect_complete_time":0,"create_time":1538977755,"reception_user":"13881790335","username":"葛小伦","client_id":40,"client_grade":10,"repair_state":60,"order_state":20,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["美孚1号"]},{"repair_id":66,"order_amount":"200.00","car_id":34,"car_number":"蒙A88888","remark":"","expect_complete_time":0,"create_time":1538969040,"reception_user":"13881790335","username":"天使颜","client_id":39,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":["道达尔"]},{"repair_id":65,"order_amount":"369.00","car_id":31,"car_number":"川A55555","remark":"","expect_complete_time":0,"create_time":1538968446,"reception_user":"13881790335","username":"C","client_id":37,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png","goods_name":["道达尔","美孚1号"]},{"repair_id":64,"order_amount":"0.00","car_id":25,"car_number":"川A11111","remark":"12rqwer","expect_complete_time":1538967660,"create_time":1538964099,"reception_user":"18780024125","username":"C","client_id":37,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":[]},{"repair_id":62,"order_amount":"0.00","car_id":25,"car_number":"川A11111","remark":"23123","expect_complete_time":1539048900,"create_time":1538962515,"reception_user":"18780024125","username":"C","client_id":37,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/''","goods_name":[]},{"repair_id":60,"order_amount":"20.00","car_id":31,"car_number":"川A55555","remark":"asdfasd","expect_complete_time":1538987400,"create_time":1538901014,"reception_user":"18780024125","username":"C","client_id":37,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png","goods_name":["A级保养"]},{"repair_id":59,"order_amount":"0.00","car_id":31,"car_number":"川A55555","remark":"asdfasd","expect_complete_time":1538987340,"create_time":1538900956,"reception_user":"18780024125","username":"C","client_id":37,"client_grade":10,"repair_state":70,"order_state":30,"car_photo":"http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png","goods_name":[]}]
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
             * repair_id : 81
             * order_amount : 220.00
             * car_id : 31
             * car_number : 川A55555
             * remark : 23rtgrfeewf
             * expect_complete_time : 1539224280
             * create_time : 1539051529
             * reception_user : 18780024125
             * username : C
             * client_id : 37
             * client_grade : 10
             * repair_state : 20
             * order_state : 10
             * car_photo : http://v3.taishifu.com.cn/store/data/upload//shop/store/05915508504613794.png
             * goods_name : ["A级保养","道达尔"]
             */

            private int repair_id;
            private String order_amount;
            private int car_id;
            private String car_number;
            private String remark;
            private int expect_complete_time;
            private int create_time;
            private String reception_user;
            private String username;
            private int client_id;
            private int client_grade;
            private int repair_state;
            private int order_state;
            private String car_photo;
            private List<String> goods_name;

            public int getRepair_id() {
                return repair_id;
            }

            public void setRepair_id(int repair_id) {
                this.repair_id = repair_id;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
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

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getExpect_complete_time() {
                return expect_complete_time;
            }

            public void setExpect_complete_time(int expect_complete_time) {
                this.expect_complete_time = expect_complete_time;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getReception_user() {
                return reception_user;
            }

            public void setReception_user(String reception_user) {
                this.reception_user = reception_user;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getClient_id() {
                return client_id;
            }

            public void setClient_id(int client_id) {
                this.client_id = client_id;
            }

            public int getClient_grade() {
                return client_grade;
            }

            public void setClient_grade(int client_grade) {
                this.client_grade = client_grade;
            }

            public int getRepair_state() {
                return repair_state;
            }

            public void setRepair_state(int repair_state) {
                this.repair_state = repair_state;
            }

            public int getOrder_state() {
                return order_state;
            }

            public void setOrder_state(int order_state) {
                this.order_state = order_state;
            }

            public String getCar_photo() {
                return car_photo;
            }

            public void setCar_photo(String car_photo) {
                this.car_photo = car_photo;
            }

            public List<String> getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(List<String> goods_name) {
                this.goods_name = goods_name;
            }
        }
    }
}
