package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

/**
 * 获取支付详情页面
 */
public class RepairClearInfoBean {

    /**
     * code : 200
     * errmsg :
     * result : {"repair_info":{"repair_sn":"WX18101153545657","username":"ps","phone":"14725803691","car_id":37,"car_number":"川A12354","car_vin":"SHGGFG6575757GDD6","car_series_name":"奥迪 奥迪A6L新能源","miles":56,"fuel":"","send_name":"ps","send_phone":"14725803691","nature_type":"","fault":"","remark":"","total_amount":"220.00","order_amount":"220.00","total_discount":"0.0","repair_date":0,"reception_user":"无视","qc_user":"无视","create_time":1539253749,"car_nature":"","order_amount_upper":"贰佰贰拾圆"},"member_card":[],"clear_info":{"project_amount":"20.00","parts_amount":"200.00","clear_state":0,"clear_id":175,"create_time":1539253848},"parts_data":[{"goods_type":20,"goods_oe":"TOTAL","goods_code":"LT-56","goods_name":"道达尔","goods_unit":"只","goods_saleprice":"200.00","goods_num":1,"goods_discount":0,"total_amount":"200.00"}],"project_data":[{"goods_type":10,"goods_oe":"","goods_code":"AJBY","goods_name":"A级保养","goods_unit":"","goods_saleprice":"20.00","goods_num":1,"goods_discount":0,"total_amount":"20.00"}],"store_info":{"company_name":"陈中培asdfasd","store_address":"四川省成都市锦江区柳江街道南三环路三段158号东风风神及雷诺4S店","store_hotline":"028-5711540","bank_name":"asdfasd","bank_account":"12345678900000009"}}
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
         * repair_info : {"repair_sn":"WX18101153545657","username":"ps","phone":"14725803691","car_id":37,"car_number":"川A12354","car_vin":"SHGGFG6575757GDD6","car_series_name":"奥迪 奥迪A6L新能源","miles":56,"fuel":"","send_name":"ps","send_phone":"14725803691","nature_type":"","fault":"","remark":"","total_amount":"220.00","order_amount":"220.00","total_discount":"0.0","repair_date":0,"reception_user":"无视","qc_user":"无视","create_time":1539253749,"car_nature":"","order_amount_upper":"贰佰贰拾圆"}
         * member_card : []
         * clear_info : {"project_amount":"20.00","parts_amount":"200.00","clear_state":0,"clear_id":175,"create_time":1539253848}
         * parts_data : [{"goods_type":20,"goods_oe":"TOTAL","goods_code":"LT-56","goods_name":"道达尔","goods_unit":"只","goods_saleprice":"200.00","goods_num":1,"goods_discount":0,"total_amount":"200.00"}]
         * project_data : [{"goods_type":10,"goods_oe":"","goods_code":"AJBY","goods_name":"A级保养","goods_unit":"","goods_saleprice":"20.00","goods_num":1,"goods_discount":0,"total_amount":"20.00"}]
         * store_info : {"company_name":"陈中培asdfasd","store_address":"四川省成都市锦江区柳江街道南三环路三段158号东风风神及雷诺4S店","store_hotline":"028-5711540","bank_name":"asdfasd","bank_account":"12345678900000009"}
         */

        private RepairInfoBean repair_info;
        private ClearInfoBean clear_info;
        private StoreInfoBean store_info;
        private List<?> member_card;
        private List<PartsDataBean> parts_data;
        private List<ProjectDataBean> project_data;

        public RepairInfoBean getRepair_info() {
            return repair_info;
        }

        public void setRepair_info(RepairInfoBean repair_info) {
            this.repair_info = repair_info;
        }

        public ClearInfoBean getClear_info() {
            return clear_info;
        }

        public void setClear_info(ClearInfoBean clear_info) {
            this.clear_info = clear_info;
        }

        public StoreInfoBean getStore_info() {
            return store_info;
        }

        public void setStore_info(StoreInfoBean store_info) {
            this.store_info = store_info;
        }

        public List<?> getMember_card() {
            return member_card;
        }

        public void setMember_card(List<?> member_card) {
            this.member_card = member_card;
        }

        public List<PartsDataBean> getParts_data() {
            return parts_data;
        }

