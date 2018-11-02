package com.zxy.mytsfqxproject.mvp.entity;

public class SaveGoodMaterialBean {
    private int rg_id;
    private int stock_id;
    private int material_price;
    private int material_num;
    private int material_amount;

    public SaveGoodMaterialBean(int rg_id, int stock_id, int material_price, int material_num, int material_amount) {
        this.rg_id = rg_id;
        this.stock_id = stock_id;
        this.material_price = material_price;
        this.material_num = material_num;
        this.material_amount = material_amount;
    }

    public int getRg_id() {
        return rg_id;
    }

    public void setRg_id(int rg_id) {
        this.rg_id = rg_id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getMaterial_price() {
        return material_price;
    }

    public void setMaterial_price(int material_price) {
        this.material_price = material_price;
    }

    public int getMaterial_num() {
        return material_num;
    }

    public void setMaterial_num(int material_num) {
        this.material_num = material_num;
    }

    public int getMaterial_amount() {
        return material_amount;
    }

    public void setMaterial_amount(int material_amount) {
        this.material_amount = material_amount;
    }
}
