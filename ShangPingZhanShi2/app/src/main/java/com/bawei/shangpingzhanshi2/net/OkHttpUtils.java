package com.bawei.shangpingzhanshi2.net;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author：张安磊
 * @E-mail：
 * @Date：
 * @Description：描述信息
 */
public class OkHttpUtils {

    private int HTTP_FAIL=9;
    private int HTTP_SUCCESS=10;
    private getHttpLisener getHttpLisener;

    public OkHttpUtils get(String url){
        getHttp(url,0,null);
        return this;
    }
    public OkHttpUtils post(String url, FormBody.Builder builder1){
        getHttp(url,1,builder1);
        return this;
    }

    private void getHttp(String url, int i, FormBody.Builder builder1) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (i==0){
            builder.get();
        }else{
            RequestBody requestBody=builder1.build();
            builder.post(requestBody);
        }
        Request request = builder.build();
        client.newCall(request).enqueue(new Callback() {
            Message message=handler.obtainMessage();
            @Override
            public void onFailure(Call call, IOException e) {
                message.what=HTTP_FAIL;
                message.obj=e.fillInStackTrace();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                message.what=HTTP_SUCCESS;
                message.obj=response.body().string();
                handler.sendMessage(message);
            }
        });
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==HTTP_SUCCESS){
                String data= (String) msg.obj;
                getHttpLisener.success(data);
            }else{
                String error= (String) msg.obj;
                getHttpLisener.fail(error);
            }
        }
    };

    //定义一个方法
    public void result(getHttpLisener getHttpLisener){
        this.getHttpLisener=getHttpLisener;
    }
    //接口
    public interface getHttpLisener{
        void success(String data);
        void fail(String error);
    }
}