        public void setParts_data(List<PartsDataBean> parts_data) {
            this.parts_data = parts_data;
        }

        public List<ProjectDataBean> getProject_data() {
            return project_data;
        }

        public void setProject_data(List<ProjectDataBean> project_data) {
            this.project_data = project_data;
        }

        public static class RepairInfoBean {
            /**
             * repair_sn : WX18101153545657
             * username : ps
             * phone : 14725803691
             * car_id : 37
             * car_number : 川A12354
             * car_vin : SHGGFG6575757GDD6
             * car_series_name : 奥迪 奥迪A6L新能源
             * miles : 56
             * fuel :
             * send_name : ps
             * send_phone : 14725803691
             * nature_type :
             * fault :
             * remark :
             * total_amount : 220.00
             * order_amount : 220.00
             * total_discount : 0.0
             * repair_date : 0
             * reception_user : 无视
             * qc_user : 无视
             * create_time : 1539253749
             * car_nature :
             * order_amount_upper : 贰佰贰拾圆
             */

            private String repair_sn;
            private String username;
            private String phone;
            private int car_id;
            private String car_number;
            private String car_vin;
            private String car_series_name;
            private int miles;
            private String fuel;
            private String send_name;
            private String send_phone;
            private String nature_type;
            private String fault;
            private String remark;
            private String total_amount;
            private String order_amount;
            private String total_discount;
            private int repair_date;
            private String reception_user;
            private String qc_user;
            private int create_time;
            private String car_nature;
            private String order_amount_upper;

            public String getRepair_sn() {
                return repair_sn;
            }

