package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

/**
 * 车辆修复信息
 */
public class RepairInfosBean {

    /**
     * code : 200
     * errmsg :
     * result : {"car_info":{"brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg","car_number":"川A2323S","car_series_name":"奥迪 奥迪A6L新能源","repair_count":6,"repair_amount":"2120.00","no_settlement_amount":"0.00"},"client_info":{"username":"张先生","phone":"15881111111","client_grade":10},"member_card":{"project_data":[]},"repair_info":{"repair_id":218,"repair_sn":"WX18101797505456","car_number":"川A2323S","brand_logo":"car-brand-pic-1.jpg","car_id":57,"car_series_name":"奥迪 奥迪A6L新能源","client_id":55,"username":"张先生","phone":"15881111111","send_name":"张先生","send_phone":"15881111111","car_vin":"","miles":0,"fuel":"","expect_complete_time":0,"fault":"","remark":"","reception_uid":1,"reception_user":"张毅杨","total_amount":"820.00","total_discount":"0.0","order_amount":"820.00","repair_state":50,"store_id":1,"create_time":1539760954,"bill_id":665},"project_data":[{"rg_id":481,"goods_type":10,"goods_id":13,"is_card":0,"card_item_id":0,"goods_oe":"","goods_code":"by","goods_name":"保养","goods_unit":"","goods_price":"380.00","goods_saleprice":"380.00","goods_num":1,"beyond_num":0,"goods_discount":0,"goods_amount":"380","total_amount":"380.00","sale_uid":0,"sale_user":"","builder_uid":0,"builder_user":"","remark":""}],"material_data":[{"rg_id":483,"goods_type":20,"goods_id":12,"is_card":0,"card_item_id":0,"goods_oe":"02","goods_code":"232151515","goods_name":"保险杠","goods_unit":"20","goods_price":"220.00","goods_saleprice":"220.00","goods_num":2,"beyond_num":0,"goods_discount":0,"goods_amount":"440","total_amount":"440.00","sale_uid":0,"sale_user":"","builder_uid":0,"builder_user":"","remark":"","received_num":2}],"pick_parts_data":[{"detail_id":1035,"warehouse_sn":"0","goods_id":12,"goods_oe":"02","goods_code":"232151515","goods_name":"保险杠","goods_unit":"20","goods_price":"220.00","goods_saleprice":"220.00","goods_num":2,"total_amount":"440.00","received_num":2,"goods_brand_name":"XSA","goods_product_area":"中国"}],"already_material":[{"stockoutin_id":379,"bill_type":2,"detail_id":585,"bill_batchno":"LNCKDH18101752100531","goods_stock_id":12,"goods_oe":"02","goods_code":"232151515","goods_name":"保险杠","goods_unit":"20","goods_price":"220.00","goods_saleprice":"220.00","goods_num":2}],"button_data":{"if_edit":false,"if_pick":false,"if_qc":false,"if_audit":false,"if_unaudit":true,"if_settlement":true,"if_unclear":false,"if_cancel":false,"if_pay":false,"if_over":false,"if_gopick":false},"payment":[]}
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
         * car_info : {"brand_logo":"http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg","car_number":"川A2323S","car_series_name":"奥迪 奥迪A6L新能源","repair_count":6,"repair_amount":"2120.00","no_settlement_amount":"0.00"}
         * client_info : {"username":"张先生","phone":"15881111111","client_grade":10}
         * member_card : {"project_data":[]}
         * repair_info : {"repair_id":218,"repair_sn":"WX18101797505456","car_number":"川A2323S","brand_logo":"car-brand-pic-1.jpg","car_id":57,"car_series_name":"奥迪 奥迪A6L新能源","client_id":55,"username":"张先生","phone":"15881111111","send_name":"张先生","send_phone":"15881111111","car_vin":"","miles":0,"fuel":"","expect_complete_time":0,"fault":"","remark":"","reception_uid":1,"reception_user":"张毅杨","total_amount":"820.00","total_discount":"0.0","order_amount":"820.00","repair_state":50,"store_id":1,"create_time":1539760954,"bill_id":665}
         * project_data : [{"rg_id":481,"goods_type":10,"goods_id":13,"is_card":0,"card_item_id":0,"goods_oe":"","goods_code":"by","goods_name":"保养","goods_unit":"","goods_price":"380.00","goods_saleprice":"380.00","goods_num":1,"beyond_num":0,"goods_discount":0,"goods_amount":"380","total_amount":"380.00","sale_uid":0,"sale_user":"","builder_uid":0,"builder_user":"","remark":""}]
         * material_data : [{"rg_id":483,"goods_type":20,"goods_id":12,"is_card":0,"card_item_id":0,"goods_oe":"02","goods_code":"232151515","goods_name":"保险杠","goods_unit":"20","goods_price":"220.00","goods_saleprice":"220.00","goods_num":2,"beyond_num":0,"goods_discount":0,"goods_amount":"440","total_amount":"440.00","sale_uid":0,"sale_user":"","builder_uid":0,"builder_user":"","remark":"","received_num":2}]
         * pick_parts_data : [{"detail_id":1035,"warehouse_sn":"0","goods_id":12,"goods_oe":"02","goods_code":"232151515","goods_name":"保险杠","goods_unit":"20","goods_price":"220.00","goods_saleprice":"220.00","goods_num":2,"total_amount":"440.00","received_num":2,"goods_brand_name":"XSA","goods_product_area":"中国"}]
         * already_material : [{"stockoutin_id":379,"bill_type":2,"detail_id":585,"bill_batchno":"LNCKDH18101752100531","goods_stock_id":12,"goods_oe":"02","goods_code":"232151515","goods_name":"保险杠","goods_unit":"20","goods_price":"220.00","goods_saleprice":"220.00","goods_num":2}]
         * button_data : {"if_edit":false,"if_pick":false,"if_qc":false,"if_audit":false,"if_unaudit":true,"if_settlement":true,"if_unclear":false,"if_cancel":false,"if_pay":false,"if_over":false,"if_gopick":false}
         * payment : []
         */

