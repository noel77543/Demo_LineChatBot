package tw.noel.sung.com.demo_linechatbot.tools.views.child;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import java.util.List;

import tw.noel.sung.com.demo_linechatbot.R;

/**
 * Created by noel on 2018/12/26.
 */
public class CustomHintTextView extends android.support.v7.widget.AppCompatTextView implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    //動畫時間 毫秒
    private final int ANIMATION_TIME = 500;
    //一開始  一般狀態的文字尺寸
    private final int NORMAL_TEXT_SIZE = 20;
    //縮小的文字尺寸
    private final int SMALL_TEXT_SIZE = 10;

    //變小
    private ValueAnimator valueAnimatorSmaller;
    //變大
    private ValueAnimator valueAnimatorLarger;
    //是否focus
    private boolean isFocus;
    //最終下方間距
    public static final float MAX_BOTTOM_MARGIN = 70f;

    private boolean isShouldRunOnHWLayer = false;

    public CustomHintTextView(Context context) {
        super(context);
        init();
    }

    //--------
    private void init() {
        setTextColor(getResources().getColor(R.color.clear_button));
        setTextSize(NORMAL_TEXT_SIZE);

        valueAnimatorSmaller = ValueAnimator.ofFloat(NORMAL_TEXT_SIZE, SMALL_TEXT_SIZE);
        valueAnimatorSmaller.setDuration(ANIMATION_TIME);
        valueAnimatorSmaller.addUpdateListener(this);
        valueAnimatorSmaller.addListener(this);

        valueAnimatorLarger = ValueAnimator.ofFloat(SMALL_TEXT_SIZE, NORMAL_TEXT_SIZE);
        valueAnimatorLarger.setDuration(ANIMATION_TIME);
        valueAnimatorLarger.addUpdateListener(this);
        valueAnimatorLarger.addListener(this);
    }

    //--------

    /***
     * focus的時候縮小 反之放大
     * @param isFocus
     */
    public void setFocus(boolean isFocus, boolean isInput) {
        this.isFocus = isFocus;

        //如果沒有字串
        if (!isInput) {
            if (isFocus) {
                setTextColor(getResources().getColor(R.color.colorAccent));
                valueAnimatorSmaller.start();
            } else {
                setTextColor(getResources().getColor(R.color.clear_button));
                valueAnimatorLarger.start();
            }
        }
//        //如果有字串
//        else {
//            setVisibility(isFocus ? VISIBLE : INVISIBLE);
//        }
    }

    //--------

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedValue = (float) valueAnimator.getAnimatedValue();
        setTextSize(animatedValue);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
//        params.bottomMargin = (int) (MAX_BOTTOM_MARGIN * (SMALL_TEXT_SIZE / animatedValue));


        params.bottomMargin = (int) (MAX_BOTTOM_MARGIN * (SMALL_TEXT_SIZE / animatedValue));


    }

    //--------

    private boolean shouldRunOnHWLayer(View view, Animator animator) {
        if (view == null || animator == null) {
            return false;
        }
        return view.getLayerType() == View.LAYER_TYPE_NONE
                && view.hasOverlappingRendering()
                && modifiesAlpha(animator);

    }


    //--------


    private boolean modifiesAlpha(Animator animator) {
        if (animator == null) {
            return false;
        }
        if (animator instanceof ValueAnimator) {
            ValueAnimator valueAnim = (ValueAnimator) animator;
            PropertyValuesHolder[] values = valueAnim.getValues();
            for (int i = 0; i < values.length; i++) {
                if (("alpha").equals(values[i].getPropertyName())) {
                    return true;
                }
            }
        } else if (animator instanceof AnimatorSet) {
            List<Animator> animList = ((AnimatorSet) animator).getChildAnimations();
            for (int i = 0; i < animList.size(); i++) {
                if (modifiesAlpha(animList.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    //----------

    @Override
    public void onAnimationStart(Animator animation) {
        isShouldRunOnHWLayer = shouldRunOnHWLayer(this, isFocus ? valueAnimatorSmaller : valueAnimatorLarger);
        if (isShouldRunOnHWLayer) {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }
    //----------

    @Override
    public void onAnimationEnd(Animator animation) {
        if (isShouldRunOnHWLayer) {
            setLayerType(View.LAYER_TYPE_NONE, null);
        }

        valueAnimatorSmaller.removeListener(this);
        valueAnimatorLarger.removeListener(this);

    }
    //----------

    @Override
    public void onAnimationCancel(Animator animation) {

    }
    //----------

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
