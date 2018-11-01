package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

/**
 * App配置信息
 */
public class ConfigurationInfor {

    /**
     * code : 200
     * errmsg :
     * result : {"tel":"028-62448100","start_img":"http://v3.taishifu.com.cn/store/data/upload//store/05888877796158241.png","about":"http://v3.taishifu.com.cn/index/app/about","help":"http://v3.taishifu.com.cn/index/app/help","repair_state":[{"state":10,"content":"检测中"},{"state":20,"content":"服务中"},{"state":60,"content":"待结账"},{"state":70,"content":"已提车"}],"clear_status":{"state":50,"content":"待结算"},"share":{"title":"钛师傅","link":"http://www.taishifu.com.cn/","remark":"钛师傅汽车维修、配件管理平台","imgurl":"http://v3.taishifu.com.cn/store/data/upload//store/05888877796158241.png"}}
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
         * tel : 028-62448100
         * start_img : http://v3.taishifu.com.cn/store/data/upload//store/05888877796158241.png
         * about : http://v3.taishifu.com.cn/index/app/about
         * help : http://v3.taishifu.com.cn/index/app/help
         * repair_state : [{"state":10,"content":"检测中"},{"state":20,"content":"服务中"},{"state":60,"content":"待结账"},{"state":70,"content":"已提车"}]
         * clear_status : {"state":50,"content":"待结算"}
         * share : {"title":"钛师傅","link":"http://www.taishifu.com.cn/","remark":"钛师傅汽车维修、配件管理平台","imgurl":"http://v3.taishifu.com.cn/store/data/upload//store/05888877796158241.png"}
         */

        private String tel;
        private String start_img;
        private String about;
        private String help;
        private ClearStatusBean clear_status;
        private ShareBean share;
        private List<RepairStateBean> repair_state;

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getStart_img() {
            return start_img;
        }

        public void setStart_img(String start_img) {
            this.start_img = start_img;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public String getHelp() {
            return help;
        }

        public void setHelp(String help) {
            this.help = help;
        }

        public ClearStatusBean getClear_status() {
            return clear_status;
        }

        public void setClear_status(ClearStatusBean clear_status) {
            this.clear_status = clear_status;
        }

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public List<RepairStateBean> getRepair_state() {
            return repair_state;
        }

        public void setRepair_state(List<RepairStateBean> repair_state) {
            this.repair_state = repair_state;
        }

        public static class ClearStatusBean {
            /**
             * state : 50
             * content : 待结算
             */

            private int state;
            private String content;

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class ShareBean {
            /**
             * title : 钛师傅
             * link : http://www.taishifu.com.cn/
             * remark : 钛师傅汽车维修、配件管理平台
             * imgurl : http://v3.taishifu.com.cn/store/data/upload//store/05888877796158241.png
             */

            private String title;
            private String link;
            private String remark;
            private String imgurl;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }
        }

        public static class RepairStateBean {
            /**
             * state : 10
             * content : 检测中
             */

            private int state;
            private String content;

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
