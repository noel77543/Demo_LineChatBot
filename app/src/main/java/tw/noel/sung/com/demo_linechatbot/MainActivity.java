package tw.noel.sung.com.demo_linechatbot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.noel.sung.com.demo_linechatbot.connect.ChatBotConnect;
import tw.noel.sung.com.demo_linechatbot.tools.views.MessageEditTextView;
import tw.noel.sung.com.ztool.tool.ZTimeTool;

import static tw.noel.sung.com.demo_linechatbot.tools.EventBusTool.EVENT_CONNECT_ERROR;
import static tw.noel.sung.com.demo_linechatbot.tools.EventBusTool.EVENT_PUSH_MESSAGE;

public class MainActivity extends AppCompatActivity {

    //詳見
    //https://www.evernote.com/shard/s606/sh/082d0cb1-2e85-427c-aede-29b99a9ef473/884a0b67a57b675602dac967111b00be

    @BindView(R.id.button_send)
    Button buttonSend;
    @BindView(R.id.message_edit_text_view_target)
    MessageEditTextView messageEditTextViewTarget;
    @BindView(R.id.message_edit_text_view_message)
    MessageEditTextView messageEditTextViewMessage;

    private ChatBotConnect chatBotConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        chatBotConnect = new ChatBotConnect(this);
        messageEditTextViewTarget.setAnimateHintText("恰霸想跟...");
        messageEditTextViewTarget.setEditText("C77680a7ec270ca14ddc7659e97d12a0c");
        messageEditTextViewMessage.setAnimateHintText("說...");

    }

    //------------

    @Override
    protected void onPostResume() {
        super.onPostResume();
        EventBus.getDefault().register(this);
    }

    //------------

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    //------------
    @OnClick(R.id.button_send)
    public void onClicked(View view) {
        if (isTarget() & isMessage()) {
            chatBotConnect.connectToPostPushMessage(messageEditTextViewTarget.getTextToString(), messageEditTextViewMessage.getTextToString());
        }
    }

    //------------


    @Subscribe
    public void onResponse(Map<String, Object> data) {
        int type = (int) data.get("type");

        switch (type) {
            //發送訊息
            case EVENT_PUSH_MESSAGE:
                messageEditTextViewMessage.setEditText("");
                Toast.makeText(this, "訊息已送出。", Toast.LENGTH_SHORT).show();
                break;
            //連線失敗
            case EVENT_CONNECT_ERROR:
                Toast.makeText(this, (String) data.get("data"), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //------------------


    private boolean isTarget() {
        boolean isTarget = messageEditTextViewTarget.getTextToString().length() > 0;
        //沒輸入目標
        if (!isTarget) {
            messageEditTextViewTarget.showRightMessage("請確認接收者欄位填寫正確", R.color.colorPrimary);
        } else {
            messageEditTextViewTarget.hideAllBottomMessage();
        }
        return isTarget;
    }

    //------------------


    private boolean isMessage() {
        boolean isTarget = messageEditTextViewMessage.getTextToString().length() > 0;
        //沒輸入目標
        if (!isTarget) {
            messageEditTextViewMessage.showRightMessage("請確認訊息欄位填寫正確", R.color.colorPrimary);
        } else {
            messageEditTextViewMessage.hideAllBottomMessage();
        }
        return isTarget;
    }
}
