package tw.noel.sung.com.demo_linechatbot.tools.views.child;

import android.content.Context;
import android.view.Gravity;

public class CustomMessageTextView extends android.support.v7.widget.AppCompatTextView {

    private final int TEXT_SIZE = 10;

    public CustomMessageTextView(Context context) {
        super(context);
        setTextSize(TEXT_SIZE);
    }
    //--------

    /***
     * 左側訊息
     */
    public void setLeftMessage(String message, int colorRes) {
        setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        setText(message);
        setTextColor(getResources().getColor(colorRes));
    }

    //--------

    /***
     * 右側訊息
     */
    public void setRightMessage(String message, int colorRes) {
        setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        setText(message);
        setTextColor(getResources().getColor(colorRes));

    }

}
