package com.zxy.mytsfqxproject.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PeiJianBean {

    /**
     * code : 200
     * errmsg :
     * result : {"total":21,"per_page":15,"current_page":1,"last_page":2,"data":[{"stock_id":39,"pc_id":1132,"pc_pid":1078,"factory_code":"JYG","material_code":"JYG","material_name":"机油格","unit":"桶","sale_price":"600.00","stock":13},{"stock_id":36,"pc_id":78,"pc_pid":10,"factory_code":"","material_code":"54830-2E000","material_name":"前稳定杆球头","unit":"个","sale_price":"56.00","stock":0},{"stock_id":31,"pc_id":11,"pc_pid":0,"factory_code":"","material_code":"54830-2E000","material_name":"前稳定杆球头","unit":"个","sale_price":"66.00","stock":0},{"stock_id":28,"pc_id":20,"pc_pid":3,"factory_code":"DAS606150","material_code":"C121S52","material_name":"天合 制动片b","unit":"片","sale_price":"200.00","stock":0},{"stock_id":27,"pc_id":12,"pc_pid":11,"factory_code":"DAODA","material_code":"AC132D2","material_name":"道达尔润滑油1","unit":"桶","sale_price":"500.00","stock":4},{"stock_id":19,"pc_id":7,"pc_pid":3,"factory_code":null,"material_code":"asdc","material_name":"123","unit":"个","sale_price":"985.00","stock":0},{"stock_id":15,"pc_id":20,"pc_pid":3,"factory_code":null,"material_code":"YM11102","material_name":"气门及部件","unit":null,"sale_price":"100.00","stock":0},{"stock_id":14,"pc_id":20,"pc_pid":3,"factory_code":null,"material_code":"HYQ1201","material_name":"化油器","unit":null,"sale_price":"100.00","stock":0},{"stock_id":13,"pc_id":20,"pc_pid":3,"factory_code":null,"material_code":"HS11203","material_name":"活塞","unit":null,"sale_price":"102.00","stock":0},{"stock_id":12,"pc_id":17,"pc_pid":11,"factory_code":null,"material_code":"J12011","material_name":"节油器","unit":null,"sale_price":"330.00","stock":0},{"stock_id":11,"pc_id":7,"pc_pid":3,"factory_code":null,"material_code":"1231","material_name":"轮胎001","unit":null,"sale_price":"3.00","stock":0},{"stock_id":10,"pc_id":17,"pc_pid":11,"factory_code":null,"material_code":"DAODAER","material_name":"道达尔润滑油","unit":null,"sale_price":"100.00","stock":0},{"stock_id":9,"pc_id":18,"pc_pid":10,"factory_code":null,"material_code":"CSSP","material_name":"测试商品","unit":"件","sale_price":"1000.00","stock":0},{"stock_id":8,"pc_id":16,"pc_pid":11,"factory_code":null,"material_code":"SCP","material_name":"刹车片","unit":"片","sale_price":"500.00","stock":0},{"stock_id":7,"pc_id":12,"pc_pid":11,"factory_code":"","material_code":"czp123","material_name":"陈中培专用轮胎","unit":"个","sale_price":"12.00","stock":0}]}
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
         * total : 21
         * per_page : 15
         * current_page : 1
         * last_page : 2
         * data : [{"stock_id":39,"pc_id":1132,"pc_pid":1078,"factory_code":"JYG","material_code":"JYG","material_name":"机油格","unit":"桶","sale_price":"600.00","stock":13},{"stock_id":36,"pc_id":78,"pc_pid":10,"factory_code":"","material_code":"54830-2E000","material_name":"前稳定杆球头","unit":"个","sale_price":"56.00","stock":0},{"stock_id":31,"pc_id":11,"pc_pid":0,"factory_code":"","material_code":"54830-2E000","material_name":"前稳定杆球头","unit":"个","sale_price":"66.00","stock":0},{"stock_id":28,"pc_id":20,"pc_pid":3,"factory_code":"DAS606150","material_code":"C121S52","material_name":"天合 制动片b","unit":"片","sale_price":"200.00","stock":0},{"stock_id":27,"pc_id":12,"pc_pid":11,"factory_code":"DAODA","material_code":"AC132D2","material_name":"道达尔润滑油1","unit":"桶","sale_price":"500.00","stock":4},{"stock_id":19,"pc_id":7,"pc_pid":3,"factory_code":null,"material_code":"asdc","material_name":"123","unit":"个","sale_price":"985.00","stock":0},{"stock_id":15,"pc_id":20,"pc_pid":3,"factory_code":null,"material_code":"YM11102","material_name":"气门及部件","unit":null,"sale_price":"100.00","stock":0},{"stock_id":14,"pc_id":20,"pc_pid":3,"factory_code":null,"material_code":"HYQ1201","material_name":"化油器","unit":null,"sale_price":"100.00","stock":0},{"stock_id":13,"pc_id":20,"pc_pid":3,"factory_code":null,"material_code":"HS11203","material_name":"活塞","unit":null,"sale_price":"102.00","stock":0},{"stock_id":12,"pc_id":17,"pc_pid":11,"factory_code":null,"material_code":"J12011","material_name":"节油器","unit":null,"sale_price":"330.00","stock":0},{"stock_id":11,"pc_id":7,"pc_pid":3,"factory_code":null,"material_code":"1231","material_name":"轮胎001","unit":null,"sale_price":"3.00","stock":0},{"stock_id":10,"pc_id":17,"pc_pid":11,"factory_code":null,"material_code":"DAODAER","material_name":"道达尔润滑油","unit":null,"sale_price":"100.00","stock":0},{"stock_id":9,"pc_id":18,"pc_pid":10,"factory_code":null,"material_code":"CSSP","material_name":"测试商品","unit":"件","sale_price":"1000.00","stock":0},{"stock_id":8,"pc_id":16,"pc_pid":11,"factory_code":null,"material_code":"SCP","material_name":"刹车片","unit":"片","sale_price":"500.00","stock":0},{"stock_id":7,"pc_id":12,"pc_pid":11,"factory_code":"","material_code":"czp123","material_name":"陈中培专用轮胎","unit":"个","sale_price":"12.00","stock":0}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Parcelable {
            /**
             * stock_id : 39
             * sale_price : 600.00
             * project_num
             * pc_id : 1132
             * pc_pid : 1078
             * factory_code : JYG
             * material_code : JYG
             * material_name : 机油格
             * unit : 桶
             * stock : 13
             */

            private int stock_id;
            private int pc_id;
            private int pc_pid;
            private String factory_code;
            private String material_code;
            private String material_name;
            private String unit;
            private String sale_price;
            private int stock;
            private int material_num;
            private int material_amount;
            private int material_price;

            public int getStock_id() {
                return stock_id;
            }

            public void setStock_id(int stock_id) {
                this.stock_id = stock_id;
            }

            public int getPc_id() {
                return pc_id;
            }

            public void setPc_id(int pc_id) {
                this.pc_id = pc_id;
            }

            public int getPc_pid() {
                return pc_pid;
            }

            public void setPc_pid(int pc_pid) {
                this.pc_pid = pc_pid;
            }

            public String getFactory_code() {
                return factory_code;
            }

            public void setFactory_code(String factory_code) {
                this.factory_code = factory_code;
            }

            public String getMaterial_code() {
                return material_code;
            }

            public void setMaterial_code(String material_code) {
                this.material_code = material_code;
            }

            public String getMaterial_name() {
                return material_name;
            }

            public void setMaterial_name(String material_name) {
                this.material_name = material_name;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getSale_price() {
                return sale_price;
            }

            public void setSale_price(String sale_price) {
                this.sale_price = sale_price;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
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

            public int getMaterial_price() {
                return material_price;
            }

            public void setMaterial_price(int material_price) {
                this.material_price = material_price;
            }

            protected DataBean(Parcel in) {
                stock_id = in.readInt();
                pc_id = in.readInt();
                pc_pid = in.readInt();
                stock = in.readInt();
                material_num = in.readInt();
                material_amount = in.readInt();
                material_code = in.readString();
                material_name = in.readString();
                factory_code = in.readString();
                unit = in.readString();
                sale_price = in.readString();
                material_price = in.readInt();
            }

            public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel in) {
                    return new DataBean(in);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(stock_id);
                dest.writeInt(pc_id);
                dest.writeInt(pc_pid);
                dest.writeInt(stock);
                dest.writeInt(material_num);
                dest.writeInt(material_amount);
                dest.writeString(material_code);
                dest.writeString(material_name);
                dest.writeString(factory_code);
                dest.writeString(unit);
                dest.writeString(sale_price);
                dest.writeInt(material_price);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "stock_id=" + stock_id +
                        ", pc_id=" + pc_id +
                        ", pc_pid=" + pc_pid +
                        ", factory_code='" + factory_code + '\'' +
                        ", material_code='" + material_code + '\'' +
                        ", material_name='" + material_name + '\'' +
                        ", unit='" + unit + '\'' +
                        ", sale_price='" + sale_price + '\'' +
                        ", stock=" + stock +
                        ", material_num=" + material_num +
                        ", material_amount=" + material_amount +
                        ", material_price=" + material_price +
                        '}';
            }
        }
    }
}
