package vn.tungdx.mediapicker;

import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import vn.tungdx.mediapicker.utils.MediaUtils;

public class MediaOptions implements Parcelable {
  private boolean canSelectMultiPhoto;
  private boolean canSelectMultiVideo;
  private boolean isCropped;
  private int maxVideoDuration;
  private int minVideoDuration;
  private boolean canSelectPhoto;
  private boolean canSelectVideo;
  private File photoCaptureFile;
  private int aspectX;
  private int aspectY;
  private boolean fixAspectRatio;
  private File croppedFile;
  private int imageSize;
  private List<MediaItem> mediaListSelected = new ArrayList<MediaItem>();
  private boolean showWarningVideoDuration;

  public boolean isShowWarningVideoDuration() {
    return showWarningVideoDuration;
  }

  public List<MediaItem> getMediaListSelected() {
    return mediaListSelected;
  }

  public File getCroppedFile() {
    return croppedFile;
  }

  public int getAspectX() {
    return aspectX;
  }

  public int getAspectY() {
    return aspectY;
  }

  public int getImageSize() {
    return imageSize;
  }

  public boolean isFixAspectRatio() {
    return fixAspectRatio;
  }

  public boolean canSelectMultiPhoto() {
    return canSelectMultiPhoto;
  }

  public boolean canSelectMultiVideo() {
    return canSelectMultiVideo;
  }

  public boolean isCropped() {
    return isCropped;
  }

  public int getMaxVideoDuration() {
    return maxVideoDuration;
  }

  public int getMinVideoDuration() {
    return minVideoDuration;
  }

  public boolean canSelectPhotoAndVideo() {
    return canSelectPhoto && canSelectVideo;
  }

  public boolean canSelectPhoto() {
    return canSelectPhoto;
  }

  public boolean canSelectVideo() {
    return canSelectVideo;
  }

  public File getPhotoFile() {
    return photoCaptureFile;
  }

  private MediaOptions(Builder builder) {
    this.canSelectMultiPhoto = builder.canSelectMultiPhoto;
    this.canSelectMultiVideo = builder.canSelectMultiVideo;
    this.isCropped = builder.isCropped;
    this.maxVideoDuration = builder.maxVideoDuration;
    this.minVideoDuration = builder.minVideoDuration;
    this.canSelectPhoto = builder.canSelectPhoto;
    this.canSelectVideo = builder.canSelectVideo;
    this.photoCaptureFile = builder.photoFile;
    this.aspectX = builder.aspectX;
    this.aspectY = builder.aspectY;
    this.fixAspectRatio = builder.fixAspectRatio;
    this.croppedFile = builder.croppedFile;
    this.imageSize = builder.imageSize;
    this.mediaListSelected = builder.mediaListSelected;
    this.showWarningVideoDuration = builder.showWarningBeforeRecord;
  }

  public static MediaOptions createDefault() {
    return new Builder().build();
  }

  public static class Builder {
    private boolean canSelectMultiPhoto = false;
    private boolean canSelectMultiVideo = false;
    private boolean isCropped = false;
    private int maxVideoDuration = Integer.MAX_VALUE;
    private int minVideoDuration = 0;
    private boolean canSelectPhoto = true;
    private boolean canSelectVideo = false;
    private File photoFile;
    private int aspectX = 1;
    private int aspectY = 1;
    private boolean fixAspectRatio = true;
    private File croppedFile;
    private int imageSize;
    private List<MediaItem> mediaListSelected;
    private boolean showWarningBeforeRecord = false;

    public Builder() {
    }

    /**
     * Should set this option = true when support multi devices and OS
     * version and set {@link #setMaxVideoDuration(int)} options. (HTC
     * devices usually can not limit video's duration when record, should
     * show message warning before record)
     *
     * @param showWarningBeforeRecord Default is false.
     */
    public Builder setShowWarningBeforeRecordVideo(boolean showWarningBeforeRecord) {
      this.showWarningBeforeRecord = showWarningBeforeRecord;
      return this;
    }

