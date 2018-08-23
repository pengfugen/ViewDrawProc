package pfg.com.viewdrawproc;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by fpeng3 on 2018/8/22.
 */

public class MyLog {
    private static final String PRE_TAG = "ViewDrawProc+/";

    public static void logd(String tag, String msg) {
        Log.d(PRE_TAG+tag, msg);
    }
}
