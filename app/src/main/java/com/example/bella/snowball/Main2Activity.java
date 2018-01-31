package com.example.bella.snowball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bella.snowball.Adapter.TimeAdapter;
import com.example.bella.snowball.Adapter.TimeItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<HashMap<String,Object>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRecyclerView=findViewById(R.id.recycler);
        initData();
        initAdapter();
    }
    private void initData() {
        listItem = new ArrayList<>();/*在数组中存放数据*/

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        HashMap<String, Object> map6 = new HashMap<String, Object>();
        HashMap<String, Object> map7 = new HashMap<String, Object>();

        map1.put("itemTitle", "美国谷歌公司已发出");
        map1.put("itemText", "发件人:谷歌 CEO Sundar Pichai");
        listItem.add(map1);

        map2.put("itemTitle", "国际顺丰已收入");
        map2.put("itemText", "等待中转");
        listItem.add(map2);

        map3.put("itemTitle", "国际顺丰转件中");
        map3.put("itemText", "下一站中国");
        listItem.add(map3);

        map4.put("itemTitle", "中国顺丰已收入");
        map4.put("itemText", "下一站广州华南理工大学");
        listItem.add(map4);

        map5.put("itemTitle", "中国顺丰派件中");
        map5.put("itemText", "等待派件");
        listItem.add(map5);

        map6.put("itemTitle", "华南理工大学已签收");
        map6.put("itemText", "收件人:Carson");
        listItem.add(map6);

        map7.put("itemTitle", "华南理工大学已签收");
        map7.put("itemText", "收件人:Carson");
        listItem.add(map7);

    }
    private void initAdapter() {
        LinearLayoutManager manager=new LinearLayoutManager(getApplication());
        mRecyclerView.setLayoutManager(manager);
        TimeAdapter adapter=new TimeAdapter(getApplication(),listItem);
        mRecyclerView.setAdapter(adapter);
        TimeItemDecoration decoration=new TimeItemDecoration();
        mRecyclerView.addItemDecoration(decoration);
        adapter.notifyDataSetChanged();
    }
}