    /**
     * Set media list that already selected before.
     *
     * @param mediaSelecteds Media list selected before.
     */
    public Builder setMediaListSelected(List<MediaItem> mediaSelecteds) {
      this.mediaListSelected = mediaSelecteds;
      return this;
    }

    /**
     * @param file Use for save image that cropped. Default image cropped
     * saved in file that created by
     * {@link Utils#createTempFile(android.content.Context)}
     * <p/>
     * <i>Note: file should not exist when pass to this method.
     * Because if user cancels capture photo, this file will be
     * temporary.</i>
     */
    public Builder setCroppedFile(File croppedFile) {
      this.croppedFile = croppedFile;
      return this;
    }

    /**
     * Sets whether the aspect ratio is fixed or not; true fixes the aspect
     * ratio, while false allows it to be changed.
     *
     * @param fixAspectRatio Default is true.
     */
    public Builder setFixAspectRatio(boolean fixAspectRatio) {
      this.fixAspectRatio = fixAspectRatio;
      return this;
    }

    /**
     * @param aspectX Default is 1.
     */
    public Builder setAspectX(int aspectX) {
      this.aspectX = aspectX;
      return this;
    }

    /**
     * @param aspectY Default is 1.
     */
    public Builder setAspectY(int aspectY) {
      this.aspectY = aspectY;
      return this;
    }

    /**
     * Crop photo before return back photo. Not effective for video or set
     * {@link MediaOptions.Builder#canSelectMultiPhoto(boolean)} method's
     * parameter =true.
     */
    public Builder setIsCropped(boolean isCropped) {
      this.isCropped = isCropped;
      return this;
    }

    /**
     * Set can select multiple photos or not. If true then
     * {@link #setIsCropped(boolean)} option will be ignored.
     */
    public Builder canSelectMultiPhoto(boolean canSelectMulti) {
      this.canSelectMultiPhoto = canSelectMulti;
      return this;
    }

    /**
     * Select multiple videos. If true, videos haven't check duration. It's
     * override {@link #setMaxVideoDuration(int)} and
     * {@link #setMinVideoDuration(int)}
     */
    public Builder canSelectMultiVideo(boolean canSelectMulti) {
      this.canSelectMultiVideo = canSelectMulti;
      if (canSelectMultiVideo) {
        maxVideoDuration = Integer.MAX_VALUE;
        minVideoDuration = 0;
      }
      return this;
    }

    /**
     * Set max video's duration. If set, can't select multiple videos. It's
     * override {@link #canSelectMultiVideo(boolean)} option.
     *
     * @param maxDuration in milliseconds.
     * @throws IllegalArgumentException if maxDuration <=0
     * @see #setMinVideoDuration(int)
     */
    public Builder setMaxVideoDuration(int maxDuration) {
      if (maxDuration <= 0) {
        throw new IllegalArgumentException("Max duration must be > 0");
      }
      this.maxVideoDuration = maxDuration;
      this.canSelectMultiVideo = false;
      return this;
    }

    /**
     * Set min video's duration. If set, can't select multi videos. It's
     * override {@link #canSelectMultiVideo(boolean)} option.
     *
     * @param minDuration in milliseconds.
     * @throws IllegalArgumentException if minDuration <=0
     * @see #setMaxVideoDuration(int)
     */
    public Builder setMinVideoDuration(int minDuration) {
      if (minDuration <= 0) {
        throw new IllegalArgumentException("Min duration must be > 0");
      }
      this.minVideoDuration = minDuration;
      this.canSelectMultiVideo = false;
      return this;
    }

    /**
     * Can choose photo or video. Default only choose photo.
     */
    public Builder canSelectBothPhotoVideo() {
      canSelectPhoto = true;
      canSelectVideo = true;
      return this;
    }

    /**
     * Only choose video. This method override {@link #selectPhoto()} if set
     * before. Default only choose photo.
     */
    public Builder selectVideo() {
      canSelectVideo = true;
      canSelectPhoto = false;
      return this;
    }

