package pfg.com.viewdrawproc;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Debug;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by fpeng3 on 2018/8/22.
 */

public class Title extends TextView {

    public String TAG = "Title";

    public Title(Context context) {
        super(context);
    }

    public Title(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Title(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Title(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //setText("Shock Spock");
        //setTextSize(20.0f);
    }

    protected void setTag(String tag) {
        TAG = tag;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        String widthmode = SpecUtil.getModeString(MeasureSpec.getMode(widthMeasureSpec));
        String heightmode = SpecUtil.getModeString(MeasureSpec.getMode(heightMeasureSpec));
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        MyLog.logd(TAG, "[width: "+width+"  "+widthmode+",  height: "+height+"  "+heightmode+"]");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        MyLog.logd(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        MyLog.logd(TAG, "onDraw");
    }
}
