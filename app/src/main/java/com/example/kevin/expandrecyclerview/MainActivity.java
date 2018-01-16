package com.example.kevin.expandrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nubility.kevin.adapter.ExpandRecyclerBean;
import com.example.kevin.expandrecyclerview.bean.EvlBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TestAdapter mExpandableAdapter;
    private List<ExpandRecyclerBean> mLtEvlBean = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerTest);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mExpandableAdapter = new TestAdapter(this, mLtEvlBean, mRecyclerView);
        mRecyclerView.setAdapter(mExpandableAdapter);
        setData();
    }


    public void setData() {
        for (int i = 0; i < 5; i++) {
            ExpandRecyclerBean<EvlBean> expandRecyclerBean = new ExpandRecyclerBean<>();
            EvlBean evlBean = new EvlBean();
            evlBean.setGroupName(String.valueOf(i));
            List lt = new ArrayList();
            evlBean.setmLtGroupChild(lt);
            expandRecyclerBean.setClose(true);
            expandRecyclerBean.setChildSize(2);
            expandRecyclerBean.setData(evlBean);
            for (int j = 0; j < 2; j++) {
                lt.add(String.valueOf(j));
            }
            mLtEvlBean.add(expandRecyclerBean);
        }
        mExpandableAdapter.notifyDataSetChanged();
    }
}
