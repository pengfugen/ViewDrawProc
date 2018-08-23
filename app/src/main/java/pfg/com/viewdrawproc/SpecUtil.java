package pfg.com.viewdrawproc;

import android.view.View.MeasureSpec;

/**
 * Created by fpeng3 on 2018/8/22.
 */

public class SpecUtil {
    public static String getModeString(int mode) {
        if(MeasureSpec.UNSPECIFIED == mode) {
            return "unspecified";
        } else if(MeasureSpec.AT_MOST == mode) {
            return "at_most";
        } else if(MeasureSpec.EXACTLY == mode) {
            return "exactly";
        } else {
            return "null";
        }
    }

}
