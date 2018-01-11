package com.nubility.kevin.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by kevin on 17/11/24.
 */

public abstract class ExpandableRecyclerAdapter extends RecyclerView.Adapter {

    public static final int VIEW_TYPE_PARENT = 1;
    public static final int VIEW_TYPE_CHILD = 2;
    private RecyclerView mRecyclerView;
    protected List<ExpandRecyclerBean> mLtBean;
    private int mCount;
    private int[] mPosition;


    public ExpandableRecyclerAdapter(List<ExpandRecyclerBean> ltBean, RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        this.mLtBean = ltBean;
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int adapterPosition = vh.getAdapterPosition();
                int[] dataPosition = positionToDataPosition(adapterPosition);
                if (dataPosition[1] == -1) {
                    mLtBean.get(dataPosition[0]).setClose(!mLtBean.get(dataPosition[0]).isClose());
                    notifyDataSetChanged();
                } else {
                    itemChildClick(dataPosition);
                }
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {

            }
        });
    }

    abstract void itemChildClick(int[] dataPosition);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final int[] dataPosition = positionToDataPosition(position);
        onBindViewHolder(holder, dataPosition);
    }

    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, final int[] dataPosition);


    @Override
    public int getItemCount() {
        return mLtBean == null ? 0 : computeCount();
    }

    private int computeCount() {
        int j = mLtBean.size();
        mPosition = new int[j];
        mCount = 0;
        int size;
        for (int i = 0; i < j; i++) {
            size = 1;
            if (!mLtBean.get(i).isClose()) {
                size += mLtBean.get(i).getChildSize();
            }
            mCount += size;
            mPosition[i] = mCount;
        }
        return mCount;
    }

    private int[] positionToDataPosition(int position) {
        int i = 0;
        int j = position;
        for (; i < mPosition.length; i++) {
            if (position < mPosition[i]) {
                if (i > 0) {
                    j -= mPosition[i - 1];
                }
                break;
            }
        }
        int[] dataPosition = {i, j - 1};
        return dataPosition;
    }


    @Override
    public int getItemViewType(int position) {
        int[] dataPosition = positionToDataPosition(position);
        if (dataPosition[1] == -1) {
            return VIEW_TYPE_PARENT;
        } else {
            return VIEW_TYPE_CHILD;
        }
    }

}
