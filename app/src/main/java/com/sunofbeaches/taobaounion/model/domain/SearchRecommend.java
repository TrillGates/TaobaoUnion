package com.sunofbeaches.taobaounion.model.domain;

import java.util.List;

public class SearchRecommend {


    /**
     * success : true
     * code : 10000
     * message : 获取成功
     * data : [{"id":"1218067349282762752","keyword":"毛衣","createTime":"2020-01-17 15:07"},{"id":"1218067383944491008","keyword":"面膜","createTime":"2020-01-17 15:07"},{"id":"1218067422200737792","keyword":"巧克力","createTime":"2020-01-17 15:07"},{"id":"1218067443742683136","keyword":"礼物","createTime":"2020-01-17 15:08"},{"id":"1218067465729224704","keyword":"情人节","createTime":"2020-01-17 15:08"},{"id":"1218067486180651008","keyword":"年货","createTime":"2020-01-17 15:08"},{"id":"1218067540752740352","keyword":"洁面乳","createTime":"2020-01-17 15:08"},{"id":"1218067581072584704","keyword":"祛螨虫","createTime":"2020-01-17 15:08"},{"id":"1218067637485973504","keyword":"零食","createTime":"2020-01-17 15:08"},{"id":"1218067755073286144","keyword":"良品铺子","createTime":"2020-01-17 15:09"}]
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
        /**
         * id : 1218067349282762752
         * keyword : 毛衣
         * createTime : 2020-01-17 15:07
         */

        private String id;
        private String keyword;
        private String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
