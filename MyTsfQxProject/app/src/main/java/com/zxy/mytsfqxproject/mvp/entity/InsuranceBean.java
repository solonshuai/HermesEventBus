package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class InsuranceBean {

    /**
     * code : 200
     * errmsg :
     * result : [{"id":127,"name":"中国人保"},{"id":128,"name":"中国太保"},{"id":129,"name":"中国平安"},{"id":130,"name":"中国大地"},{"id":131,"name":"中华联合"},{"id":132,"name":"安邦保险"},{"id":133,"name":"都邦保险"},{"id":134,"name":"华泰保险"},{"id":135,"name":"天安保险"},{"id":136,"name":"永安保险"},{"id":137,"name":"浙商保险"},{"id":138,"name":"紫金保险"},{"id":139,"name":"民安保险"},{"id":140,"name":"中银保险"}]
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
         * id : 127
         * name : 中国人保
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
