package com.bawei.shangpingzhanshi2.mvp.presenter;

import com.bawei.shangpingzhanshi2.mvp.model.ShopModel;
import com.bawei.shangpingzhanshi2.mvp.view.ShopView;

/**
 * @Author：张安磊
 * @E-mail：
 * @Date：
 * @Description：描述信息
 */
public class ShopPresenterIml implements ShopPresenter {
    private ShopModel shopModel;
    private ShopView shopView;

    public ShopPresenterIml(ShopModel shopModel, ShopView shopView) {
        this.shopModel = shopModel;
        this.shopView = shopView;
    }

    @Override
    public void doLeft(int type) {
        shopModel.getLeft(type,new ShopModel.CallBack() {
            @Override
            public void success(int type,String data) {
                shopView.success(type,data);
            }

            @Override
            public void fail(String error) {
                shopView.fail(error);
            }
        });
    }

    @Override
    public void doRight(int type,String firstCategoryId) {
        shopModel.getRight(type,firstCategoryId, new ShopModel.CallBack() {
            @Override
            public void success(int type,String data) {
                shopView.success(type,data);
            }

            @Override
            public void fail(String error) {
                shopView.fail(error);
            }
        });
    }
}