        private CarInfoBean car_info;
        private ClientInfoBean client_info;
        private MemberCardBean member_card;
        private RepairInfoBean repair_info;
        private ButtonDataBean button_data;
        private List<ProjectDataBean> project_data;
        private List<MaterialDataBean> material_data;
        private List<PickPartsDataBean> pick_parts_data;
        private List<AlreadyMaterialBean> already_material;
        private List<?> payment;

        public CarInfoBean getCar_info() {
            return car_info;
        }

        public void setCar_info(CarInfoBean car_info) {
            this.car_info = car_info;
        }

        public ClientInfoBean getClient_info() {
            return client_info;
        }

        public void setClient_info(ClientInfoBean client_info) {
            this.client_info = client_info;
        }

        public MemberCardBean getMember_card() {
            return member_card;
        }

        public void setMember_card(MemberCardBean member_card) {
            this.member_card = member_card;
        }

        public RepairInfoBean getRepair_info() {
            return repair_info;
        }

        public void setRepair_info(RepairInfoBean repair_info) {
            this.repair_info = repair_info;
        }

        public ButtonDataBean getButton_data() {
            return button_data;
        }

        public void setButton_data(ButtonDataBean button_data) {
            this.button_data = button_data;
        }

        public List<ProjectDataBean> getProject_data() {
            return project_data;
        }

        public void setProject_data(List<ProjectDataBean> project_data) {
            this.project_data = project_data;
        }

        public List<MaterialDataBean> getMaterial_data() {
            return material_data;
        }

        public void setMaterial_data(List<MaterialDataBean> material_data) {
            this.material_data = material_data;
        }

        public List<PickPartsDataBean> getPick_parts_data() {
            return pick_parts_data;
        }

        public void setPick_parts_data(List<PickPartsDataBean> pick_parts_data) {
            this.pick_parts_data = pick_parts_data;
        }

        public List<AlreadyMaterialBean> getAlready_material() {
            return already_material;
        }

        public void setAlready_material(List<AlreadyMaterialBean> already_material) {
            this.already_material = already_material;
        }

        public List<?> getPayment() {
            return payment;
        }

