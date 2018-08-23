package pfg.com.viewdrawproc;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by fpeng3 on 2018/8/22.
 */

public class Menu extends ProfilePhoto {
    public String TAG = "Menu";

    public Menu(Context context) {
        super(context);
        setTag(TAG);
    }

    public Menu(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTag(TAG);
    }

    public Menu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTag(TAG);
    }

    public Menu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {


        super(context, attrs, defStyleAttr, defStyleRes);
        //setBackground(getResources().getDrawable(R.drawable.menu));
        setTag(TAG);
    }

}
