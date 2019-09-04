package tw.noel.sung.com.demo_linechatbot.connect;

import android.content.Context;

import com.google.gson.Gson;

import tw.noel.sung.com.ztool.connect.z_connect.ZConnect;

public class BaseConnect {
    protected Context context;
    protected Gson gson;
    private final int TIME_OUT = 30 * 1000;
    protected ZConnect zConnect;

    public BaseConnect(Context context) {
        this.context = context;
        gson = new Gson();
        zConnect = new ZConnect(context);
        zConnect.setConnectTimeOut(TIME_OUT);
        zConnect.setWriteTimeOut(TIME_OUT);
        zConnect.setReadTimeOut(TIME_OUT);
    }
}
