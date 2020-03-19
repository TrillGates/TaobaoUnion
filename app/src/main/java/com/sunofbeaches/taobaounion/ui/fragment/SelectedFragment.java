package com.sunofbeaches.taobaounion.ui.fragment;

import android.view.View;

import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.base.BaseFragment;
import com.sunofbeaches.taobaounion.model.domain.SelectedContent;
import com.sunofbeaches.taobaounion.model.domain.SelectedPageCategory;
import com.sunofbeaches.taobaounion.presenter.ISelectedPagePresenter;
import com.sunofbeaches.taobaounion.ui.adapter.SelectedPageLeftAdapter;
import com.sunofbeaches.taobaounion.utils.PresenterManager;
import com.sunofbeaches.taobaounion.view.ISelectedPageCallback;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SelectedFragment extends BaseFragment implements ISelectedPageCallback {

    @BindView(R.id.left_category_list)
    public RecyclerView leftCategoryList;


    private ISelectedPagePresenter mSelectedPagePresenter;
    private SelectedPageLeftAdapter mLeftAdapter;

    @Override
    protected void initPresenter() {
        super.initPresenter();
        mSelectedPagePresenter = PresenterManager.getInstance().getSelectedPagePresenter();
        mSelectedPagePresenter.registerViewCallback(this);
        mSelectedPagePresenter.getCategories();
    }


    @Override
    protected void release() {
        super.release();
        if(mSelectedPagePresenter != null) {
            mSelectedPagePresenter.unregisterViewCallback(this);
        }
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_selected;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
        leftCategoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        mLeftAdapter = new SelectedPageLeftAdapter();
        leftCategoryList.setAdapter(mLeftAdapter);
    }

    @Override
    public void onCategoriesLoaded(SelectedPageCategory categories) {
        mLeftAdapter.setData(categories);
        //分类内容
       // LogUtils.d(this,"onCategoriesLoaded -- > " + categories);
        //TODO:更新UI
        //根据当前选中的分类，获取分类详情内容
//        List<SelectedPageCategory.DataBean> data = categories.getData();
//        mSelectedPagePresenter.getContentByCategory(data.get(0));
    }

    @Override
    public void onContentLoaded(SelectedContent content) {
//        LogUtils.d(this,"onContentLoaded --- > " + content.getData()
//                .getTbk_uatm_favorites_item_get_response()
//                .getResults()
//                .getUatm_tbk_item()
//                .get(0).getTitle());
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }
}
