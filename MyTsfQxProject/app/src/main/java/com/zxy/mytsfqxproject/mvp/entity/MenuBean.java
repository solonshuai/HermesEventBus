package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class MenuBean {

    /**
     * code : 200
     * errmsg : 成功
     * result : [{"alias":"JDKD","id":1,"name":"接待开单","imgurl":"http://v3.taishifu.com.cn/store/data/upload//store/05876476510665379.png"},{"alias":"FWDD","id":2,"name":"服务订单","imgurl":"http://v3.taishifu.com.cn/store/data/upload//store/05876477400787343.png"},{"alias":"GCGL","id":3,"name":"过程管理","imgurl":"http://v3.taishifu.com.cn/store/data/upload//store/05876478367080058.png"},{"alias":"DDJS","id":4,"name":"订单结算","imgurl":"http://v3.taishifu.com.cn/store/data/upload//store/05876479032874451.png"}]
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
         * alias : JDKD
         * id : 1
         * name : 接待开单
         * imgurl : http://v3.taishifu.com.cn/store/data/upload//store/05876476510665379.png
         */

        private String alias;
        private int id;
        private String name;
        private String imgurl;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

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

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
