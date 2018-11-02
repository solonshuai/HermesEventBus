package com.zxy.mytsfqxproject.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class SortModel implements Parcelable {
    private String name;
    private int brand_id;
    private String letters;//显示拼音的首字母
    private String imgs;

    public SortModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    private SortModel(Parcel in) {
        brand_id = in.readInt();
        name = in.readString();
        imgs = in.readString();
        letters = in.readString();
    }

    public static final Creator<SortModel> CREATOR = new Creator<SortModel>() {
        @Override
        public SortModel createFromParcel(Parcel in) {
            return new SortModel(in);
        }

        @Override
        public SortModel[] newArray(int size) {
            return new SortModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(brand_id);
        dest.writeString(name);
        dest.writeString(imgs);
        dest.writeString(letters);
    }

    @Override
    public String toString() {
        return "SortModel{" +
                "name='" + name + '\'' +
                ", brand_id=" + brand_id +
                ", letters='" + letters + '\'' +
                ", imgs='" + imgs + '\'' +
                '}';
    }
}
