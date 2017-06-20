package fr.frodriguez.library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

import fr.frodriguez.library.Logger;
import fr.frodriguez.library.ShellCommand;

import static android.content.Context.WIFI_SERVICE;

/**
 * By FloZone on 22/01/2017.
 */
@SuppressWarnings("unused")
public class AppUtils {

    /**
     * Get the application version & name
     */
    public static String getAppVersion(Context context) {
        String version = "?";
        if(context != null) {
            try {
                version = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName +  " - " + version;
            } catch (PackageManager.NameNotFoundException e) {
                Logger.e(AppUtils.class.getName()+".getAppVersion()", context, "Version name not found");
            }
        }
        return version;
    }

    /**
     * Get the application name
     */
    public static String getAppName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }


    /**
     * Return whether the root permission is granted
     */
    public static boolean isRootPermissionGranted() {
        return !ShellCommand.runAsRoot(new String[]{"id"}).isError();
    }

    /**
     * Return whether the device is connected to internet or not.
     * Requires {@link android.Manifest.permission#ACCESS_NETWORK_STATE}.
     */
    public static boolean hasInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
