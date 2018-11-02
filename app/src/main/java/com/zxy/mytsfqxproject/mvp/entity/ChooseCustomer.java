package com.zxy.mytsfqxproject.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ChooseCustomer {

    /**
     * code : 200
     * errmsg : 客户列表
     * result : {"total":25,"per_page":15,"current_page":1,"last_page":2,"data":[{"is_member":0,"username":"12wasdfasd","client_id":136,"client_type":10,"phone":"18780097890","sex":"男","birthday":"","client_grade":30,"client_from":10,"address":null,"create_time":1535609722,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"BM","client_id":177,"client_type":10,"phone":"18780029876","sex":"男","birthday":"2018-12-12","client_grade":10,"client_from":0,"address":"","create_time":1536836074,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"GG","client_id":189,"client_type":10,"phone":"18700000000","sex":"男","birthday":"2018-12-12","client_grade":10,"client_from":0,"address":"","create_time":1537931685,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"HaroAnna","client_id":137,"client_type":10,"phone":"5555228243","sex":"男","birthday":"","client_grade":30,"client_from":10,"address":null,"create_time":1535611420,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"HH","client_id":127,"client_type":10,"phone":"18711111111","sex":"男","birthday":"2018-09-26","client_grade":10,"client_from":10,"address":"四川 成都市 武侯区","create_time":1537932215,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"MJ","client_id":187,"client_type":10,"phone":"18780092312","sex":"男","birthday":"2018-12-12","client_grade":10,"client_from":0,"address":"","create_time":1537860958,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"WWWW","client_id":134,"client_type":20,"phone":"18780099090","sex":"男","birthday":"","client_grade":null,"client_from":0,"address":"","create_time":1535528897,"last_repair_date":"","client_code":"单位","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"带挨说杜","client_id":126,"client_type":10,"phone":"18382366969","sex":"男","birthday":"","client_grade":0,"client_from":0,"address":"","create_time":0,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"李晨","client_id":150,"client_type":10,"phone":"18888888888","sex":"男","birthday":"2018-09-11","client_grade":10,"client_from":10,"address":"四川 成都市 武侯区","create_time":1536654785,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"问问我我","client_id":100,"client_type":10,"phone":"18844445555","sex":"男","birthday":"2018-08-17","client_grade":10,"client_from":10,"address":"四川 成都市 武侯区","create_time":1534498080,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"无所谓","client_id":182,"client_type":10,"phone":"18552264545","sex":"男","birthday":"2000-10-10","client_grade":10,"client_from":0,"address":"","create_time":1537859014,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"无所谓","client_id":133,"client_type":10,"phone":"18356566565","sex":"男","birthday":"","client_grade":10,"client_from":0,"address":"","create_time":1535525106,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"无所谓","client_id":170,"client_type":10,"phone":"18552264545","sex":"男","birthday":"2000-10-10","client_grade":10,"client_from":0,"address":"","create_time":0,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"无所谓","client_id":172,"client_type":10,"phone":"18552264545","sex":"男","birthday":"2000-10-10","client_grade":10,"client_from":0,"address":"","create_time":1536830516,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"无所谓","client_id":173,"client_type":10,"phone":"18552264545","sex":"男","birthday":"2000-10-10","client_grade":10,"client_from":0,"address":"","create_time":1536830619,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null}]}
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
         * total : 25
         * per_page : 15
         * current_page : 1
         * last_page : 2
         * data : [{"is_member":0,"username":"12wasdfasd","client_id":136,"client_type":10,"phone":"18780097890","sex":"男","birthday":"","client_grade":30,"client_from":10,"address":null,"create_time":1535609722,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"BM","client_id":177,"client_type":10,"phone":"18780029876","sex":"男","birthday":"2018-12-12","client_grade":10,"client_from":0,"address":"","create_time":1536836074,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"GG","client_id":189,"client_type":10,"phone":"18700000000","sex":"男","birthday":"2018-12-12","client_grade":10,"client_from":0,"address":"","create_time":1537931685,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"HaroAnna","client_id":137,"client_type":10,"phone":"5555228243","sex":"男","birthday":"","client_grade":30,"client_from":10,"address":null,"create_time":1535611420,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"HH","client_id":127,"client_type":10,"phone":"18711111111","sex":"男","birthday":"2018-09-26","client_grade":10,"client_from":10,"address":"四川 成都市 武侯区","create_time":1537932215,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"MJ","client_id":187,"client_type":10,"phone":"18780092312","sex":"男","birthday":"2018-12-12","client_grade":10,"client_from":0,"address":"","create_time":1537860958,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"WWWW","client_id":134,"client_type":20,"phone":"18780099090","sex":"男","birthday":"","client_grade":null,"client_from":0,"address":"","create_time":1535528897,"last_repair_date":"","client_code":"单位","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"带挨说杜","client_id":126,"client_type":10,"phone":"18382366969","sex":"男","birthday":"","client_grade":0,"client_from":0,"address":"","create_time":0,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"李晨","client_id":150,"client_type":10,"phone":"18888888888","sex":"男","birthday":"2018-09-11","client_grade":10,"client_from":10,"address":"四川 成都市 武侯区","create_time":1536654785,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"问问我我","client_id":100,"client_type":10,"phone":"18844445555","sex":"男","birthday":"2018-08-17","client_grade":10,"client_from":10,"address":"四川 成都市 武侯区","create_time":1534498080,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"无所谓","client_id":182,"client_type":10,"phone":"18552264545","sex":"男","birthday":"2000-10-10","client_grade":10,"client_from":0,"address":"","create_time":1537859014,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"无所谓","client_id":133,"client_type":10,"phone":"18356566565","sex":"男","birthday":"","client_grade":10,"client_from":0,"address":"","create_time":1535525106,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"无所谓","client_id":170,"client_type":10,"phone":"18552264545","sex":"男","birthday":"2000-10-10","client_grade":10,"client_from":0,"address":"","create_time":0,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"无所谓","client_id":172,"client_type":10,"phone":"18552264545","sex":"男","birthday":"2000-10-10","client_grade":10,"client_from":0,"address":"","create_time":1536830516,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null},{"is_member":0,"username":"无所谓","client_id":173,"client_type":10,"phone":"18552264545","sex":"男","birthday":"2000-10-10","client_grade":10,"client_from":0,"address":"","create_time":1536830619,"last_repair_date":"","client_code":"个人","car_number":"","brand_logo":"http://v3.taishifu.com.cn/store/data/upload//store/","repair_amount":null}]
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
             * is_member : 0
             * username : 12wasdfasd
             * client_id : 136
             * client_type : 10
             * phone : 18780097890
             * sex : 男
             * birthday :
             * client_grade : 30
             * client_from : 10
             * address : null
             * create_time : 1535609722
             * last_repair_date :
             * client_code : 个人
             * car_number :
             * brand_logo : http://v3.taishifu.com.cn/store/data/upload//store/
             * repair_amount : null
             */

            private int is_member;
            private String username;
            private int client_id;
            private int client_type;
            private String phone;
            private String sex;
            private String birthday;
            private int client_grade;
            private int client_from;
            private String address;
            private int create_time;
            private String last_repair_date;
            private String client_code;
            private String car_number;
            private String brand_logo;
            private Object repair_amount;

            public int getIs_member() {
                return is_member;
            }

            public void setIs_member(int is_member) {
                this.is_member = is_member;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getClient_id() {
                return client_id;
            }

            public void setClient_id(int client_id) {
                this.client_id = client_id;
            }

            public int getClient_type() {
                return client_type;
            }

            public void setClient_type(int client_type) {
                this.client_type = client_type;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public int getClient_grade() {
                return client_grade;
            }

            public void setClient_grade(int client_grade) {
                this.client_grade = client_grade;
            }

            public int getClient_from() {
                return client_from;
            }

            public void setClient_from(int client_from) {
                this.client_from = client_from;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getLast_repair_date() {
                return last_repair_date;
            }

            public void setLast_repair_date(String last_repair_date) {
                this.last_repair_date = last_repair_date;
            }

            public String getClient_code() {
                return client_code;
            }

            public void setClient_code(String client_code) {
                this.client_code = client_code;
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

            public Object getRepair_amount() {
                return repair_amount;
            }

            public void setRepair_amount(Object repair_amount) {
                this.repair_amount = repair_amount;
            }

            protected DataBean(Parcel in) {
                is_member = in.readInt();
                username = in.readString();
                client_id = in.readInt();
                client_type = in.readInt();
                phone = in.readString();
                sex = in.readString();
                birthday = in.readString();
                client_grade = in.readInt();
                client_from = in.readInt();
                address = in.readString();
                create_time = in.readInt();
                last_repair_date = in.readString();
                client_code = in.readString();
                car_number = in.readString();
                brand_logo = in.readString();
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
                dest.writeInt(is_member);
                dest.writeString(username);
                dest.writeInt(client_id);
                dest.writeInt(client_type);
                dest.writeString(phone);
                dest.writeString(sex);
                dest.writeString(birthday);
                dest.writeInt(client_grade);
                dest.writeInt(client_from);
                dest.writeInt(create_time);
                dest.writeString(last_repair_date);
                dest.writeString(client_code);
                dest.writeString(car_number);
                dest.writeString(brand_logo);
                dest.writeString(address);
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "is_member=" + is_member +
                        ", username='" + username + '\'' +
                        ", client_id=" + client_id +
                        ", client_type=" + client_type +
                        ", phone='" + phone + '\'' +
                        ", sex='" + sex + '\'' +
                        ", birthday='" + birthday + '\'' +
                        ", client_grade=" + client_grade +
                        ", client_from=" + client_from +
                        ", address=" + address +
                        ", create_time=" + create_time +
                        ", last_repair_date='" + last_repair_date + '\'' +
                        ", client_code='" + client_code + '\'' +
                        ", car_number='" + car_number + '\'' +
                        ", brand_logo='" + brand_logo +
                        '}';
            }

            @Override
            public int describeContents() {
                return 0;
            }
        }
    }
}
