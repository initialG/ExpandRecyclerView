package com.nubility.kevin.adapter;

/**
 * Created by kevin on 17/12/6.
 */

public class ExpandRecyclerBean<T> {
    private boolean isClose;
    private int childSize;
    private T data;

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }

    public int getChildSize() {
        return childSize;
    }

    public void setChildSize(int childSize) {
        this.childSize = childSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
