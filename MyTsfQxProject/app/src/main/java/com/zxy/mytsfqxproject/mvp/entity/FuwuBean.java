package com.zxy.mytsfqxproject.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FuwuBean {


    /**
     * code : 200
     * errmsg :
     * result : {"total":2,"per_page":15,"current_page":1,"last_page":1,"data":[{"project_id":5,"pc_id":68,"pc_pid":26,"project_title":"内饰清洗","project_code":"NSQX","project_price":"600.00"},{"project_id":4,"pc_id":68,"pc_pid":26,"project_title":"全车改色","project_code":"QCGS","project_price":"5000.00"}]}
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
         * total : 2
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"project_id":5,"pc_id":68,"pc_pid":26,"project_title":"内饰清洗","project_code":"NSQX","project_price":"600.00"},{"project_id":4,"pc_id":68,"pc_pid":26,"project_title":"全车改色","project_code":"QCGS","project_price":"5000.00"}]
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
             * project_id : 5
             * project_price : 600.00
             * project_num
             * pc_id : 68
             * pc_pid : 26
             * project_title : 内饰清洗
             * project_code : NSQX
             */
            private int project_id;
            private int pc_id;
            private int pc_pid;
            private String project_title;
            private String project_code;
            private String project_price;
            private int project_num;
            private int project_amount;

            public int getProject_id() {
                return project_id;
            }

            public void setProject_id(int project_id) {
                this.project_id = project_id;
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

            public String getProject_title() {
                return project_title;
            }

            public void setProject_title(String project_title) {
                this.project_title = project_title;
            }

            public String getProject_code() {
                return project_code;
            }

            public void setProject_code(String project_code) {
                this.project_code = project_code;
            }

            public String getProject_price() {
                return project_price;
            }

            public void setProject_price(String project_price) {
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

            protected DataBean(Parcel in) {
                project_id = in.readInt();
                pc_id = in.readInt();
                pc_pid = in.readInt();
                project_num = in.readInt();
                project_amount = in.readInt();
                project_title = in.readString();
                project_code = in.readString();
                project_price = in.readString();

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
                dest.writeInt(project_id);
                dest.writeInt(pc_id);
                dest.writeInt(pc_pid);
                dest.writeInt(project_num);
                dest.writeInt(project_amount);
                dest.writeString(project_title);
                dest.writeString(project_code);
                dest.writeString(project_price);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "project_id=" + project_id +
                        ", pc_id=" + pc_id +
                        ", pc_pid=" + pc_pid +
                        ", project_title='" + project_title + '\'' +
                        ", project_code='" + project_code + '\'' +
                        ", project_price='" + project_price + '\'' +
                        ", project_num=" + project_num +
                        ", project_amount=" + project_amount +
                        '}';
            }
        }
    }
}
