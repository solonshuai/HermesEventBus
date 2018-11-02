package com.zxy.mytsfqxproject.mvp.entity;

import java.io.Serializable;
import java.util.List;

public class FwDetailBean implements Serializable{


    /**
     * code : 200
     * errmsg : 成功
     * result : {"repair_state":20,"repair_info":{"car_number":"蒙A66666","remark":"","reception_user":"13881790335","create_time":"2018-10-09 10:10:45","client_id":40,"car_id":35,"fault":"","miles":0,"phone":"13636365454"},"verify_car":{"verify_car_verdict":["但还是","上海海事","啥时候","不三不四","我","还真不少","叔叔","好像还是"],"verify_car_count":8,"qc_user":""},"goods_info":{"goodsNameList":["道达尔"],"goodsAmount":200},"goods_assign":{"goodsNotAssign":1,"goodsYesAssign":1},"repair_id":"85","total_amount":"200.00","verify_car_detail":{"lacquer":"但还是","conditioner":"上海海事","engine":"啥时候","chassis":"不三不四","light":"我","road_test":"还真不少","lamplight":"叔叔","remark":"好像还是"}}
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

    public static class ResultBean implements Serializable{
        /**
         * repair_state : 20
         * repair_info : {"car_number":"蒙A66666","remark":"","reception_user":"13881790335","create_time":"2018-10-09 10:10:45","client_id":40,"car_id":35,"fault":"","miles":0,"phone":"13636365454"}
         * verify_car : {"verify_car_verdict":["但还是","上海海事","啥时候","不三不四","我","还真不少","叔叔","好像还是"],"verify_car_count":8,"qc_user":""}
         * goods_info : {"goodsNameList":["道达尔"],"goodsAmount":200}
         * goods_assign : {"goodsNotAssign":1,"goodsYesAssign":1}
         * repair_id : 85
         * total_amount : 200.00
         * verify_car_detail : {"lacquer":"但还是","conditioner":"上海海事","engine":"啥时候","chassis":"不三不四","light":"我","road_test":"还真不少","lamplight":"叔叔","remark":"好像还是"}
         */

        private int repair_state;
        private RepairInfoBean repair_info;
        private VerifyCarBean verify_car;
        private GoodsInfoBean goods_info;
        private GoodsAssignBean goods_assign;
        private String repair_id;
        private String total_amount;
        private VerifyCarDetailBean verify_car_detail;

        public int getRepair_state() {
            return repair_state;
        }

        public void setRepair_state(int repair_state) {
            this.repair_state = repair_state;
        }

        public RepairInfoBean getRepair_info() {
            return repair_info;
        }

        public void setRepair_info(RepairInfoBean repair_info) {
            this.repair_info = repair_info;
        }

        public VerifyCarBean getVerify_car() {
            return verify_car;
        }

        public void setVerify_car(VerifyCarBean verify_car) {
            this.verify_car = verify_car;
        }

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public GoodsAssignBean getGoods_assign() {
            return goods_assign;
        }

        public void setGoods_assign(GoodsAssignBean goods_assign) {
            this.goods_assign = goods_assign;
        }

        public String getRepair_id() {
            return repair_id;
        }

        public void setRepair_id(String repair_id) {
            this.repair_id = repair_id;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public VerifyCarDetailBean getVerify_car_detail() {
            return verify_car_detail;
        }

        public void setVerify_car_detail(VerifyCarDetailBean verify_car_detail) {
            this.verify_car_detail = verify_car_detail;
        }

        public static class RepairInfoBean {
            /**
             * car_number : 蒙A66666
             * remark :
             * reception_user : 13881790335
             * create_time : 2018-10-09 10:10:45
             * client_id : 40
             * car_id : 35
             * fault :
             * miles : 0
             * phone : 13636365454
             */

            private String car_number;
            private String remark;
            private String reception_user;
            private String create_time;
            private int client_id;
            private int car_id;
            private String fault;
            private int miles;
            private String phone;

            public String getCar_number() {
                return car_number;
            }

            public void setCar_number(String car_number) {
                this.car_number = car_number;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getReception_user() {
                return reception_user;
            }

            public void setReception_user(String reception_user) {
                this.reception_user = reception_user;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getClient_id() {
                return client_id;
            }

            public void setClient_id(int client_id) {
                this.client_id = client_id;
            }

            public int getCar_id() {
                return car_id;
            }

            public void setCar_id(int car_id) {
                this.car_id = car_id;
            }

            public String getFault() {
                return fault;
            }

            public void setFault(String fault) {
                this.fault = fault;
            }

            public int getMiles() {
                return miles;
            }

            public void setMiles(int miles) {
                this.miles = miles;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

        public static class VerifyCarBean {
            /**
             * verify_car_verdict : ["但还是","上海海事","啥时候","不三不四","我","还真不少","叔叔","好像还是"]
             * verify_car_count : 8
             * qc_user :
             */

            private int verify_car_count;
            private String qc_user = null;
            private List<String> verify_car_verdict;

            public int getVerify_car_count() {
                return verify_car_count;
            }

            public void setVerify_car_count(int verify_car_count) {
                this.verify_car_count = verify_car_count;
            }

            public String getQc_user() {
                return qc_user;
            }

            public void setQc_user(String qc_user) {
                this.qc_user = qc_user;
            }

            public List<String> getVerify_car_verdict() {
                return verify_car_verdict;
            }

            public void setVerify_car_verdict(List<String> verify_car_verdict) {
                this.verify_car_verdict = verify_car_verdict;
            }
        }

        public static class GoodsInfoBean {
            /**
             * goodsNameList : ["道达尔"]
             * goodsAmount : 200
             */

            private int goodsAmount;
            private List<String> goodsNameList;

            public int getGoodsAmount() {
                return goodsAmount;
            }

            public void setGoodsAmount(int goodsAmount) {
                this.goodsAmount = goodsAmount;
            }

            public List<String> getGoodsNameList() {
                return goodsNameList;
            }

            public void setGoodsNameList(List<String> goodsNameList) {
                this.goodsNameList = goodsNameList;
            }
        }

        public static class GoodsAssignBean {
            /**
             * goodsNotAssign : 1
             * goodsYesAssign : 1
             */

            private int goodsNotAssign;
            private int goodsYesAssign;

            public int getGoodsNotAssign() {
                return goodsNotAssign;
            }

            public void setGoodsNotAssign(int goodsNotAssign) {
                this.goodsNotAssign = goodsNotAssign;
            }

            public int getGoodsYesAssign() {
                return goodsYesAssign;
            }

            public void setGoodsYesAssign(int goodsYesAssign) {
                this.goodsYesAssign = goodsYesAssign;
            }
        }

        public static class VerifyCarDetailBean implements Serializable {
            /**
             * lacquer : 但还是
             * conditioner : 上海海事
             * engine : 啥时候
             * chassis : 不三不四
             * road_test : 还真不少
             * light : 我
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
