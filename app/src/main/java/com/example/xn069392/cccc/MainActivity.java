package com.example.xn069392.cccc;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
public class MainActivity extends Activity {
    private ListView listView;
    private LinearLayout sectionB;
    private int aHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sectionB = (LinearLayout) findViewById(R.id.main_section_b_outside);
        aHeight = getResources().getDimensionPixelSize(R.dimen.ggggg);
        initListView();
    }
    private void initListView(){
        listView = (ListView) findViewById(R.id.main_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        for (int i = 0; i<100; i++){
            adapter.add("item "+String.valueOf(i));
        }
        listView.setAdapter(adapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.mainheader,null);
        listView.addHeaderView(headerView);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (getScrollY() >= aHeight) {
                    if (sectionB.getVisibility() == View.INVISIBLE) {
                        sectionB.setVisibility(View.VISIBLE);
                    }
                } else if (getScrollY() < aHeight){
                    if (sectionB.getVisibility() == View.VISIBLE){
                        sectionB.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }
    //获取滚动距离
    public int getScrollY() {
        View c = listView.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int top = c.getTop();
        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = listView.getHeight();
        }
        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }
}