        public void setPayment(List<?> payment) {
            this.payment = payment;
        }

        public static class CarInfoBean {
            /**
             * brand_logo : http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg
             * car_number : 川A2323S
             * car_series_name : 奥迪 奥迪A6L新能源
             * repair_count : 6
             * repair_amount : 2120.00
             * no_settlement_amount : 0.00
             */

            private String brand_logo;
            private String car_number;
            private String car_series_name;
            private int repair_count;
            private String repair_amount;
            private String no_settlement_amount;

            public String getBrand_logo() {
                return brand_logo;
            }

            public void setBrand_logo(String brand_logo) {
                this.brand_logo = brand_logo;
            }

            public String getCar_number() {
                return car_number;
            }

            public void setCar_number(String car_number) {
                this.car_number = car_number;
            }

            public String getCar_series_name() {
                return car_series_name;
            }

            public void setCar_series_name(String car_series_name) {
                this.car_series_name = car_series_name;
            }

            public int getRepair_count() {
                return repair_count;
            }

            public void setRepair_count(int repair_count) {
                this.repair_count = repair_count;
            }

            public String getRepair_amount() {
                return repair_amount;
            }

            public void setRepair_amount(String repair_amount) {
                this.repair_amount = repair_amount;
            }

            public String getNo_settlement_amount() {
                return no_settlement_amount;
            }

            public void setNo_settlement_amount(String no_settlement_amount) {
                this.no_settlement_amount = no_settlement_amount;
            }
        }

        public static class ClientInfoBean {
            /**
             * username : 张先生
             * phone : 15881111111
             * client_grade : 10
             */

            private String username;
            private String phone;
            private int client_grade;

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

            public int getClient_grade() {
                return client_grade;
            }

            public void setClient_grade(int client_grade) {
                this.client_grade = client_grade;
            }
        }

        public static class MemberCardBean {
            private List<?> project_data;

            public List<?> getProject_data() {
                return project_data;
            }

            public void setProject_data(List<?> project_data) {
                this.project_data = project_data;
            }
        }

        public static class RepairInfoBean {
            /**
             * repair_id : 218
             * repair_sn : WX18101797505456
             * car_number : 川A2323S
             * brand_logo : car-brand-pic-1.jpg
             * car_id : 57
             * car_series_name : 奥迪 奥迪A6L新能源
             * client_id : 55
             * username : 张先生
             * phone : 15881111111
             * send_name : 张先生
             * send_phone : 15881111111
             * car_vin :
             * miles : 0
             * fuel :
             * expect_complete_time : 0
             * fault :
             * remark :
             * reception_uid : 1
             * reception_user : 张毅杨
             * total_amount : 820.00
             * total_discount : 0.0
             * order_amount : 820.00
             * repair_state : 50
             * store_id : 1
             * create_time : 1539760954
             * bill_id : 665
             */

            private int repair_id;
            private String repair_sn;
            private String car_number;
            private String brand_logo;
            private int car_id;
            private String car_series_name;
            private int client_id;
            private String username;
            private String phone;
            private String send_name;
            private String send_phone;
            private String car_vin;
            private int miles;
            private String fuel;
            private int expect_complete_time;
            private String fault;
            private String remark;
            private int reception_uid;
            private String reception_user;
            private String total_amount;
            private String total_discount;
            private String order_amount;
            private int repair_state;
            private int store_id;
            private int create_time;
            private int bill_id;

            public int getRepair_id() {
                return repair_id;
            }

            public void setRepair_id(int repair_id) {
                this.repair_id = repair_id;
            }

            public String getRepair_sn() {
                return repair_sn;
            }

