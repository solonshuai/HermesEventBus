package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class ClientCardBean {

    /**
     * code : 200
     * errmsg :
     * result : [{"mem_id":4,"card_title":"普通会员卡","money":"4000.00"}]
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
         * mem_id : 4
         * card_title : 普通会员卡
         * money : 4000.00
         */

        private int mem_id;
        private String card_title;
        private String money;
        private boolean isCheck = false;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getMem_id() {
            return mem_id;
        }

        public void setMem_id(int mem_id) {
            this.mem_id = mem_id;
        }

        public String getCard_title() {
            return card_title;
        }

        public void setCard_title(String card_title) {
            this.card_title = card_title;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
