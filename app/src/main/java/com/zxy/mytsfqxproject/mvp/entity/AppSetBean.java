package com.zxy.mytsfqxproject.mvp.entity;

public class AppSetBean {

    /**
     * code : 200
     * errmsg :
     * result : {"ios":{"versionInfo":{"lastVersion":"1.0.1","getUrl":"","desc":"","isForce":true}},"android":{"versionInfo":{"lastVersion":"1.0.1","getUrl":"","desc":"","isForce":true}}}
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
         * ios : {"versionInfo":{"lastVersion":"1.0.1","getUrl":"","desc":"","isForce":true}}
         * android : {"versionInfo":{"lastVersion":"1.0.1","getUrl":"","desc":"","isForce":true}}
         */

        private IosBean ios;
        private AndroidBean android;

        public IosBean getIos() {
            return ios;
        }

        public void setIos(IosBean ios) {
            this.ios = ios;
        }

        public AndroidBean getAndroid() {
            return android;
        }

        public void setAndroid(AndroidBean android) {
            this.android = android;
        }

        public static class IosBean {
            /**
             * versionInfo : {"lastVersion":"1.0.1","getUrl":"","desc":"","isForce":true}
             */

            private VersionInfoBean versionInfo;

            public VersionInfoBean getVersionInfo() {
                return versionInfo;
            }

            public void setVersionInfo(VersionInfoBean versionInfo) {
                this.versionInfo = versionInfo;
            }

            public static class VersionInfoBean {
                /**
                 * lastVersion : 1.0.1
                 * getUrl :
                 * desc :
                 * isForce : true
                 */

                private String lastVersion;
                private String getUrl;
                private String desc;
                private boolean isForce;

                public String getLastVersion() {
                    return lastVersion;
                }

                public void setLastVersion(String lastVersion) {
                    this.lastVersion = lastVersion;
                }

                public String getGetUrl() {
                    return getUrl;
                }

                public void setGetUrl(String getUrl) {
                    this.getUrl = getUrl;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public boolean isIsForce() {
                    return isForce;
                }

                public void setIsForce(boolean isForce) {
                    this.isForce = isForce;
                }
            }
        }

        public static class AndroidBean {
            /**
             * versionInfo : {"lastVersion":"1.0.1","getUrl":"","desc":"","isForce":true}
             */

            private VersionInfoBeanX versionInfo;

            public VersionInfoBeanX getVersionInfo() {
                return versionInfo;
            }

            public void setVersionInfo(VersionInfoBeanX versionInfo) {
                this.versionInfo = versionInfo;
            }

            public static class VersionInfoBeanX {
                /**
                 * lastVersion : 1.0.1
                 * getUrl :
                 * desc :
                 * isForce : true
                 */

                private String lastVersion;
                private String getUrl;
                private String desc;
                private boolean isForce;

                public String getLastVersion() {
                    return lastVersion;
                }

                public void setLastVersion(String lastVersion) {
                    this.lastVersion = lastVersion;
                }

                public String getGetUrl() {
                    return getUrl;
                }

                public void setGetUrl(String getUrl) {
                    this.getUrl = getUrl;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public boolean isIsForce() {
                    return isForce;
                }

                public void setIsForce(boolean isForce) {
                    this.isForce = isForce;
                }
            }
        }
    }
}
