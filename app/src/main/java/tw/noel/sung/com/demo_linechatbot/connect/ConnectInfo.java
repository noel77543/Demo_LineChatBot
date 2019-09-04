package tw.noel.sung.com.demo_linechatbot.connect;

public class ConnectInfo {

    private final static String _DOMAIN = "https://api.line.me/v2";
    public static final String _POST_ACCESS_TOKEN = _DOMAIN+"/oauth/accessToken";
    //一對一 , 一對群組
    public static final String _POST_PUSH_MESSAGE = _DOMAIN+"/bot/message/push";
    //廣播
    public static final String _POST_BROADCAST_MESSAGE = _DOMAIN+"/bot/message/broadcast";


}
