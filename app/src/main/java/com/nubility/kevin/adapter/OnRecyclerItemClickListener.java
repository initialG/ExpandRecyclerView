package com.nubility.kevin.adapter;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kevin on 17/12/6.
 */

public abstract class OnRecyclerItemClickListener extends RecyclerView.SimpleOnItemTouchListener {
    private GestureDetectorCompat mGestureDetector;
    private RecyclerView recyclerView;

    public OnRecyclerItemClickListener(final RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null) {
                    RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(childView);
                    onItemClick(vh);
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null) {
                    RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(childView);
                    onItemLongClick(vh);
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    public abstract void onItemLongClick(RecyclerView.ViewHolder vh);
}
