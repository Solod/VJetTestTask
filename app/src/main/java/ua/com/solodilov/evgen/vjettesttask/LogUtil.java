package ua.com.solodilov.evgen.vjettesttask;

import android.util.Log;

public class LogUtil {
    public static void info(Object o, String message){
        if (o instanceof String) {
            Log.d(o.toString(), message);
        } else {
            Log.d(o.getClass().getName(), message);
        }
    }

    public static void info(String message){
        Log.d("Look!: ", message);
    }
}
