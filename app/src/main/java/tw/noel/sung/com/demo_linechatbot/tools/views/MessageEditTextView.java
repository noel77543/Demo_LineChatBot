package tw.noel.sung.com.demo_linechatbot.tools.views;

import android.content.Context;
import android.support.annotation.IntDef;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import tw.noel.sung.com.demo_linechatbot.tools.views.child.CustomClearButton;
import tw.noel.sung.com.demo_linechatbot.tools.views.child.CustomEditTextView;
import tw.noel.sung.com.demo_linechatbot.tools.views.child.CustomHintTextView;
import tw.noel.sung.com.demo_linechatbot.tools.views.child.CustomMessageTextView;

/**
 * Created by noel on 2018/12/25.
 * 具備訊息說明狀態的動畫輸入匡
 * todo 高度必須為60dp
 */
public class MessageEditTextView extends RelativeLayout implements TextWatcher, View.OnClickListener, View.OnFocusChangeListener {

    //輸入欄
    private CustomEditTextView customEditTextView;
    //輸入欄ID
    private int customEditTextViewID;
    //edit hint
    private String editHint;

    //清除鈕
    private CustomClearButton customClearButton;
    //清除鈕寬高
    private final int CLEAR_BUTTON_SIZE = 40;
    //清除鈕ID
    private int customClearButtonID;

    //動態hint
    private CustomHintTextView customHintTextView;
    //動態hint ID
    private int customHintTextViewID;

    //下方訊息
    private CustomMessageTextView customMessageTextView;
    //下方訊息 ID
    private int customMessageTextViewID;

    //輸入欄是否有字串
    private boolean isInput = false;
    private Context context;


    //輸入欄監聽接口
    private OnEditTextChangeListener onEditTextChangeListener;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({_INPUT_TYPE_TEXT, _INPUT_TYPE_PASSWORD})
    public @interface MessageEditTextInputType {
    }

    public static final int _INPUT_TYPE_TEXT = InputType.TYPE_CLASS_TEXT;
    public static final int _INPUT_TYPE_PASSWORD = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

    private int inputType = _INPUT_TYPE_TEXT;

    //--------------

    public MessageEditTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    //-------------

    /***
     *  初始化
     */
    private void init() {
        //使不自動聚焦
        setFocusable(true);
        setFocusableInTouchMode(true);

        initMessageTextView();
        initEditText();
        initClearButton();
        initHintTextView();

        addView(customMessageTextView);
        addView(customEditTextView);
        addView(customHintTextView);
        addView(customClearButton);

    }


    //------

    /***
     * 輸入匡
     */
    private void initEditText() {
        customEditTextView = new CustomEditTextView(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_START);
        params.addRule(ABOVE, customMessageTextViewID);
        params.bottomMargin = -20;
        customEditTextView.setLayoutParams(params);
        customEditTextView.setInputType(inputType);
        customEditTextViewID = View.generateViewId();
        customEditTextView.setId(customEditTextViewID);
        customEditTextView.addTextChangedListener(this);
        customEditTextView.setOnFocusChangeListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence text, int start, int before, int count) {
        isInput = text.length() > 0;
        customClearButton.setVisibility(isInput ? VISIBLE : INVISIBLE);
        customMessageTextView.setVisibility(INVISIBLE);
        if (onEditTextChangeListener != null) {
            onEditTextChangeListener.onEditTextChanged(text, start, before, count);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        customEditTextView.setFocus(hasFocus, editHint);
        customHintTextView.setFocus(hasFocus, isInput);
        customClearButton.setFocus(hasFocus, isInput);
    }

    //-------------

    /***
     * 清除鈕
     */
    private void initClearButton() {
        customClearButton = new CustomClearButton(context);
        LayoutParams params = new LayoutParams(CLEAR_BUTTON_SIZE, CLEAR_BUTTON_SIZE);
        params.addRule(RelativeLayout.ALIGN_END, customEditTextViewID);
        params.addRule(RelativeLayout.ALIGN_BOTTOM, customEditTextViewID);
        params.rightMargin = 25;
        params.bottomMargin = 45;
        customClearButtonID = View.generateViewId();
        customClearButton.setId(customClearButtonID);
        customClearButton.setLayoutParams(params);
        customClearButton.setOnClickListener(this);
        customClearButton.setVisibility(INVISIBLE);
    }
    //-------------

    @Override
    public void onClick(View v) {
        //清除鈕
        if (v.getId() == customClearButtonID) {
            customEditTextView.setText("");
        }
    }

    //---------

    /***
     * 初始化 動態hint  TextView
     */
    private void initHintTextView() {
        customHintTextView = new CustomHintTextView(context);
        customHintTextViewID = View.generateViewId();
        customHintTextView.setId(customHintTextViewID);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 80);
        params.addRule(RelativeLayout.ALIGN_START, customEditTextViewID);
        params.addRule(RelativeLayout.ALIGN_BOTTOM, customEditTextViewID);
        params.leftMargin = 10;
        params.bottomMargin = (int) CustomHintTextView.MAX_BOTTOM_MARGIN / 2;
        customHintTextView.setLayoutParams(params);
    }

    //--------

    /***
     * 下方訊息
     */
    private void initMessageTextView() {
        customMessageTextView = new CustomMessageTextView(context);
        customMessageTextViewID = View.generateViewId();
        customMessageTextView.setId(customMessageTextViewID);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_START);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.leftMargin = 10;
        params.rightMargin = 10;

        customMessageTextView.setLayoutParams(params);
        customMessageTextView.setVisibility(INVISIBLE);
    }

    //----------

    /***
     * 輸入欄接口
     */
    public interface OnEditTextChangeListener {
        void onEditTextChanged(CharSequence text, int start, int before, int count);
    }

    public void setOnEditTextChangeListener(OnEditTextChangeListener onEditTextChangeListener) {
        this.onEditTextChangeListener = onEditTextChangeListener;
    }

    //---------

    /***
     * 在客製化hint上  動態設置文字
     * @param text
     */
    public void setAnimateHintText(String text) {
        customHintTextView.setText(text);
    }

    //--------

    /***
     * 設置 輸入模式
     */
    public void setInputType(@MessageEditTextInputType int inputType) {
        customEditTextView.setInputType(inputType);
    }


    //--------

    /***
     * 在edit text上設置提示
     */
    public void setHintText(String editHint) {
        this.editHint = editHint;
    }

    //--------

    /***
     *  下方 訊息 - 左側
     */
    public void showLeftMessage(String message, int colorResource, int iconResource) {
        customMessageTextView.setVisibility(VISIBLE);
        customMessageTextView.setLeftMessage(message, colorResource);
        customMessageTextView.setCompoundDrawablesWithIntrinsicBounds(iconResource, 0, 0, 0);
    }
    //--------


    /***
     *  下方 訊息 - 右側
     */
    public void showRightMessage(String message, int colorResource) {
        customMessageTextView.setVisibility(VISIBLE);
        customMessageTextView.setRightMessage(message, colorResource);
    }
    //--------

    /***
     * 清空下方所有訊息
     */
    public void hideAllBottomMessage() {
        customMessageTextView.setVisibility(INVISIBLE);
    }


    //--------

    /***
     * 取得輸入字串
     */
    public String getTextToString() {
        return customEditTextView.getText().toString();
    }


    //----------

    /***
     * 設置輸入的字串
     */
    public void setEditText(String text) {
        customEditTextView.setFocus(true, editHint);
        customHintTextView.setFocus(true, isInput);
        customClearButton.setFocus(true, isInput);
        customEditTextView.setText(text);
    }
}
