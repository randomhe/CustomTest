package com.example.bella.snowball.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bella.snowball.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bella on 2018/1/26.
 */

public class TimeAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<HashMap<String,Object>> list;

    public TimeAdapter(Context context,List<HashMap<String,Object>> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_time_layout,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1= (MyViewHolder) holder;
        holder1.firstText.setText((String)list.get(position).get("itemTitle"));
        holder1.secondText.setText((String)list.get(position).get("itemText"));
    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView firstText;
        private TextView secondText;
        public MyViewHolder(View itemView) {
            super(itemView);
            firstText=itemView.findViewById(R.id.text_post);
            secondText=itemView.findViewById(R.id.text_addr);
        }
    }

}
