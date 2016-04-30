# android-media-picker
A library for choose photos, videos from Android devices.

Here are some of the features of the Android Media Picker Library:

* 1) Allows you to pick one or multiple photos or videos
* 2) Options to crop photo.
* 3) Options for pick video.
* 4) Use with Activity or Fragment.


## Code

### Single image and select Cut

```Java
MediaOptions.Builder builders = new MediaOptions.Builder();
        MediaOptions options =
            builders.setIsCropped(true).setFixAspectRatio(true).setImageSize(1).build();
        MediaPickerActivity.open(this, REQUEST_MEDIA, options, false);
```
### Select multiple pictures

```Java
  MediaOptions.Builder builder = new MediaOptions.Builder();
        MediaOptions options1 =
            builder.canSelectMultiPhoto(true).setMediaListSelected(items).setImageSize(9).build();
        MediaPickerActivity.open(this, REQUEST_MEDIA, options1, false);
```
### Photographed and cut

```Java
MediaOptions.Builder builders2 = new MediaOptions.Builder();
        MediaOptions options2 =
            builders2.setIsCropped(true).setFixAspectRatio(true).setImageSize(1).build();
        MediaPickerActivity.open(this, REQUEST_PHOTOGRAPH, options2, true);
```

## How do I use it?

### Gradle
```xml
<dependency>
  <groupId>com.kevinbas</groupId>
  <artifactId>android-media-picker</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
### Maven
```xml
compile 'com.kevinbas:android-media-picker:1.0.0'
```


