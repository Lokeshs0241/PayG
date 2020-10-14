package com.payg;

import android.nfc.Tag;
import android.util.Log;

public class LogDebug {
    private static final String TAG = "PAYG_APP";

    public static void d(String message){
        Log.d(TAG,message);
    }
}
