package com.sunofbeaches.taobaounion.model.domain;

import java.util.List;

public class SelectedPageCategory {
    @Override
    public String toString() {
        return "SelectedPageCategory{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * success : true
     * code : 10000
     * message : 获取精选分类成功.
     * data : [{"type":1,"favorites_id":19876595,"favorites_title":"程序员必备"},{"type":1,"favorites_id":19876636,"favorites_title":"办公室零食"},{"type":1,"favorites_id":19876637,"favorites_title":"上班族早餐"},{"type":1,"favorites_id":19876649,"favorites_title":"日用品"},{"type":1,"favorites_id":19902751,"favorites_title":"电脑周边"},{"type":1,"favorites_id":19903201,"favorites_title":"秋天必备"}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "type=" + type +
                    ", favorites_id=" + favorites_id +
                    ", favorites_title='" + favorites_title + '\'' +
                    '}';
        }

        /**
         * type : 1
         * favorites_id : 19876595
         * favorites_title : 程序员必备
         */

        private int type;
        private int favorites_id;
        private String favorites_title;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getFavorites_id() {
            return favorites_id;
        }

        public void setFavorites_id(int favorites_id) {
            this.favorites_id = favorites_id;
        }

        public String getFavorites_title() {
            return favorites_title;
        }

        public void setFavorites_title(String favorites_title) {
            this.favorites_title = favorites_title;
        }
    }
}
