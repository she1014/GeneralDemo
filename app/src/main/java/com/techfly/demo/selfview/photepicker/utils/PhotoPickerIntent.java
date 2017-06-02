package com.techfly.demo.selfview.photepicker.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.techfly.demo.selfview.photepicker.PhotoPickerActivity;


/**
 * Created by donglua on 15/7/2.
 */
public class PhotoPickerIntent extends Intent {

  private PhotoPickerIntent() {
  }

  private PhotoPickerIntent(Intent o) {
    super(o);
  }

  private PhotoPickerIntent(String action) {
    super(action);
  }

  private PhotoPickerIntent(String action, Uri uri) {
    super(action, uri);
  }

  private PhotoPickerIntent(Context packageContext, Class<?> cls) {
    super(packageContext, cls);
  }

  public PhotoPickerIntent(Context packageContext) {
    super(packageContext, PhotoPickerActivity.class);
  }

  public void setPhotoCount(int photoCount) {
    this.putExtra(PhotoPickerActivity.EXTRA_MAX_COUNT, photoCount);
  }


  public void setShowCamera(boolean showCamera) {
    this.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, showCamera);
  }

  public void setSingleSelect(boolean singleSelect) {
    this.putExtra(PhotoPickerActivity.EXTRA_SINGLE_SELECT, singleSelect);
  }

  public void setCaptureReturn(boolean captureReturn){
    this.putExtra(PhotoPickerActivity.EXTRA_CAPTURE_RETURN, captureReturn);
  }


}
