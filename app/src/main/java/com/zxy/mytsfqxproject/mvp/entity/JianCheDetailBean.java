package com.zxy.mytsfqxproject.mvp.entity;

public class JianCheDetailBean {

    /**
     * code : 200
     * errmsg :
     * result : {"repair":{"create_time":"2018-10-09 10:10:45","car_number":"蒙A66666","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg","miles":0,"fault":""},"verify":{"lacquer":"但还是","conditioner":"上海海事","engine":"啥时候","chassis":"不三不四","light":"我","road_test":"还真不少","lamplight":"叔叔","remark":"好像还是"}}
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
         * repair : {"create_time":"2018-10-09 10:10:45","car_number":"蒙A66666","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg","miles":0,"fault":""}
         * verify : {"lacquer":"但还是","conditioner":"上海海事","engine":"啥时候","chassis":"不三不四","light":"我","road_test":"还真不少","lamplight":"叔叔","remark":"好像还是"}
         */

        private RepairBean repair;
        private VerifyBean verify;

        public RepairBean getRepair() {
            return repair;
        }

        public void setRepair(RepairBean repair) {
            this.repair = repair;
        }

        public VerifyBean getVerify() {
            return verify;
        }

        public void setVerify(VerifyBean verify) {
            this.verify = verify;
        }

        public static class RepairBean {
            /**
             * create_time : 2018-10-09 10:10:45
             * car_number : 蒙A66666
             * brand_logo : http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg
             * miles : 0
             * fault :
             */

            private String create_time;
            private String car_number;
            private String brand_logo;
            private int miles;
            private String fault;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getCar_number() {
                return car_number;
            }

            public void setCar_number(String car_number) {
                this.car_number = car_number;
            }

            public String getBrand_logo() {
                return brand_logo;
            }

            public void setBrand_logo(String brand_logo) {
                this.brand_logo = brand_logo;
            }

            public int getMiles() {
                return miles;
            }

            public void setMiles(int miles) {
                this.miles = miles;
            }

            public String getFault() {
                return fault;
            }

            public void setFault(String fault) {
                this.fault = fault;
            }
        }

        public static class VerifyBean {
            /**
             * lacquer : 但还是
             * conditioner : 上海海事
             * engine : 啥时候
             * chassis : 不三不四
             * light : 我
             * road_test : 还真不少
             * lamplight : 叔叔
             * remark : 好像还是
             */

            private String lacquer;
            private String conditioner;
            private String engine;
            private String chassis;
            private String light;
            private String road_test;
            private String lamplight;
            private String remark;

            public String getLacquer() {
                return lacquer;
            }

            public void setLacquer(String lacquer) {
                this.lacquer = lacquer;
            }

            public String getConditioner() {
                return conditioner;
            }

            public void setConditioner(String conditioner) {
                this.conditioner = conditioner;
            }

            public String getEngine() {
                return engine;
            }

            public void setEngine(String engine) {
                this.engine = engine;
            }

            public String getChassis() {
                return chassis;
            }

            public void setChassis(String chassis) {
                this.chassis = chassis;
            }

            public String getLight() {
                return light;
            }

            public void setLight(String light) {
                this.light = light;
            }

            public String getRoad_test() {
                return road_test;
            }

            public void setRoad_test(String road_test) {
                this.road_test = road_test;
            }

            public String getLamplight() {
                return lamplight;
            }

            public void setLamplight(String lamplight) {
                this.lamplight = lamplight;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
