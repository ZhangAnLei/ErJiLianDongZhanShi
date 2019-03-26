package com.bawei.shangpingzhanshi2.mvp.model;

/**
 * @Author：张安磊
 * @E-mail：
 * @Date：
 * @Description：描述信息
 */
public interface ShopModel {
    interface CallBack{
        void success(int type,String data);
        void fail(String error);
    }
    //请求左边的数据
    void getLeft(int type,CallBack callBack);

    //请求右边的数据
    void getRight(int type,String firstCategoryId,CallBack callBack);
}
