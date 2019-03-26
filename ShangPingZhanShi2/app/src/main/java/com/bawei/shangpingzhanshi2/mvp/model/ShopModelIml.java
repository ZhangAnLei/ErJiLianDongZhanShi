package com.bawei.shangpingzhanshi2.mvp.model;

import com.bawei.shangpingzhanshi2.net.OkHttpUtils;

/**
 * @Author：张安磊
 * @E-mail：
 * @Date：
 * @Description：描述信息
 */
public class ShopModelIml implements ShopModel {
    @Override
    public void getLeft(final int type, final CallBack callBack) {
        String url="http://172.17.8.100/small/commodity/v1/findFirstCategory";
        new OkHttpUtils().get(url).result(new OkHttpUtils.getHttpLisener() {
            @Override
            public void success(String data) {
                callBack.success(type,data);
            }

            @Override
            public void fail(String error) {
                callBack.fail(error);
            }
        });
    }

    @Override
    public void getRight(final int type, String firstCategoryId, final CallBack callBack) {
        String url="http://172.17.8.100/small/commodity/v1/findSecondCategory?firstCategoryId="+firstCategoryId;
        new OkHttpUtils().get(url).result(new OkHttpUtils.getHttpLisener() {
            @Override
            public void success(String data) {
                callBack.success(type,data);
            }

            @Override
            public void fail(String error) {
                callBack.fail(error);
            }
        });
    }
}
