package tw.noel.sung.com.demo_linechatbot.tools;



import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by noel on 2019/7/18.
 */
public class EventBusTool {


    // //連線失敗
    public static final int EVENT_CONNECT_ERROR = 1;
    //發送訊息
    public static final int EVENT_PUSH_MESSAGE = 2;

    //------------

    private static EventBusTool eventBusTool;


    //--------------------------------------------------

    public static EventBusTool getInstance() {
        if (eventBusTool == null) {
            eventBusTool = new EventBusTool();
        }
        return eventBusTool;
    }

    //------

    private void sendListEvent(int type, List<?> dataList) {
        Map<String, Object> data = new HashMap<>();

        data.put("type", type);
        data.put("data", dataList);
        EventBus.getDefault().post(data);
    }

    //------

    private void sendObjectEvent(int type, Object object) {
        Map<String, Object> data = new HashMap<>();
        data.put("type", type);
        data.put("data", object);
        EventBus.getDefault().post(data);
    }

    //-----

    /**
     * 連線失敗
     */
    public void sendConnectErrorEvent(String errString) {
        sendObjectEvent(EVENT_CONNECT_ERROR, errString);
    }


    //--------

    /***
     *  發送 - 送出訊息
     */
    public void sendPushMessageData() {
        sendObjectEvent(EVENT_PUSH_MESSAGE, "");
    }
}
