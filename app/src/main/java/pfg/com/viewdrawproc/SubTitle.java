package pfg.com.viewdrawproc;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by fpeng3 on 2018/8/22.
 */

public class SubTitle extends Title {

    public String TAG = "SubTitle";

    public SubTitle(Context context) {
        super(context);
        setTag(TAG);
    }

    public SubTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTag(TAG);
    }

    public SubTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTag(TAG);
    }

    public SubTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //setText("2rh, shanghai");
        //setTextSize(12.0f);
        setTag(TAG);
    }
}
