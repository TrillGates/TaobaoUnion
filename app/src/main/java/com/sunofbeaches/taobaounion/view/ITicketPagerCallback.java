package com.sunofbeaches.taobaounion.view;

import com.sunofbeaches.taobaounion.base.IBaseCallback;
import com.sunofbeaches.taobaounion.model.domain.TicketResult;

public interface ITicketPagerCallback extends IBaseCallback {
    /**
     * 淘口令加载结果
     *
     * @param cover
     * @param result
     */
    void onTicketLoaded(String cover,TicketResult result);
}
