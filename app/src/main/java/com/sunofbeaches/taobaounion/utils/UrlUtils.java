package com.sunofbeaches.taobaounion.utils;

public class UrlUtils {
    public static String createHomePageUrl(int materialId,int page) {
        return "discovery/" + materialId + "/" + page;
    }

    public static String getCoverPath(String pict_url,int size) {
        return "https:" + pict_url + "_" + size + "x" + size + ".jpg";
    }
}
