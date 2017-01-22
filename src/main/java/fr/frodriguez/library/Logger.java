package fr.frodriguez.library;

import android.content.Context;
import android.util.Log;

/**
 * By Florian on 22/01/2017.
 */

public class Logger {

    private final static String PREFIX = "flz";
    private String _tag;

    public Logger(String appName, Context context) {
        _tag = buildLogTag(appName, Utils.getAppVersion(context));
    }

    private static String buildLogTag(String appName, String version) {
        return PREFIX + "_" + appName + ">" + version;
    }

    public void d(String message) {
        Log.d(_tag, message);
    }
    public static void d(String appName, Context context, String message) {
        Log.d(buildLogTag(appName, Utils.getAppVersion(context)), message);
    }

    public void e(String message) {
        Log.e(_tag, message);
    }
    public void e(String message, Exception exception) {
        Log.e(_tag, message, exception);
    }
    public static void e(String appName, Context context, String message) {
        Log.e(buildLogTag(appName, Utils.getAppVersion(context)), message);
    }
    public static void e(String appName, Context context, String message, Exception exception) {
        Log.e(buildLogTag(appName, Utils.getAppVersion(context)), message, exception);
    }

    public void i(String message) {
        Log.i(_tag, message);
    }
    public static void i(String appName, Context context, String message) {
        Log.i(buildLogTag(appName, Utils.getAppVersion(context)), message);
    }
}
