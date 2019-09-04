package tw.noel.sung.com.demo_linechatbot.connect;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import tw.noel.sung.com.demo_linechatbot.connect.message.RequestPushMessage;
import tw.noel.sung.com.demo_linechatbot.tools.EventBusTool;
import tw.noel.sung.com.ztool.connect.z_connect.util.callback.ZConnectHandler;

public class ChatBotConnect extends BaseConnect {


    private final String _ACCESS_TOKEN = "xI4WQa1+DR8+g24dlgFNTEmnxuT35VbXAxOBzQd7nG2/5FtRKxTL5KmXkJ5+g/qOqvJyNtcwq2cH4Qn5nAizlmHBmJnkxxxNZgz6EDzKHj3X9kO5NHrL52BDWafSVz7NhKJ7kHBHVUo0hcjSfC5IdwdB04t89/1O/w1cDnyilFU=";

    public ChatBotConnect(Context context) {
        super(context);
    }


    //--------------

    /***
     *  發送訊息
     */
    public void connectToPostPushMessage(String to, String message) {

        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + _ACCESS_TOKEN);

        ArrayList<RequestPushMessage.MessagesBean> messages = new ArrayList<>();
        messages.add(new RequestPushMessage.MessagesBean("text", message));

        zConnect.post(ConnectInfo._POST_PUSH_MESSAGE, header, new RequestPushMessage(to, messages), new ZConnectHandler() {

            @Override
            public void OnStringResponse(String response, int code) {
                EventBusTool.getInstance().sendPushMessageData();
            }

            @Override
            public void OnFail(IOException e) {
                super.OnFail(e);
                Log.e("Exception", e.getMessage());
            }

            @Override
            public void OnFail(String response, int code) {
                super.OnFail(response, code);
                Log.e("OnFail", code + "");
                Log.e("OnFail", response);

            }
        });

    }
}
