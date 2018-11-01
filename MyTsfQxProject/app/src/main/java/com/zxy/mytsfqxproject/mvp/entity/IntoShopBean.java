package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class IntoShopBean {

    /**
     * code : 200
     * errmsg :
     * result : {"total_history":{"repair_count":7,"repair_amount":"491.00","client_id":37},"now_year":{"total_num":8,"total_amount":"234725.00"},"repair_list":[{"goods_list":["A级保养"],"order_amount":"20.00","create_time":1538900692,"repair_id":56},{"goods_list":null,"order_amount":"0.00","create_time":1538900880,"repair_id":58},{"goods_list":null,"order_amount":"0.00","create_time":1538900956,"repair_id":59},{"goods_list":["A级保养"],"order_amount":"20.00","create_time":1538901014,"repair_id":60},{"goods_list":["道达尔","美孚1号"],"order_amount":"369.00","create_time":1538968446,"repair_id":65},{"goods_list":["A级保养","道达尔"],"order_amount":"82.00","create_time":1539051529,"repair_id":81},{"goods_list":["A级保养","道达尔"],"order_amount":"234234.00","create_time":1539052941,"repair_id":87}]}
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
         * total_history : {"repair_count":7,"repair_amount":"491.00","client_id":37}
         * now_year : {"total_num":8,"total_amount":"234725.00"}
         * repair_list : [{"goods_list":["A级保养"],"order_amount":"20.00","create_time":1538900692,"repair_id":56},{"goods_list":null,"order_amount":"0.00","create_time":1538900880,"repair_id":58},{"goods_list":null,"order_amount":"0.00","create_time":1538900956,"repair_id":59},{"goods_list":["A级保养"],"order_amount":"20.00","create_time":1538901014,"repair_id":60},{"goods_list":["道达尔","美孚1号"],"order_amount":"369.00","create_time":1538968446,"repair_id":65},{"goods_list":["A级保养","道达尔"],"order_amount":"82.00","create_time":1539051529,"repair_id":81},{"goods_list":["A级保养","道达尔"],"order_amount":"234234.00","create_time":1539052941,"repair_id":87}]
         */

        private TotalHistoryBean total_history;
        private NowYearBean now_year;
        private List<RepairListBean> repair_list;

        public TotalHistoryBean getTotal_history() {
            return total_history;
        }

        public void setTotal_history(TotalHistoryBean total_history) {
            this.total_history = total_history;
        }

        public NowYearBean getNow_year() {
            return now_year;
        }

        public void setNow_year(NowYearBean now_year) {
            this.now_year = now_year;
        }

        public List<RepairListBean> getRepair_list() {
            return repair_list;
        }

        public void setRepair_list(List<RepairListBean> repair_list) {
            this.repair_list = repair_list;
        }

        public static class TotalHistoryBean {
            /**
             * repair_count : 7
             * repair_amount : 491.00
             * client_id : 37
             */

            private int repair_count;
            private String repair_amount;
            private int client_id;

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

            public int getClient_id() {
                return client_id;
            }

            public void setClient_id(int client_id) {
                this.client_id = client_id;
            }
        }

        public static class NowYearBean {
            /**
             * total_num : 8
             * total_amount : 234725.00
             */

            private int total_num;
            private String total_amount;

            public int getTotal_num() {
                return total_num;
            }

            public void setTotal_num(int total_num) {
                this.total_num = total_num;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }
        }

        public static class RepairListBean {
            /**
             * goods_list : ["A级保养"]
             * order_amount : 20.00
             * create_time : 1538900692
             * repair_id : 56
             */

            private String order_amount;
            private int create_time;
            private int repair_id;
            private List<String> goods_list;

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getRepair_id() {
                return repair_id;
            }

            public void setRepair_id(int repair_id) {
                this.repair_id = repair_id;
            }

            public List<String> getGoods_list() {
                return goods_list;
            }

            public void setGoods_list(List<String> goods_list) {
                this.goods_list = goods_list;
            }
        }
    }
}
