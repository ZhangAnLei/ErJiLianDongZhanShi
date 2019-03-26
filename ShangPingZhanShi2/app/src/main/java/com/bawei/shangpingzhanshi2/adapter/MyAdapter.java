package com.bawei.shangpingzhanshi2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.shangpingzhanshi2.R;
import com.bawei.shangpingzhanshi2.bean.LeftBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：张安磊
 * @E-mail：
 * @Date：
 * @Description：描述信息
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<LeftBean.ResultBean> list=new ArrayList<>();
    private CallBack callBack;

    public MyAdapter(Context context, List<LeftBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_left, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(list.get(i).getName());
        if (list.get(i).isClick()){
            viewHolder.name.setTextColor(Color.RED);
        }else{
            viewHolder.name.setTextColor(Color.parseColor("#999999"));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.setData(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name =(TextView)itemView.findViewById(R.id.name);
        }
    }
    public void result(CallBack callBack){
        this.callBack=callBack;
    }
    public interface CallBack{
        void setData(int position);
    }
}
