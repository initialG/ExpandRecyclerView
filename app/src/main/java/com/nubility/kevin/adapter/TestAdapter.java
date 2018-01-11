package com.nubility.kevin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kevin.expandrecyclerview.R;
import com.nubility.kevin.bean.EvlBean;

import java.util.List;

/**
 * Created by kevin on 17/12/6.
 */

public class TestAdapter extends ExpandableRecyclerAdapter {
    private Context mContext;
    private List<ExpandRecyclerBean<EvlBean>> mData;

    public TestAdapter(Context context, List ltBean, RecyclerView recyclerView) {
        super(ltBean, recyclerView);
        mData = ltBean;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_PARENT:
                View parentView = LayoutInflater.from(mContext).inflate(R.layout.layout_parent, parent, false);
                return new ParentViewHolder(parentView);
            default:
                View childView = LayoutInflater.from(mContext).inflate(R.layout.layout_child, parent, false);
                return new ChildViewHolder(childView);
        }
    }

    @Override
    void itemChildClick(int[] dataPosition) {
        Toast.makeText(mContext, dataPosition[0] + " " + dataPosition[1], Toast.LENGTH_SHORT).show();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int[] dataPosition) {
        if (dataPosition[1] == -1) {
            ParentViewHolder parentViewHolder = (ParentViewHolder) holder;
            parentViewHolder.mTvName.setText(mData.get(dataPosition[0]).getData().getGroupName());
        } else {
            ChildViewHolder childViewHolder = (ChildViewHolder) holder;
            childViewHolder.mTvName.setText(mData.get(dataPosition[0]).getData().getmLtGroupChild().get(dataPosition[1]));
        }
    }


    class ParentViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvName;
        private LinearLayout llyt;

        public ParentViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvName);
            llyt = itemView.findViewById(R.id.llytParent);
        }
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;

        public ChildViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvName);
        }
    }


    public void setData(List<EvlBean> ltBean) {
//        mLtBean.clear();
//        mLtBean.addAll(ltBean);
//        notifyDataSetChanged();
    }
}



