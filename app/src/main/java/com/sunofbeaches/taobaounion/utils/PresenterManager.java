package com.sunofbeaches.taobaounion.utils;

import com.sunofbeaches.taobaounion.presenter.ICategoryPagerPresenter;
import com.sunofbeaches.taobaounion.presenter.IHomePresenter;
import com.sunofbeaches.taobaounion.presenter.ISelectedPagePresenter;
import com.sunofbeaches.taobaounion.presenter.ITicketPresenter;
import com.sunofbeaches.taobaounion.presenter.impl.CategoryPagePresenterImpl;
import com.sunofbeaches.taobaounion.presenter.impl.HomePresenterImpl;
import com.sunofbeaches.taobaounion.presenter.impl.SelectedPagePresenterImpl;
import com.sunofbeaches.taobaounion.presenter.impl.TicketPresenterImpl;

public class PresenterManager {
    private static final PresenterManager ourInstance = new PresenterManager();
    private final ICategoryPagerPresenter mCategoryPagePresenter;
    private final IHomePresenter mHomePresenter;
    private final ITicketPresenter mTicketPresenter;
    private final ISelectedPagePresenter mSelectedPagePresenter;

    public ITicketPresenter getTicketPresenter() {
        return mTicketPresenter;
    }

    public IHomePresenter getHomePresenter() {
        return mHomePresenter;
    }

    public ICategoryPagerPresenter getCategoryPagePresenter() {
        return mCategoryPagePresenter;
    }

    public static PresenterManager getInstance() {
        return ourInstance;
    }

    public ISelectedPagePresenter getSelectedPagePresenter() {
        return mSelectedPagePresenter;
    }

    private PresenterManager() {
        mCategoryPagePresenter = new CategoryPagePresenterImpl();
        mHomePresenter = new HomePresenterImpl();
        mTicketPresenter = new TicketPresenterImpl();
        mSelectedPagePresenter = new SelectedPagePresenterImpl();
    }
}
