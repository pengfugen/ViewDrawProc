package pfg.com.viewdrawproc;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by fpeng3 on 2018/8/23.
 */

public class MyRelativeLayout extends RelativeLayout {

    private static final String TAG = "MyRelativeLayout";

    MyRelativeLayout(Context context) {
        super(context);
    }

    MyRelativeLayout(Context context, AttributeSet attSet) {
        super(context, attSet);
    }

    MyRelativeLayout(Context context, AttributeSet attSet, int defStyleAttr) {
        super(context, attSet, defStyleAttr);
    }

    MyRelativeLayout(Context context, AttributeSet attSet, int defStyleAttr, int defStyleRes) {
        super(context, attSet, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String widthmode = SpecUtil.getModeString(MeasureSpec.getMode(widthMeasureSpec));
        String heightmode = SpecUtil.getModeString(MeasureSpec.getMode(heightMeasureSpec));
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        MyLog.logd(TAG, "[width: "+width+"  "+widthmode+",  height: "+height+"  "+heightmode+"]");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        MyLog.logd(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MyLog.logd(TAG, "onDraw");
        super.onDraw(canvas);
        //Debug.stopMethodTracing();
    }

    // drawChild流程走了，为什么child view的onDraw没有走进去？
    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        MyLog.logd(TAG, "drawChild child:"+child.getId());
        return super.drawChild(canvas, child, drawingTime);
    }
}
