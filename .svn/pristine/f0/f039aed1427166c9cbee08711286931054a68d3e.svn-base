package com.techfly.demo.selfview.photepicker.entity;

/**
 * Created by donglua on 15/6/30.
 */
public class Photo {

  private int id;
  private String path;
  private String thumbnailPath;

  public Photo(int id, String path) {
    this.id = id;
    this.path = path;
  }

  public Photo(int id ,String path,String thumbnailPath) {
    this.id = id;
    this.path = path;
    this.thumbnailPath = thumbnailPath;
  }

  public Photo() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Photo)) return false;

    Photo photo = (Photo) o;

    return id == photo.id;
  }

  @Override
  public int hashCode() {
    return id;
  }

  public String getPath() {
    return path;
  }

  public String getThumbnailPath(){
    return thumbnailPath;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
