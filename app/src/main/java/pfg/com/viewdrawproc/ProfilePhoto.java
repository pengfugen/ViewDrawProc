package pfg.com.viewdrawproc;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Debug;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by fpeng3 on 2018/8/22.
 */

public class ProfilePhoto extends ImageView {

    public String TAG = "ProfilePhoto";

    public ProfilePhoto(Context context) {
        super(context);
    }

    public ProfilePhoto(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProfilePhoto(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProfilePhoto(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //setBackground(getResources().getDrawable(R.drawable.photo));
    }

    protected void setTag(String tag) {
        TAG = tag;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String widthmode = SpecUtil.getModeString(MeasureSpec.getMode(widthMeasureSpec));
        String heightmode = SpecUtil.getModeString(MeasureSpec.getMode(heightMeasureSpec));
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        MyLog.logd(TAG, "[width: "+width+"  "+widthmode+",  height: "+height+"  "+heightmode+"]");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //Debug.startMethodTracing(TAG+" onMeasure ");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        MyLog.logd(TAG, "onLayout left:"+left+", top="+top+", right="+right+", bottom="+bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        MyLog.logd(TAG, "onDraw");
        //Debug.stopMethodTracing();
    }
}
