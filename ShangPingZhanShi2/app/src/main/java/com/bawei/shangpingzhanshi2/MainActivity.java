package com.bawei.shangpingzhanshi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bawei.shangpingzhanshi2.adapter.MyAdapter;
import com.bawei.shangpingzhanshi2.adapter.RightAdapter;
import com.bawei.shangpingzhanshi2.bean.LeftBean;
import com.bawei.shangpingzhanshi2.bean.RightBean;
import com.bawei.shangpingzhanshi2.mvp.model.ShopModelIml;
import com.bawei.shangpingzhanshi2.mvp.presenter.ShopPresenterIml;
import com.bawei.shangpingzhanshi2.mvp.view.ShopView;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ShopView {

    private RecyclerView recycler_left,recycler_right;
    private ShopPresenterIml shopPresenterIml;
    private List<LeftBean.ResultBean> listLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_left =(RecyclerView)findViewById(R.id.recycler_left);
        recycler_right =(RecyclerView)findViewById(R.id.recycler_right);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_left.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,3);
        recycler_right.setLayoutManager(gridLayoutManager);
        shopPresenterIml = new ShopPresenterIml(new ShopModelIml(), this);
        shopPresenterIml.doLeft(0);
    }

    @Override
    public void success(int type,String data) {
        if (type == 0) {
            // Log.d("aaa",data);
            listLeft = new Gson().fromJson(data, LeftBean.class).getResult();
            //创建适配器
            final MyAdapter adapter = new MyAdapter(MainActivity.this, listLeft);
            recycler_left.setAdapter(adapter);

            /*//默认展示第一条数据  设置成选中状态
            listLeft.get(0).setClick(true);
            adapter.result(new MyAdapter.CallBack() {
                @Override
                public void setData(int position) {
                    //拿到左边数据所对应的id
                    String id = listLeft.get(position).getId();
                    //设置右边对应的数据
                    shopPresenterIml.doRight(1,id);
                    //便利左边集合  设置颜色
                    for (int a=0;a<listLeft.size();a++){
                        if (position==a){
                            listLeft.get(a).setClick(true);
                        }else{
                            listLeft.get(a).setClick(false);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            });*/

            //将第一条数据默认设置成红色
            listLeft.get(0).setClick(true);
            adapter.result(new MyAdapter.CallBack() {
                @Override
                public void setData(int position) {
                    //获取左边的id
                    String id = listLeft.get(position).getId();
                    shopPresenterIml.doRight(1,id);
                    for (int i=0;i<listLeft.size();i++){
                        if (i==position){
                            listLeft.get(i).setClick(true);
                        }else{
                            listLeft.get(i).setClick(false);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            });



            shopPresenterIml.doRight(1,listLeft.get(0).getId());
        } else {
            List<RightBean.ResultBean> listRight = new Gson().fromJson(data, RightBean.class).getResult();
            //创建适配器
            RightAdapter adapter = new RightAdapter(MainActivity.this, listRight);
            recycler_right.setAdapter(adapter);
        }
    }

    @Override
    public void fail(String error) {

    }
}
