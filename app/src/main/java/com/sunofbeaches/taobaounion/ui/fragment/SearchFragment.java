package com.sunofbeaches.taobaounion.ui.fragment;

import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.base.BaseFragment;
import com.sunofbeaches.taobaounion.model.domain.Histories;
import com.sunofbeaches.taobaounion.model.domain.IBaseInfo;
import com.sunofbeaches.taobaounion.model.domain.SearchRecommend;
import com.sunofbeaches.taobaounion.model.domain.SearchResult;
import com.sunofbeaches.taobaounion.presenter.ISearchPresenter;
import com.sunofbeaches.taobaounion.ui.adapter.LinearItemContentAdapter;
import com.sunofbeaches.taobaounion.ui.custom.TextFlowLayout;
import com.sunofbeaches.taobaounion.utils.KeyboardUtil;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.PresenterManager;
import com.sunofbeaches.taobaounion.utils.SizeUtils;
import com.sunofbeaches.taobaounion.utils.TicketUtil;
import com.sunofbeaches.taobaounion.utils.ToastUtil;
import com.sunofbeaches.taobaounion.view.ISearchPageCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class SearchFragment extends BaseFragment implements ISearchPageCallback, TextFlowLayout.OnFlowTextItemClickListener {


    @BindView(R.id.search_history_view)
    public TextFlowLayout mHistoriesView;

    @BindView(R.id.search_recommend_view)
    public TextFlowLayout mRecommendView;

    @BindView(R.id.search_recommend_container)
    public View mRecommendContainer;

    @BindView(R.id.search_history_container)
    public View mHistoriesContainer;


    @BindView(R.id.search_history_delete)
    public View mHistoryDelete;


    @BindView(R.id.search_result_list)
    public RecyclerView mSearchList;

    @BindView(R.id.search_btn)
    public TextView mSearchBtn;

    @BindView(R.id.search_clean_btn)
    public ImageView mCleanInputBtn;

    @BindView(R.id.search_input_box)
    public EditText mSearchInputBox;


    @BindView(R.id.search_result_container)
    public TwinklingRefreshLayout mRefreshContainer;


    private ISearchPresenter mSearchPresenter;
    private LinearItemContentAdapter mSearchResultAdapter;

    @Override
    protected void initPresenter() {
        mSearchPresenter = PresenterManager.getInstance().getSearchPresenter();
        mSearchPresenter.registerViewCallback(this);
        //获取搜索推荐词
        mSearchPresenter.getRecommendWords();
        //mSearchPresenter.doSearch("毛衣");
        mSearchPresenter.getHistories();
    }


    @Override
    protected void onRetryClick() {
        //重新加载内容
        if(mSearchPresenter != null) {
            mSearchPresenter.research();
        }
    }

    @Override
    protected void release() {
        if(mSearchPresenter != null) {
            mSearchPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    protected View loadRootView(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.fragment_search_layout,container,false);
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initListener() {
        mHistoriesView.setOnFlowTextItemClickListener(this);
        mRecommendView.setOnFlowTextItemClickListener(this);
        //发起搜索
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果有内容搜索
                //如果输入框没有内容则取消
                if(hasInput(false)) {
                    //发起否所
                    if(mSearchPresenter != null) {
                        //mSearchPresenter.doSearch(mSearchInputBox.getText().toString().trim());
                        toSearch(mSearchInputBox.getText().toString().trim());
                        KeyboardUtil.hide(getContext(),v);
                    }
                } else {
                    //隐藏键盘
                    KeyboardUtil.hide(getContext(),v);
                }
            }
        });
        //清除输入框里的内容
        mCleanInputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchInputBox.setText("");
                //回到历史记录界面
                switch2HistoryPage();
            }
        });

        //监听输入框的内容变化
        mSearchInputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s,int start,int count,int after) {

            }

            @Override
            public void onTextChanged(CharSequence s,int start,int before,int count) {
                //变化时候的通知
                //LogUtils.d(SearchFragment.this,"input text === > " + s.toString().trim());
                //如果长度不为0，那么显示删除按钮
                //否则隐藏删除按钮
                mCleanInputBtn.setVisibility(hasInput(true) ? View.VISIBLE : View.GONE);
                mSearchBtn.setText(hasInput(false) ? "搜索" : "取消");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });
        mSearchInputBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v,int actionId,KeyEvent event) {
                //LogUtils.d(SearchFragment.this,"actionId === > " + actionId);
                if(actionId == EditorInfo.IME_ACTION_SEARCH && mSearchPresenter != null) {
                    String keyword = v.getText().toString().trim();
                    if(TextUtils.isEmpty(keyword)) {
                        return false;
                    }
                    //判断拿到的内容是否为空
                    LogUtils.d(SearchFragment.this," input text === > " + keyword);
                    //发起搜索
                    toSearch(keyword);
                    //mSearchPresenter.doSearch(keyword);
                }
                return false;
            }
        });
        mHistoryDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除历史记录
                mSearchPresenter.delHistories();
            }
        });

        mRefreshContainer.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                //去加载更多内容
                if(mSearchPresenter != null) {
                    mSearchPresenter.loaderMore();
                }
            }
        });

        mSearchResultAdapter.setOnListItemClickListener(new LinearItemContentAdapter.OnListItemClickListener() {
            @Override
            public void onItemClick(IBaseInfo item) {
                //搜索列表内容被点击了
                TicketUtil.toTicketPage(getContext(),item);
            }
        });

    }

    /**
     * 切换到历史和推荐界面
     */
    private void switch2HistoryPage() {
        if(mSearchPresenter != null) {
            mSearchPresenter.getHistories();
        }
        if(mRecommendView.getContentSize() != 0) {
            mRecommendContainer.setVisibility(View.VISIBLE);
        } else {
            mRecommendContainer.setVisibility(View.GONE);
        }
        //内容要隐藏
        mRefreshContainer.setVisibility(View.GONE);
    }

    private boolean hasInput(boolean containSpace) {
        if(containSpace) {
            return mSearchInputBox.getText().toString().length() > 0;
        } else {
            return mSearchInputBox.getText().toString().trim().length() > 0;
        }
    }

    @Override
    protected void initView(View rootView) {
        //设置布局管理器
        mSearchList.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置适配器
        mSearchResultAdapter = new LinearItemContentAdapter();
        mSearchList.setAdapter(mSearchResultAdapter);
        //设置刷新控件
        mRefreshContainer.setEnableLoadmore(true);
        mRefreshContainer.setEnableRefresh(false);
        mRefreshContainer.setEnableOverScroll(true);
        mSearchList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect,@NonNull View view,@NonNull RecyclerView parent,@NonNull RecyclerView.State state) {
                outRect.top = SizeUtils.dip2px(getContext(),1.5f);
                outRect.bottom = SizeUtils.dip2px(getContext(),1.5f);
                ;
            }
        });
    }

    @Override
    public void onHistoriesLoaded(Histories histories) {
        setUpState(State.SUCCESS);
        LogUtils.d(this,"histories -- > " + histories);
        if(histories == null || histories.getHistories().size() == 0) {
            mHistoriesContainer.setVisibility(View.GONE);
        } else {
            mHistoriesContainer.setVisibility(View.VISIBLE);
            mHistoriesView.setTextList(histories.getHistories());
        }
    }

    @Override
    public void onHistoriesDeleted() {
        //更新历史记录
        if(mSearchPresenter != null) {
            mSearchPresenter.getHistories();
        }
    }

    @Override
    public void onSearchSuccess(SearchResult result) {
        setUpState(State.SUCCESS);
        //LogUtils.d(this," result -=- > " + result);
        //隐藏掉历史记录和推荐
        mRecommendContainer.setVisibility(View.GONE);
        mHistoriesContainer.setVisibility(View.GONE);
        //显示搜索界面
        mRefreshContainer.setVisibility(View.VISIBLE);
        //设置数据
        try {
            mSearchResultAdapter.setData(result.getData()
                    .getTbk_dg_material_optional_response()
                    .getResult_list()
                    .getMap_data());
        } catch(Exception e) {
            e.printStackTrace();
            //切换到搜搜内容为空
            setUpState(State.EMPTY);
        }
    }

    @Override
    public void onMoreLoaded(SearchResult result) {
        mRefreshContainer.finishLoadmore();
        //加载到更多的结果
        //拿到结果，添加到适配器的尾部
        List<SearchResult.DataBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean> moreData = result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data();
        mSearchResultAdapter.addData(moreData);
        //提示用户加载到的内容
        ToastUtil.showToast("加载到了" + moreData.size() + "条记录");
    }

    @Override
    public void onMoreLoadedError() {
        mRefreshContainer.finishLoadmore();
        ToastUtil.showToast("网络异常，请稍后重试");
    }

    @Override
    public void onMoreLoadedEmpty() {
        mRefreshContainer.finishLoadmore();
        ToastUtil.showToast("没有更多数据");
    }

    @Override
    public void onRecommendWordsLoaded(List<SearchRecommend.DataBean> recommendWords) {
        setUpState(State.SUCCESS);
        LogUtils.d(this,"recommendWords size --- > " + recommendWords.size());
        List<String> recommendKeywords = new ArrayList<>();
        for(SearchRecommend.DataBean item : recommendWords) {
            recommendKeywords.add(item.getKeyword());
        }
        if(recommendWords == null || recommendWords.size() == 0) {
            mRecommendContainer.setVisibility(View.GONE);
        } else {
            mRecommendView.setTextList(recommendKeywords);
            mRecommendContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError() {
        setUpState(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);
    }

    @Override
    public void onFlowItemClick(String text) {
        //发起搜索
        toSearch(text);
    }

    private void toSearch(String text) {
        if(mSearchPresenter != null) {
            mSearchList.scrollToPosition(0);
            mSearchInputBox.setText(text);
            mSearchInputBox.setFocusable(true);
            mSearchInputBox.requestFocus();
            //mSearchInputBox.setSelection(text.length());
            mSearchInputBox.setSelection(text.length(),text.length());
            mSearchPresenter.doSearch(text);
        }
    }
}
