package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

/**
 * 预约单的服务类别
 */
public class BespeakServiceBean {

    /**
     * code : 200
     * errmsg :
     * result : [{"id":6,"title":"机电维修"},{"id":7,"title":"电瓶更换"},{"id":36,"title":"标洗"},{"id":37,"title":"精洗"}]
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
         * id : 6
         * title : 机电维修
         */

        private int id;
        private String title;

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
    }
}
