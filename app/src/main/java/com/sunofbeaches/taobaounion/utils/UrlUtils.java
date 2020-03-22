package com.sunofbeaches.taobaounion.utils;

public class UrlUtils {
    public static String createHomePageUrl(int materialId,int page) {
        return "discovery/" + materialId + "/" + page;
    }

    public static String getCoverPath(String pict_url,int size) {
        return "https:" + pict_url + "_" + size + "x" + size + ".jpg";
    }

    public static String getCoverPath(String pict_url) {
        if(pict_url.startsWith("http") || pict_url.startsWith("https")) {
            return pict_url;
        } else {
            return "https:" + pict_url;
        }
    }

    public static String getTicketUrl(String url) {
        if(url.startsWith("http") || url.startsWith("https")) {
            return url;
        } else {
            return "https:" + url;
        }
    }

    public static String getSelectedPageContentUrl(int categoryId) {
        return "recommend/" + categoryId;
    }

    public static String getOnSellPageUrl(int currentPage) {
        return "onSell/" + currentPage;
    }
}