            public void setRepair_sn(String repair_sn) {
                this.repair_sn = repair_sn;
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

            public int getCar_id() {
                return car_id;
            }

            public void setCar_id(int car_id) {
                this.car_id = car_id;
            }

            public String getCar_series_name() {
                return car_series_name;
            }

            public void setCar_series_name(String car_series_name) {
                this.car_series_name = car_series_name;
            }

            public int getClient_id() {
                return client_id;
            }

            public void setClient_id(int client_id) {
                this.client_id = client_id;
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

            public String getCar_vin() {
                return car_vin;
            }

            public void setCar_vin(String car_vin) {
                this.car_vin = car_vin;
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

            public int getExpect_complete_time() {
                return expect_complete_time;
            }

            public void setExpect_complete_time(int expect_complete_time) {
                this.expect_complete_time = expect_complete_time;
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

            public int getReception_uid() {
                return reception_uid;
            }

            public void setReception_uid(int reception_uid) {
                this.reception_uid = reception_uid;
            }

            public String getReception_user() {
                return reception_user;
            }

            public void setReception_user(String reception_user) {
                this.reception_user = reception_user;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getTotal_discount() {
                return total_discount;
            }

            public void setTotal_discount(String total_discount) {
                this.total_discount = total_discount;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public int getRepair_state() {
                return repair_state;
            }

            public void setRepair_state(int repair_state) {
                this.repair_state = repair_state;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getBill_id() {
                return bill_id;
            }

            public void setBill_id(int bill_id) {
                this.bill_id = bill_id;
            }
        }

        public static class ButtonDataBean {
            /**
             * if_edit : false
             * if_pick : false
             * if_qc : false
             * if_audit : false
             * if_unaudit : true
             * if_settlement : true
             * if_unclear : false
             * if_cancel : false
             * if_pay : false
             * if_over : false
             * if_gopick : false
             */

            private boolean if_edit;
            private boolean if_pick;
            private boolean if_qc;
            private boolean if_audit;
            private boolean if_unaudit;
            private boolean if_settlement;
            private boolean if_unclear;
            private boolean if_cancel;
            private boolean if_pay;
            private boolean if_over;
            private boolean if_gopick;

            public boolean isIf_edit() {
                return if_edit;
            }

            public void setIf_edit(boolean if_edit) {
                this.if_edit = if_edit;
            }

            public boolean isIf_pick() {
                return if_pick;
            }

            public void setIf_pick(boolean if_pick) {
                this.if_pick = if_pick;
            }

            public boolean isIf_qc() {
                return if_qc;
            }

            public void setIf_qc(boolean if_qc) {
                this.if_qc = if_qc;
            }

            public boolean isIf_audit() {
                return if_audit;
            }

            public void setIf_audit(boolean if_audit) {
                this.if_audit = if_audit;
            }

            public boolean isIf_unaudit() {
                return if_unaudit;
            }

            public void setIf_unaudit(boolean if_unaudit) {
                this.if_unaudit = if_unaudit;
            }

            public boolean isIf_settlement() {
                return if_settlement;
            }

            public void setIf_settlement(boolean if_settlement) {
                this.if_settlement = if_settlement;
            }

            public boolean isIf_unclear() {
                return if_unclear;
            }

            public void setIf_unclear(boolean if_unclear) {
                this.if_unclear = if_unclear;
            }

            public boolean isIf_cancel() {
                return if_cancel;
            }

            public void setIf_cancel(boolean if_cancel) {
                this.if_cancel = if_cancel;
            }

            public boolean isIf_pay() {
                return if_pay;
            }

            public void setIf_pay(boolean if_pay) {
                this.if_pay = if_pay;
            }

            public boolean isIf_over() {
                return if_over;
            }

            public void setIf_over(boolean if_over) {
                this.if_over = if_over;
            }

            public boolean isIf_gopick() {
                return if_gopick;
            }

            public void setIf_gopick(boolean if_gopick) {
                this.if_gopick = if_gopick;
            }
        }

        public static class ProjectDataBean {
            /**
             * rg_id : 481
             * goods_type : 10
             * goods_id : 13
             * is_card : 0
             * card_item_id : 0
             * goods_oe :
             * goods_code : by
             * goods_name : 保养
             * goods_unit :
             * goods_price : 380.00
             * goods_saleprice : 380.00
             * goods_num : 1
             * beyond_num : 0
             * goods_discount : 0
             * goods_amount : 380
             * total_amount : 380.00
             * sale_uid : 0
             * sale_user :
             * builder_uid : 0
             * builder_user :
             * remark :
             */

            private int rg_id;
            private int goods_type;
            private int goods_id;
            private int is_card;
            private int card_item_id;
            private String goods_oe;
            private String goods_code;
            private String goods_name;
            private String goods_unit;
            private String goods_price;
            private String goods_saleprice;
            private int goods_num;
            private int beyond_num;
            private float goods_discount;
            private String goods_amount;
            private String total_amount;
            private int sale_uid;
            private String sale_user;
            private int builder_uid;
            private String builder_user;
            private String remark;

            public int getRg_id() {
                return rg_id;
            }

            public void setRg_id(int rg_id) {
                this.rg_id = rg_id;
            }

            public int getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(int goods_type) {
                this.goods_type = goods_type;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getIs_card() {
                return is_card;
            }

            public void setIs_card(int is_card) {
                this.is_card = is_card;
            }

            public int getCard_item_id() {
                return card_item_id;
            }

            public void setCard_item_id(int card_item_id) {
                this.card_item_id = card_item_id;
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

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
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

            public int getBeyond_num() {
                return beyond_num;
            }

            public void setBeyond_num(int beyond_num) {
                this.beyond_num = beyond_num;
            }

            public float getGoods_discount() {
                return goods_discount;
            }

            public void setGoods_discount(float goods_discount) {
                this.goods_discount = goods_discount;
            }

            public String getGoods_amount() {
                return goods_amount;
            }

            public void setGoods_amount(String goods_amount) {
                this.goods_amount = goods_amount;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public int getSale_uid() {
                return sale_uid;
            }

            public void setSale_uid(int sale_uid) {
                this.sale_uid = sale_uid;
            }

            public String getSale_user() {
                return sale_user;
            }

            public void setSale_user(String sale_user) {
                this.sale_user = sale_user;
            }

            public int getBuilder_uid() {
                return builder_uid;
            }

            public void setBuilder_uid(int builder_uid) {
                this.builder_uid = builder_uid;
            }

            public String getBuilder_user() {
                return builder_user;
            }

            public void setBuilder_user(String builder_user) {
                this.builder_user = builder_user;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }

        public static class MaterialDataBean {
            /**
             * rg_id : 483
             * goods_type : 20
             * goods_id : 12
             * is_card : 0
             * card_item_id : 0
             * goods_oe : 02
             * goods_code : 232151515
             * goods_name : 保险杠
             * goods_unit : 20
             * goods_price : 220.00
             * goods_saleprice : 220.00
             * goods_num : 2
             * beyond_num : 0
             * goods_discount : 0
             * goods_amount : 440
             * total_amount : 440.00
             * sale_uid : 0
             * sale_user :
             * builder_uid : 0
             * builder_user :
             * remark :
             * received_num : 2
             */

            private int rg_id;
            private int goods_type;
            private int goods_id;
            private int is_card;
            private int card_item_id;
            private String goods_oe;
            private String goods_code;
            private String goods_name;
            private String goods_unit;
            private String goods_price;
            private String goods_saleprice;
            private int goods_num;
            private int beyond_num;
            private float goods_discount;
            private String goods_amount;
            private String total_amount;
            private int sale_uid;
            private String sale_user;
            private int builder_uid;
            private String builder_user;
            private String remark;
            private int received_num;

            public int getRg_id() {
                return rg_id;
            }

            public void setRg_id(int rg_id) {
                this.rg_id = rg_id;
            }

            public int getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(int goods_type) {
                this.goods_type = goods_type;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getIs_card() {
                return is_card;
            }

            public void setIs_card(int is_card) {
                this.is_card = is_card;
            }

            public int getCard_item_id() {
                return card_item_id;
            }

            public void setCard_item_id(int card_item_id) {
                this.card_item_id = card_item_id;
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

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
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

            public int getBeyond_num() {
                return beyond_num;
            }

            public void setBeyond_num(int beyond_num) {
                this.beyond_num = beyond_num;
            }

            public float getGoods_discount() {
                return goods_discount;
            }

            public void setGoods_discount(float goods_discount) {
                this.goods_discount = goods_discount;
            }

            public String getGoods_amount() {
                return goods_amount;
            }

            public void setGoods_amount(String goods_amount) {
                this.goods_amount = goods_amount;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public int getSale_uid() {
                return sale_uid;
            }

            public void setSale_uid(int sale_uid) {
                this.sale_uid = sale_uid;
            }

            public String getSale_user() {
                return sale_user;
            }

            public void setSale_user(String sale_user) {
                this.sale_user = sale_user;
            }

            public int getBuilder_uid() {
                return builder_uid;
            }

            public void setBuilder_uid(int builder_uid) {
                this.builder_uid = builder_uid;
            }

            public String getBuilder_user() {
                return builder_user;
            }

            public void setBuilder_user(String builder_user) {
                this.builder_user = builder_user;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getReceived_num() {
                return received_num;
            }

            public void setReceived_num(int received_num) {
                this.received_num = received_num;
            }
        }

        public static class PickPartsDataBean {
            /**
             * detail_id : 1035
             * warehouse_sn : 0
             * goods_id : 12
             * goods_oe : 02
             * goods_code : 232151515
             * goods_name : 保险杠
             * goods_unit : 20
             * goods_price : 220.00
             * goods_saleprice : 220.00
             * goods_num : 2
             * total_amount : 440.00
             * received_num : 2
             * goods_brand_name : XSA
             * goods_product_area : 中国
             */

            private int detail_id;
            private String warehouse_sn;
            private int goods_id;
            private String goods_oe;
            private String goods_code;
            private String goods_name;
            private String goods_unit;
            private String goods_price;
            private String goods_saleprice;
            private int goods_num;
            private String total_amount;
            private int received_num;
            private String goods_brand_name;
            private String goods_product_area;

            public int getDetail_id() {
                return detail_id;
            }

            public void setDetail_id(int detail_id) {
                this.detail_id = detail_id;
            }

            public String getWarehouse_sn() {
                return warehouse_sn;
            }

            public void setWarehouse_sn(String warehouse_sn) {
                this.warehouse_sn = warehouse_sn;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
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

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
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

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public int getReceived_num() {
                return received_num;
            }

            public void setReceived_num(int received_num) {
                this.received_num = received_num;
            }

            public String getGoods_brand_name() {
                return goods_brand_name;
            }

            public void setGoods_brand_name(String goods_brand_name) {
                this.goods_brand_name = goods_brand_name;
            }

            public String getGoods_product_area() {
                return goods_product_area;
            }

            public void setGoods_product_area(String goods_product_area) {
                this.goods_product_area = goods_product_area;
            }
        }

        public static class AlreadyMaterialBean {
            /**
             * stockoutin_id : 379
             * bill_type : 2
             * detail_id : 585
             * bill_batchno : LNCKDH18101752100531
             * goods_stock_id : 12
             * goods_oe : 02
             * goods_code : 232151515
             * goods_name : 保险杠
             * goods_unit : 20
             * goods_price : 220.00
             * goods_saleprice : 220.00
             * goods_num : 2
             */

            private int stockoutin_id;
            private int bill_type;
            private int detail_id;
            private String bill_batchno;
            private int goods_stock_id;
            private String goods_oe;
            private String goods_code;
            private String goods_name;
            private String goods_unit;
            private String goods_price;
            private String goods_saleprice;
            private int goods_num;

            public int getStockoutin_id() {
                return stockoutin_id;
            }

            public void setStockoutin_id(int stockoutin_id) {
                this.stockoutin_id = stockoutin_id;
            }

            public int getBill_type() {
                return bill_type;
            }

            public void setBill_type(int bill_type) {
                this.bill_type = bill_type;
            }

            public int getDetail_id() {
                return detail_id;
            }

            public void setDetail_id(int detail_id) {
                this.detail_id = detail_id;
            }

            public String getBill_batchno() {
                return bill_batchno;
            }

            public void setBill_batchno(String bill_batchno) {
                this.bill_batchno = bill_batchno;
            }

            public int getGoods_stock_id() {
                return goods_stock_id;
            }

            public void setGoods_stock_id(int goods_stock_id) {
                this.goods_stock_id = goods_stock_id;
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

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
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
        }
    }
}
