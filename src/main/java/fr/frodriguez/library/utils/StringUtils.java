package fr.frodriguez.library.utils;

import android.support.annotation.NonNull;

/**
 * By Florian on 22/01/2017.
 */

public class StringUtils {

    /**
     * Return true if the string is null or empty
     */
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * Return the string value or an empty value if the string is null
     */
    @NonNull
    public static String getValue(String str) {
        return str == null ? "" : str;
    }
}
