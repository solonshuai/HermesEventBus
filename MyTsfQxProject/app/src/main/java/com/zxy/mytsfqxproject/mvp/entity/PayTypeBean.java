package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class PayTypeBean {

    /**
     * code : 200
     * errmsg :
     * result : {"total":5,"per_page":10,"current_page":1,"last_page":1,"data":[{"id":6,"title":"现金","store_id":0,"payment_type":null},{"id":7,"title":"支付宝","store_id":0,"payment_type":null},{"id":8,"title":"微信","store_id":0,"payment_type":null}]}
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
         * total : 5
         * per_page : 10
         * current_page : 1
         * last_page : 1
         * data : [{"id":6,"title":"现金","store_id":0,"payment_type":null},{"id":7,"title":"支付宝","store_id":0,"payment_type":null},{"id":8,"title":"微信","store_id":0,"payment_type":null}]
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
             * id : 6
             * title : 现金
             * store_id : 0
             * payment_type : null
             */

            private int id;
            private String title;
            private int store_id;
            private Object payment_type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public Object getPayment_type() {
                return payment_type;
            }

            public void setPayment_type(Object payment_type) {
                this.payment_type = payment_type;
            }
        }
    }
}
