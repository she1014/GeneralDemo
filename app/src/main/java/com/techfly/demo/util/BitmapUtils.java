package com.techfly.demo.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils {
	/**
	 * 加载本地的图片文件 到内存
	 * 
	 * @param path
	 *            图片文件的路径
	 * @return
	 */
	public static Bitmap loadBitmap(String path) {
		Bitmap bm = null;
		bm = BitmapFactory.decodeFile(path);
		return bm;
	}

	/**
	 * 按指定宽高 加载字节数组中的图片到内存
	 * 
	 * @param data
	 * @return
	 */
	public static Bitmap loadBitmap(byte[] data) {
		Bitmap bm = null;
		// 加载图片的边界信息
//		Options opts = new Options();
//		opts.inJustDecodeBounds = true;
//		BitmapFactory.decodeByteArray(data, 0, data.length, opts);
//		// 计算收缩比例
//		int xSacle = opts.outWidth / width;
//		int yScale = opts.outHeight / height;
//
		// 设置收缩比例
//		opts.inSampleSize = xSacle > yScale ? xSacle : yScale;
		// 加载图片
//		opts.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeByteArray(data, 0, data.length);
		return bm;
	}

	/**
	 * 保存内存中的图片到指定文件
	 * 
	 * @param bm
	 * @param file
	 * @throws IOException
	 */
	public static void save(Bitmap bm, File file) throws IOException {
		if (bm != null && file != null) {
			File f = file.getAbsoluteFile().getParentFile();
			if (f == null || !f.exists()){
				f.mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream stream = new FileOutputStream(file);
			bm.compress(CompressFormat.JPEG, 100, stream);
		}
	}

	public static Bitmap decodeFile(File f) {
		Bitmap bm= BitmapFactory.decodeFile(f.toString());
		return bm;
	}


	/**
	 *按照比例压缩
	 *
	 */
	public static Bitmap comp(Bitmap image) {
		Bitmap bitmap = image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, baos);
		if( baos.toByteArray().length / 1024>512) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();//重置baos即清空baos
			image.compress(CompressFormat.JPEG, 50, baos);//这里压缩70%，把压缩后的数据存放到baos中
			bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()));
		}
		return bitmap;//压缩好比例大小后再进行质量压缩
		/*BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = 1280f;//这里设置高度为1280f
		float ww = 768f;//这里设置宽度为768f
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置缩放比例
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());*/

	}





}
