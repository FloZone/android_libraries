package fr.frodriguez.library.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * By FloZone on 22/01/2017.
 */
public class MessageUtils {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
