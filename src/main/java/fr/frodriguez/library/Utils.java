package fr.frodriguez.library;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * By Florian on 22/01/2017.
 */

public class Utils {

    /**
     * Get the application version and the application name in a string
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        String version = "?";
        if(context != null) {
            try {
                version = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                version += "-" + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                Logger.e(Utils.class.getName()+".getAppVersion()", context, "Version name not found");
            }
        }
        return version;
    }

    /**
     * Get the application name
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }
}
