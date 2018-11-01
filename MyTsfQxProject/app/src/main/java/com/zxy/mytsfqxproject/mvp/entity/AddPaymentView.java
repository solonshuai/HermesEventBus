package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class AddPaymentView {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        private String pay_title;
        private String amount;
        private int pay_id;

        public ResultBean() {
        }
        public ResultBean(String pay_title, int pay_id) {
            this.pay_title = pay_title;
            this.pay_id = pay_id;
        }

        public String getPay_title() {
            return pay_title;
        }

        public void setPay_title(String pay_title) {
            this.pay_title = pay_title;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getPay_id() {
            return pay_id;
        }

        public void setPay_id(int pay_id) {
            this.pay_id = pay_id;
        }
    }
}