            public void setRepair_sn(String repair_sn) {
                this.repair_sn = repair_sn;
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

            public int getCar_id() {
                return car_id;
            }

            public void setCar_id(int car_id) {
                this.car_id = car_id;
            }

            public String getCar_number() {
                return car_number;
            }

            public void setCar_number(String car_number) {
                this.car_number = car_number;
            }

            public String getCar_vin() {
                return car_vin;
            }

            public void setCar_vin(String car_vin) {
                this.car_vin = car_vin;
            }

            public String getCar_series_name() {
                return car_series_name;
            }

            public void setCar_series_name(String car_series_name) {
                this.car_series_name = car_series_name;
            }

            public int getMiles() {
                return miles;
            }

            public void setMiles(int miles) {
                this.miles = miles;
            }

            public String getFuel() {
                return fuel;
            }

            public void setFuel(String fuel) {
                this.fuel = fuel;
            }

            public String getSend_name() {
                return send_name;
            }

            public void setSend_name(String send_name) {
                this.send_name = send_name;
            }

            public String getSend_phone() {
                return send_phone;
            }

            public void setSend_phone(String send_phone) {
                this.send_phone = send_phone;
            }

            public String getNature_type() {
                return nature_type;
            }

            public void setNature_type(String nature_type) {
                this.nature_type = nature_type;
            }

            public String getFault() {
                return fault;
            }

            public void setFault(String fault) {
                this.fault = fault;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getTotal_discount() {
                return total_discount;
            }

            public void setTotal_discount(String total_discount) {
                this.total_discount = total_discount;
            }

            public int getRepair_date() {
                return repair_date;
            }

            public void setRepair_date(int repair_date) {
                this.repair_date = repair_date;
            }

            public String getReception_user() {
                return reception_user;
            }

            public void setReception_user(String reception_user) {
                this.reception_user = reception_user;
            }

            public String getQc_user() {
                return qc_user;
            }

            public void setQc_user(String qc_user) {
                this.qc_user = qc_user;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getCar_nature() {
                return car_nature;
            }

            public void setCar_nature(String car_nature) {
                this.car_nature = car_nature;
            }

            public String getOrder_amount_upper() {
                return order_amount_upper;
            }

            public void setOrder_amount_upper(String order_amount_upper) {
                this.order_amount_upper = order_amount_upper;
            }
        }

        public static class ClearInfoBean {
            /**
             * project_amount : 20.00
             * parts_amount : 200.00
             * clear_state : 0
             * clear_id : 175
             * create_time : 1539253848
             */

            private String project_amount;
            private String parts_amount;
            private int clear_state;
            private int clear_id;
            private int create_time;

            public String getProject_amount() {
                return project_amount;
            }

            public void setProject_amount(String project_amount) {
                this.project_amount = project_amount;
            }

            public String getParts_amount() {
                return parts_amount;
            }

            public void setParts_amount(String parts_amount) {
                this.parts_amount = parts_amount;
            }

            public int getClear_state() {
                return clear_state;
            }

            public void setClear_state(int clear_state) {
                this.clear_state = clear_state;
            }

            public int getClear_id() {
                return clear_id;
            }

            public void setClear_id(int clear_id) {
                this.clear_id = clear_id;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }
        }

        public static class StoreInfoBean {
            /**
             * company_name : 陈中培asdfasd
             * store_address : 四川省成都市锦江区柳江街道南三环路三段158号东风风神及雷诺4S店
             * store_hotline : 028-5711540
             * bank_name : asdfasd
             * bank_account : 12345678900000009
             */

            private String company_name;
            private String store_address;
            private String store_hotline;
            private String bank_name;
            private String bank_account;

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getStore_address() {
                return store_address;
            }

            public void setStore_address(String store_address) {
                this.store_address = store_address;
            }

            public String getStore_hotline() {
                return store_hotline;
            }

            public void setStore_hotline(String store_hotline) {
                this.store_hotline = store_hotline;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getBank_account() {
                return bank_account;
            }

            public void setBank_account(String bank_account) {
                this.bank_account = bank_account;
            }
        }

        public static class PartsDataBean {
            /**
             * goods_type : 20
             * goods_oe : TOTAL
             * goods_code : LT-56
             * goods_name : 道达尔
             * goods_unit : 只
             * goods_saleprice : 200.00
             * goods_num : 1
             * goods_discount : 0
             * total_amount : 200.00
             */

            private int goods_type;
            private String goods_oe;
            private String goods_code;
            private String goods_name;
            private String goods_unit;
            private String goods_saleprice;
            private int goods_num;
            private int goods_discount;
            private String total_amount;

            public int getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(int goods_type) {
                this.goods_type = goods_type;
            }

            public String getGoods_oe() {
                return goods_oe;
            }

            public void setGoods_oe(String goods_oe) {
                this.goods_oe = goods_oe;
            }

            public String getGoods_code() {
                return goods_code;
            }

            public void setGoods_code(String goods_code) {
                this.goods_code = goods_code;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_unit() {
                return goods_unit;
            }

            public void setGoods_unit(String goods_unit) {
                this.goods_unit = goods_unit;
            }

            public String getGoods_saleprice() {
                return goods_saleprice;
            }

            public void setGoods_saleprice(String goods_saleprice) {
                this.goods_saleprice = goods_saleprice;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public int getGoods_discount() {
                return goods_discount;
            }

            public void setGoods_discount(int goods_discount) {
                this.goods_discount = goods_discount;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }
        }

        public static class ProjectDataBean {
            /**
             * goods_type : 10
             * goods_oe :
             * goods_code : AJBY
             * goods_name : A级保养
             * goods_unit :
             * goods_saleprice : 20.00
             * goods_num : 1
             * goods_discount : 0
             * total_amount : 20.00
             */

            private int goods_type;
            private String goods_oe;
            private String goods_code;
            private String goods_name;
            private String goods_unit;
            private String goods_saleprice;
            private int goods_num;
            private int goods_discount;
            private String total_amount;

            public int getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(int goods_type) {
                this.goods_type = goods_type;
            }

            public String getGoods_oe() {
                return goods_oe;
            }

            public void setGoods_oe(String goods_oe) {
                this.goods_oe = goods_oe;
            }

            public String getGoods_code() {
                return goods_code;
            }

            public void setGoods_code(String goods_code) {
                this.goods_code = goods_code;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_unit() {
                return goods_unit;
            }

            public void setGoods_unit(String goods_unit) {
                this.goods_unit = goods_unit;
            }

            public String getGoods_saleprice() {
                return goods_saleprice;
            }

            public void setGoods_saleprice(String goods_saleprice) {
                this.goods_saleprice = goods_saleprice;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public int getGoods_discount() {
                return goods_discount;
            }

            public void setGoods_discount(int goods_discount) {
                this.goods_discount = goods_discount;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }
        }
    }
}
