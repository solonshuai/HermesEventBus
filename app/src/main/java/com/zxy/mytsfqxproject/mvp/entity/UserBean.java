package com.zxy.mytsfqxproject.mvp.entity;

import java.util.List;

public class UserBean  {
    /**
     * staff_id : 27
     * username : 18780024125
     * phone : 18780024125
     * store_id : 8
     * store_name : 钛师傅店
     * is_login_allow : 1
     * photo : http://v3.taishifu.com.cn/store/data/upload/staff/a1ba38c00ad247e863461e619b842425.png
     * acctoken : f4S7s4CGg2qEd45ih3t-YIu4vNKRZ82bgoZ8a4Cfr5WVmpenk3mIZpmhoaWeimFhk5PWmpKMr6g
     * first_guide : true
     * auth : [{"title":"车单记录","alias":"CARRECODE"},{"title":"财务管理","alias":"FINANCE"},{"title":"库存管理","alias":"STOCK"},{"title":"智能提醒","alias":"SMART"},{"title":"统计分析","alias":"ANALYZE"},{"title":"运营指标","alias":"TARGET"},{"title":"营销","alias":"MARKTEING"},{"title":"微信","alias":"WECHAR"},{"title":"客户管理","alias":"CLIENT"},{"title":"邀请同事","alias":"INVITE"},{"title":"接车单打印设置","alias":"JCDYDSZ"},{"title":"系统管理","alias":"SYSTEM"},{"title":"救援管理","alias":"SOS"},{"title":"车险业务","alias":"INSUARCE"},{"title":"连锁管理","alias":"CHAIN"},{"title":"指挥中心","alias":"COMMAND"},{"title":"消息推送","alias":"MESSAGE"},{"title":"接车单","alias":"REPAIR"},{"title":"预约单","alias":"BOOKING"},{"title":"施工单","alias":"WOEKBILL"},{"title":"应付款","alias":"RECEIPT"},{"title":"应收款","alias":"PAYMENT"},{"title":"现金流表","alias":"CASH"},{"title":"营业总表","alias":"BUSINESS"},{"title":"定金","alias":"DOWN"},{"title":"按日期查询营业统计","alias":"DATEBUSINES"},{"title":"销售目标","alias":"SALETARGET"},{"title":"其他收支","alias":"INCOME"},{"title":"营业对账","alias":"CONTRAST"},{"title":"接车单项目明细","alias":"REPAIRDETAIL"},{"title":"报销单","alias":"APPLY"},{"title":"绩效提成","alias":"DEDUCT"},{"title":"拉卡拉","alias":"LACARRA"},{"title":"库存概况","alias":"INVENTORY"},{"title":"库存总览","alias":"PANDECT"},{"title":"采购报表","alias":"PURCHASE"},{"title":"进销存统计报表","alias":"JXCTJBB"},{"title":"待采购清单查询","alias":"DCGQDCX"},{"title":"待入库清单查询","alias":"CRKQDCX"},{"title":"待领料清单查询","alias":"DLLQDCX"},{"title":"采购退货","alias":"CGTH"},{"title":"顾客退货","alias":"GKTH"},{"title":"库存盘点","alias":"KCPD"},{"title":"出入库记录","alias":"STOCKOUTIN"},{"title":"库存调拨","alias":"KCDB"},{"title":"库存调价","alias":"KCDJ"},{"title":"移库","alias":"YK"},{"title":"仓库设置","alias":"STORAGESET"},{"title":"提醒设置","alias":"REMARKSET"},{"title":"编辑提醒","alias":"EDITSET"},{"title":"配件销售毛利报表","alias":"PJXSMLBB"},{"title":"会员卡业务统计","alias":"MEMBER"},{"title":"今日看板","alias":"TODAYBOARD"},{"title":"客流量","alias":"GUESTFLOW"},{"title":"会员业务","alias":"MEMBERSERVER"},{"title":"客户量","alias":"CLIENTNUM"},{"title":"微信营销","alias":"WECHARMARKETING"},{"title":"定金余额","alias":"DJYE"},{"title":"分享活动","alias":"SHAREACTIVITY"},{"title":"关注奖励","alias":"ATTEITION"},{"title":"渠道分红","alias":"CHANNEL"},{"title":"股东分红","alias":"SHAREHOLDER"},{"title":"商城","alias":"MALL"},{"title":"微信活动","alias":"WECHARACTIVITY"},{"title":"微信总览","alias":"WECHARPANDECT"},{"title":"微信卡券","alias":"WECHARCARD"},{"title":"微信设置","alias":"WECHARSET"},{"title":"客户管理","alias":"CLIENTMANAGE"},{"title":"车辆管理","alias":"CATMANAGE"},{"title":"会员管理","alias":"MEMBERMANAGE"},{"title":"门店管理","alias":"STOREMANAGE"},{"title":"管辖门店组","alias":"GXMDZ"},{"title":"角色权限管理","alias":"ROLEAUTHMANAGE"},{"title":"部门管理","alias":"DEPARMENT"},{"title":"职位等级管理","alias":"JOBGRADEMANAGE"},{"title":"员工管理","alias":"STAFFMANAGE"},{"title":"服务管理","alias":"SERVERMANAGE"},{"title":"结帐方式设置","alias":"CLEARSET"},{"title":"检车项目设置","alias":"CARPROJECT"}]
     * rongCloud : lAK6QPvkl1Gy1750TgH71ECeLhEbilO/iQGXlKoou+r709yV/vdSaUPe1zibdOXSL0/jbe4rOm0o2Q9hg2IPtw==
     */

    private int staff_id;
    private String username;
    private String phone;
    private int store_id;
    private String store_name;
    private int is_login_allow;
    private String photo;
    private String acctoken;
    private boolean first_guide;
    private String rongCloud;
    private List<AuthBean> auth;

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

    public String getAcctoken() {
        return acctoken;
    }

    public void setAcctoken(String acctoken) {
        this.acctoken = acctoken;
    }

    public boolean isFirst_guide() {
        return first_guide;
    }

    public void setFirst_guide(boolean first_guide) {
        this.first_guide = first_guide;
    }

    public String getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(String rongCloud) {
        this.rongCloud = rongCloud;
    }

    public List<AuthBean> getAuth() {
        return auth;
    }

    public void setAuth(List<AuthBean> auth) {
        this.auth = auth;
    }

    public static class AuthBean  {
        /**
         * title : 车单记录
         * alias : CARRECODE
         */

        private String title;
        private String alias;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }
    }
}