    /**
     * Only choose photo. This method override {@link #selectVideo()} if set
     * before.
     */
    public Builder selectPhoto() {
      canSelectPhoto = true;
      canSelectVideo = false;
      return this;
    }

    /**
     * @param file Use for save image that capture from camera. Default image
     * saved in {@link Environment#DIRECTORY_PICTURES} folder of
     * device, file create by
     * {@link MediaUtils#createDefaultImageFile()}<br/>
     * <b>Note:</b><br/>
     * - File should not exist when pass to this method. Because
     * if use cancle capture photo, this file will be temporary.<br/>
     * - In HTC devices (maybe others) this options not true,
     * image isn't saved in this file, it's saved by folder
     * default of camera.
     */
    public Builder setPhotoCaptureFile(File file) {
      photoFile = file;
      return this;
    }

    public Builder setImageSize(int size) {
      imageSize = size;
      return this;
    }

    /**
     * Build configured {@link MediaOptions} object.
     */
    public MediaOptions build() {
      return new MediaOptions(this);
    }
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(canSelectMultiPhoto ? 1 : 0);
    dest.writeInt(canSelectMultiVideo ? 1 : 0);
    dest.writeInt(canSelectPhoto ? 1 : 0);
    dest.writeInt(canSelectVideo ? 1 : 0);
    dest.writeInt(isCropped ? 1 : 0);
    dest.writeInt(fixAspectRatio ? 1 : 0);
    dest.writeInt(showWarningVideoDuration ? 1 : 0);
    dest.writeInt(this.maxVideoDuration);
    dest.writeInt(this.minVideoDuration);
    dest.writeInt(aspectX);
    dest.writeInt(aspectY);
    dest.writeSerializable(photoCaptureFile);
    dest.writeSerializable(croppedFile);
    dest.writeInt(imageSize);
    dest.writeTypedList(mediaListSelected);
  }

  public MediaOptions(Parcel in) {
    canSelectMultiPhoto = in.readInt() != 0;
    canSelectMultiVideo = in.readInt() != 0;
    canSelectPhoto = in.readInt() != 0;
    canSelectVideo = in.readInt() != 0;
    isCropped = in.readInt() != 0;
    fixAspectRatio = in.readInt() != 0;
    showWarningVideoDuration = in.readInt() != 0;
    this.maxVideoDuration = in.readInt();
    this.minVideoDuration = in.readInt();
    aspectX = in.readInt();
    aspectY = in.readInt();
    this.photoCaptureFile = (File) in.readSerializable();
    this.croppedFile = (File) in.readSerializable();
    this.imageSize = in.readInt();
    in.readTypedList(this.mediaListSelected, MediaItem.CREATOR);
  }

  public static final Creator<MediaOptions> CREATOR = new Creator<MediaOptions>() {

    @Override public MediaOptions[] newArray(int size) {
      return new MediaOptions[size];
    }

    @Override public MediaOptions createFromParcel(Parcel source) {
      return new MediaOptions(source);
    }
  };

  @Override public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (canSelectMultiPhoto ? 1231 : 1237);
    result = prime * result + (canSelectPhoto ? 1231 : 1237);
    result = prime * result + (canSelectVideo ? 1231 : 1237);
    result = prime * result + (isCropped ? 1231 : 1237);
    result = prime * result + maxVideoDuration;
    result = prime * result + minVideoDuration;
    return result;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    MediaOptions other = (MediaOptions) obj;
    if (canSelectMultiPhoto != other.canSelectMultiPhoto) return false;
    if (canSelectPhoto != other.canSelectPhoto) return false;
    if (canSelectVideo != other.canSelectVideo) return false;
    if (isCropped != other.isCropped) return false;
    if (maxVideoDuration != other.maxVideoDuration) return false;
    if (minVideoDuration != other.minVideoDuration) return false;
    return true;
  }
}