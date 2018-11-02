package com.zxy.mytsfqxproject.mvp.entity;

public class StaffInfoBean {

    /**
     * code : 200
     * errmsg :
     * result : {"staff_id":27,"username":"18780024125","phone":"18780024125","store_id":8,"store_name":"钛师傅店","is_login_allow":1,"photo":"http://v3.taishifu.com.cn/store/data/upload/staff/a1ba38c00ad247e863461e619b842425.png","company_name":"陈中培asdfasd","wx_qrcode":"http://v3.taishifu.com.cn/store/data/upload//store/store_qrcode.png"}
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
         * staff_id : 27
         * username : 18780024125
         * phone : 18780024125
         * store_id : 8
         * store_name : 钛师傅店
         * is_login_allow : 1
         * photo : http://v3.taishifu.com.cn/store/data/upload/staff/a1ba38c00ad247e863461e619b842425.png
         * company_name : 陈中培asdfasd
         * wx_qrcode : http://v3.taishifu.com.cn/store/data/upload//store/store_qrcode.png
         */

        private int staff_id;
        private String username;
        private String phone;
        private int store_id;
        private String store_name;
        private int is_login_allow;
        private String photo;
        private String company_name;
        private String wx_qrcode;

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
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

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public int getIs_login_allow() {
            return is_login_allow;
        }

        public void setIs_login_allow(int is_login_allow) {
            this.is_login_allow = is_login_allow;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getWx_qrcode() {
            return wx_qrcode;
        }

        public void setWx_qrcode(String wx_qrcode) {
            this.wx_qrcode = wx_qrcode;
        }
    }
}
