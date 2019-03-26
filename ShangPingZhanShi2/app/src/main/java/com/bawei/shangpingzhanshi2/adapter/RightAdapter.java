package com.bawei.shangpingzhanshi2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.shangpingzhanshi2.R;
import com.bawei.shangpingzhanshi2.bean.LeftBean;
import com.bawei.shangpingzhanshi2.bean.RightBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：张安磊
 * @E-mail：
 * @Date：
 * @Description：描述信息
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context context;
    private  List<RightBean.ResultBean> list=new ArrayList<>();

    public RightAdapter(Context context, List<RightBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_right, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.ViewHolder viewHolder, int i) {
        viewHolder.name.setText(list.get(i).getName());
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
}
