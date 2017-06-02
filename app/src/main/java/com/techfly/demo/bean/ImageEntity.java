package com.techfly.demo.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * GridView的每个item的数据对象
 * 
 * @author cr1106
 * 
 */
public class ImageEntity implements Parcelable,Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 1705924033879439495L;

    @Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.id);
		dest.writeString(this.imagePath);
		dest.writeString(this.folderName);
		dest.writeInt(this.imageCounts);
		dest.writeString(this.createTime);
		dest.writeString(this.remark);
	}

	public static final Creator<ImageEntity> CREATOR = new Creator<ImageEntity>() {
		@Override
		public ImageEntity createFromParcel(Parcel source) {
			// 从Parcel中读取数据，返回person对象
			ImageEntity entity = new ImageEntity();
			entity.setId(source.readString());
			entity.setImagePath(source.readString());
			entity.setFolderName(source.readString());
			entity.setImageCounts(source.readInt());
			entity.setCreateTime(source.readString());
			entity.setRemark(source.readString());
			return entity;
		}

		@Override
		public ImageEntity[] newArray(int size) {
			return new ImageEntity[size];
		}
	};
	private String id;
	/**
	 * 图片路径
	 */
	private String imagePath;
	/**
	 * 文件夹名
	 */
	private String folderName;
	/**
	 * 文件夹中的图片数
	 */
	private int imageCounts;
	/**
	 * 
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 照片描述
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public int getImageCounts() {
		return imageCounts;
	}

	public void setImageCounts(int imageCounts) {
		this.imageCounts = imageCounts;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

}
