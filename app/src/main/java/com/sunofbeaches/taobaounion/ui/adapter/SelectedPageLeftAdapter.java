package com.sunofbeaches.taobaounion.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.model.domain.SelectedPageCategory;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectedPageLeftAdapter extends RecyclerView.Adapter<SelectedPageLeftAdapter.InnerHolder> {

    private List<SelectedPageCategory.DataBean> mData = new ArrayList<>();

    private int mCurrentSelectedPosition = 0;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_page_left,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder,int position) {
        TextView itemTv = holder.itemView.findViewById(R.id.left_category_tv);
        if(mCurrentSelectedPosition == position) {
            itemTv.setBackgroundColor(itemTv.getResources().getColor(R.color.colorEEEEEE,null));
        } else {
            itemTv.setBackgroundColor(itemTv.getResources().getColor(R.color.white,null));
        }
        SelectedPageCategory.DataBean dataBean = mData.get(position);
        itemTv.setText(dataBean.getFavorites_title());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 设置数据
     *
     * @param categories
     */
    public void setData(SelectedPageCategory categories) {
        List<SelectedPageCategory.DataBean> data = categories.getData();
        if(data != null) {
            this.mData.clear();
            this.mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
