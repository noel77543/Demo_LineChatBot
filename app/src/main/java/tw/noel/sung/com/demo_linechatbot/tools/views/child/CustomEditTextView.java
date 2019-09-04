package tw.noel.sung.com.demo_linechatbot.tools.views.child;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.InputType;

import tw.noel.sung.com.demo_linechatbot.R;


/**
 * Created by noel on 2018/12/26.
 */
public class CustomEditTextView extends android.support.v7.widget.AppCompatEditText {
    private final int TEXT_SIZE = 14;

    public CustomEditTextView(Context context) {
        super(context);
        setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        setTextSize(TEXT_SIZE);

    }

    //--------

    /***
     * focus 邏輯
     * @param isFocus
     */

    public void setFocus(boolean isFocus, String editHint) {
        if (isFocus) {
            setBottomLineColor(R.color.colorAccent);
        } else {
            editHint = "";
            setBottomLineColor(R.color.clear_button);
        }
        setHint(editHint);
    }


    //-------

    /***
     * 更改下方線顏色
     */
    public void setBottomLineColor(int colorResource) {
        getBackground().mutate().setColorFilter(getResources().getColor(colorResource), PorterDuff.Mode.SRC_ATOP);
    }
}
