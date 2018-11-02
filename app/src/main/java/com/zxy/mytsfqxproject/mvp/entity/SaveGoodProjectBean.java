package com.zxy.mytsfqxproject.mvp.entity;

/**
 * 添加服务 保存--是给后台转实体类
 */
public class SaveGoodProjectBean {
    private int rg_id;
    private int project_id;
    private int project_price;
    private int project_num;
    private int project_amount;

    public SaveGoodProjectBean(int rg_id, int project_id, int project_price, int project_num, int project_amount) {
        this.rg_id = rg_id;
        this.project_id = project_id;
        this.project_price = project_price;
        this.project_num = project_num;
        this.project_amount = project_amount;
    }

    public int getRg_id() {
        return rg_id;
    }

    public void setRg_id(int rg_id) {
        this.rg_id = rg_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getProject_price() {
        return project_price;
    }

    public void setProject_price(int project_price) {
        this.project_price = project_price;
    }

    public int getProject_num() {
        return project_num;
    }

    public void setProject_num(int project_num) {
        this.project_num = project_num;
    }

    public int getProject_amount() {
        return project_amount;
    }

    public void setProject_amount(int project_amount) {
        this.project_amount = project_amount;
    }
}
