package fr.frodriguez.library.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import androidx.annotation.NonNull;

import fr.frodriguez.library.Logger;
import fr.frodriguez.library.ShellCommand;

/**
 * By FloZone on 22/01/2017.
 */
@SuppressWarnings("unused")
public final class AppUtils {

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

    /**
     * Return the current device battery level or -1 if an error occurs
     */
    public static int getBatteryLevel(@NonNull Context context) {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryIntent = context.registerReceiver(null, intentFilter);
        if(batteryIntent == null) return -1;
        else return batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
    }

    /**
     * Switch on/off bluetooth
     */
    public static void switchBluetooth(boolean enable) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (enable && !bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        } else if (!enable && bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        }
    }

}
