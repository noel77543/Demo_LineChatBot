package tw.noel.sung.com.demo_linechatbot.tools.views.child;

import android.content.Context;

import tw.noel.sung.com.demo_linechatbot.R;


/**
 * Created by noel on 2018/12/26.
 */
public class CustomClearButton extends android.support.v7.widget.AppCompatButton {

    public CustomClearButton(Context context) {
        super(context);
        init();
    }

    //-------
    private void init() {
        setBackground(getResources().getDrawable(R.drawable.ic_clear));
    }


    //--------

    /***
     * focus的時候縮小 反之放大
     * @param isFocus
     */
    public void setFocus(boolean isFocus, boolean isInput) {
        setVisibility(isFocus && isInput ? VISIBLE : INVISIBLE);
    }
}
