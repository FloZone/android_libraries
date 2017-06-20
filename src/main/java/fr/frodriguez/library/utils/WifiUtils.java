package fr.frodriguez.library.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

import static android.content.Context.WIFI_SERVICE;

/**
 * By FloZone on 18/02/2017.
 */

@SuppressWarnings("WeakerAccess")
public class WifiUtils {

    public enum WifiState {
        WIFI_OFF,
        WIFI_CONNECTED,
        WIFI_NOT_CONNECTED
    }


    /**
     * Get the device Wifi IP address.
     * Requires {@link android.Manifest.permission#ACCESS_WIFI_STATE}.
     */
    public static String getWifiIPAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

        // Convert little-endian to big-endianif needed
        if(ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            Log.e("getWifiIPAddress", "Unable to get host address.");
            ipAddressString = null;
        }

        return ipAddressString;
    }

    /**
     * Get the Wifi state.
     * Requires {@link android.Manifest.permission#ACCESS_WIFI_STATE}.
     */
    public static WifiState getWifiState(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // Wifi is ON
        if(wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            // Not connected to an access point
            if(wifiInfo.getNetworkId() == -1) {
                return WifiState.WIFI_NOT_CONNECTED;
            }
            // Connected to an access point
            else {
                return WifiState.WIFI_CONNECTED;
            }
        }
        // Wifi is OFF
        else {
            return WifiState.WIFI_OFF;
        }
    }

}
