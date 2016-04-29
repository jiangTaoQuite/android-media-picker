package vn.tungdx.mediapicker.utils;

import android.content.Context;
import vn.tungdx.mediapicker.R;

public class MessageUtils {

    public static String getWarningMessageVideoDuration(Context context,
                                                        int maxDuration) {
        return context.getResources().getQuantityString(
                R.plurals.picker_video_duration_warning, maxDuration, maxDuration);
    }

    public static String getInvalidMessageMaxVideoDuration(Context context,
                                                           int maxDuration) {
        return context.getResources().getQuantityString(
                R.plurals.picker_video_duration_max, maxDuration, maxDuration);
    }

    public static String getInvalidMessageMinVideoDuration(Context context,
                                                           int minDuration) {
        return context.getResources().getQuantityString(
                R.plurals.picker_video_duration_min, minDuration, minDuration);
    }
}