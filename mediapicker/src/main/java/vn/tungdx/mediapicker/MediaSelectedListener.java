package vn.tungdx.mediapicker;

import java.util.List;

public interface MediaSelectedListener {
    public void onHasNoSelected();

    public void onHasSelected(List<MediaItem> mediaSelectedList);
}
