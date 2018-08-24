package pfg.com.viewdrawproc;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.jar.Attributes;

/**
 * Created by fpeng3 on 2018/8/22.
 */

public class MyLinearLayout extends LinearLayout {

    MyLinearLayout(Context context) {
        super(context);
    }

    MyLinearLayout(Context context, AttributeSet attSet) {
        super(context, attSet);
    }

    MyLinearLayout(Context context, AttributeSet attSet, int defStyleAttr) {
        super(context, attSet, defStyleAttr);
    }

    MyLinearLayout(Context context, AttributeSet attSet, int defStyleAttr, int defStyleRes) {
        super(context, attSet, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String widthmode = SpecUtil.getModeString(MeasureSpec.getMode(widthMeasureSpec));
        String heightmode = SpecUtil.getModeString(MeasureSpec.getMode(heightMeasureSpec));
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        boolean isHorizontal = (getOrientation() == LinearLayout.HORIZONTAL);
        String TAG = isHorizontal ? "LinearLayout [horizontal]" : "LinearLayout [vertical]";
        MyLog.logd(TAG, "[width: "+width+"  "+widthmode+",  height: "+height+"  "+heightmode+"]");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Debug.startMethodTracing(TAG+" onMeasure ");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        boolean isHorizontal = (getOrientation() == LinearLayout.HORIZONTAL);
        String TAG = isHorizontal ? "LinearLayout [horizontal]" : "LinearLayout [vertical]";
        MyLog.logd(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        boolean isHorizontal = (getOrientation() == LinearLayout.HORIZONTAL);
        String TAG = isHorizontal ? "LinearLayout [horizontal]" : "LinearLayout [vertical]";
        MyLog.logd(TAG, "onDraw");
        super.onDraw(canvas);
        //Debug.stopMethodTracing();
    }

}
