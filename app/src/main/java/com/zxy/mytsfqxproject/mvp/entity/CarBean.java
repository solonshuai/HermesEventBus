package com.zxy.mytsfqxproject.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CarBean {


    private int code;
    private String errmsg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Parcelable {
        /**
         * brand_id : 1
         * brand_name : 奥迪
         * brand_logo : http://v3.taishifu.com.cn/store/data/upload//shop/car_brand/car-brand-pic-1.jpg
         * brand_category : A
         */

        private int brand_id;
        private String brand_name;
        private String brand_logo;
        private String brand_category;

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_logo() {
            return brand_logo;
        }

        public void setBrand_logo(String brand_logo) {
            this.brand_logo = brand_logo;
        }

        public String getBrand_category() {
            return brand_category;
        }

        public void setBrand_category(String brand_category) {
            this.brand_category = brand_category;
        }

        protected ResultBean(Parcel in) {
            brand_id = in.readInt();
            brand_name = in.readString();
            brand_logo = in.readString();
            brand_category = in.readString();
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel in) {
                return new ResultBean(in);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(brand_id);
            dest.writeString(brand_name);
            dest.writeString(brand_logo);
            dest.writeString(brand_category);
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "brand_id=" + brand_id +
                    ", brand_name='" + brand_name + '\'' +
                    ", brand_logo='" + brand_logo + '\'' +
                    ", brand_category='" + brand_category + '\'' +
                    '}';
        }
    }
}
