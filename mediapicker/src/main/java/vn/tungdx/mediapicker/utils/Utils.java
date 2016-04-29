package vn.tungdx.mediapicker.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

import vn.tungdx.mediapicker.R;

public class Utils {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static int getActionbarHeight(Activity activity) {
        int attr;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            attr = android.R.attr.actionBarSize;
        } else {
            attr = R.attr.actionBarSize;
        }
        final TypedArray styledAttributes = activity.getTheme()
                .obtainStyledAttributes(new int[]{attr});
        int actionbarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return actionbarSize;
    }

    public static File createTempFile(Context context) throws IOException {
        if (!hasExternalStorage()) {
            return createTempFile(context, context.getCacheDir());
        } else {
            return createTempFile(context,
                    context.getExternalFilesDir("caches"));
        }
    }

    public static boolean hasExternalStorage() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static File createTempFile(Context context, File folder)
            throws IOException {
        String prefix = String.valueOf(System.currentTimeMillis());
        return File.createTempFile(prefix, null, folder);
    }
}
