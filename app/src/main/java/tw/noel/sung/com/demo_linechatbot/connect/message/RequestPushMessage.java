package tw.noel.sung.com.demo_linechatbot.connect.message;

import java.util.ArrayList;
import java.util.List;

public class RequestPushMessage {

    /**
     * to : Ue75beea55b11371666701e214bc07917
     * messages : [{"type":"text","text":"Hello, world1"},{"type":"text","text":"Hello, world2"}]
     */

    private String to;
    private ArrayList<MessagesBean> messages;

    public RequestPushMessage(String to, ArrayList<MessagesBean> messages) {
        this.to = to;
        this.messages = messages;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public ArrayList<MessagesBean> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessagesBean> messages) {
        this.messages = messages;
    }

    public static class MessagesBean {
        /**
         * type : text
         * text : Hello, world1
         */

        private String type;
        private String text;

        public MessagesBean(String type, String text) {
            this.type = type;
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
