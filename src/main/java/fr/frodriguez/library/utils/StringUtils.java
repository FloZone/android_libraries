package fr.frodriguez.library.utils;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * By FloZone on 22/01/2017.
 */
public final class StringUtils {

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

    /**
     * Get the string value of an input stream
     * @throws IOException
     */
    public static String getStringValue(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        int read;
        char[] buffer = new char[4096];
        StringBuffer out = new StringBuffer();
        while((read = br.read(buffer))>0){
            out.append(buffer,0,read);
        }
        br.close();

        return out.toString();
    }
}
