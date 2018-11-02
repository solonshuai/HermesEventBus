package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class OrdeNumBean {
    /**
     * code : 200
     * errmsg : 获取成功
     * result : [{"days":"2018-10-10","count":1},{"days":"2018-10-14","count":1}]
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
         * days : 2018-10-10
         * count : 1
         */

        private String days;
        private int count;

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
