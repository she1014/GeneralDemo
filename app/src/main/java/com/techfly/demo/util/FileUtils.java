package com.techfly.demo.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.techfly.demo.activity.application.MyApplication;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Properties;

/*
 * 功能：读、写SD卡
 * 注：需添加SD卡权限
 */
public class FileUtils {
	private Context context;
	/** SD卡是否存在 **/
	private boolean hasSD = false;
	/** SD卡的路径 **/
	private String SDPATH;
	/** 当前程序包的路径 **/
	private String FILESPATH;

	private static String BASE_PATH;

	private static FileUtils mInstance;

	public FileUtils(Context context) {
		this.context = context;
		hasSD = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
		SDPATH = Environment.getExternalStorageDirectory().getPath();
		FILESPATH = this.context.getFilesDir().getPath();
	}

	/*
	 * 提取链接/路径中的文件名
	 */
	public static String getUrlFileName(String url){
		return url.substring(url.lastIndexOf("/") + 1);
	}

	/**
	 * 在SD卡上创建文件
	 * @throws IOException
	 */
	public File createSDFile(String fileName) throws IOException {
		File file = new File(SDPATH + "//" + fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 删除SD卡上的文件
	 * @param fileName
	 */
	public boolean deleteSDFile(String fileName) {
		File file = new File(SDPATH + "//" + fileName);
		if (file == null || !file.exists() || file.isDirectory())
			return false;
		return file.delete();
	}

	/**
	 * 写入内容到SD卡中的txt文本中 str为内容
	 */
	public void writeSDFile(String str, String fileName) {
		try {
			FileWriter fw = new FileWriter(SDPATH + "//" + fileName);
			File f = new File(SDPATH + "//" + fileName);
			fw.write(str);
			FileOutputStream os = new FileOutputStream(f);
			DataOutputStream out = new DataOutputStream(os);
			out.writeShort(2);
			out.writeUTF("");
			System.out.println(out);
			fw.flush();
			fw.close();
			System.out.println(fw);
		} catch (Exception e) {
		}
	}

	/*
	 * 以UTF-8的格式保存文件到SD卡上
	 */
	public void writeUtfFile(String s,String name){
		try {
			FileOutputStream outStream = new FileOutputStream(SDPATH + "//" + name,true);
			OutputStreamWriter writer = new OutputStreamWriter(outStream,Charset.forName("UTF-8"));
			writer.write(s);
//			writer.write("/n");
			writer.flush();
			writer.close();//记得关闭
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取SD卡中文本文件
	 * @param fileName
	 * @return
	 */
	public String readSDFile(String fileName) {
		StringBuffer sb = new StringBuffer();
		File file = new File(SDPATH + "//" + fileName);
		try {
			FileInputStream fis = new FileInputStream(file);
			int c;
			while ((c = fis.read()) != -1) {
				sb.append((char) c);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/*
     * 读取配置文件
     */
	public Properties loadConfig(Context context,String file){
		Properties properties = new Properties();
		try{
			FileInputStream s = new FileInputStream(file);
			properties.load(s);
		}catch(Exception e){
			e.printStackTrace();
			//Log.i(TAG,"loadConfig,Exception："+e.getMessage());
		}
		return properties;
	}

	//将字符串写入到文本文件中
	public static void WriteTxtFile(String strcontent,String strFilePath)
	{
		// 每次写入时，都换行写
		String strContent = strcontent + "\n";
		try {
			File file = new File(strFilePath);
			if (!file.exists()) {
				Log.d("TestFile", "Create the file:" + strFilePath);
				file.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(file.length());
			raf.write(strContent.getBytes());
			raf.close();
		} catch (Exception e) {
			Log.e("TestFile", "Error on write File.");
		}
	}

	//判断该文件是否存在
	public static boolean fileIsExists(String path){  //这里只能判断SD卡下的路径
		try{
			File f = new File(path);
			if(!f.exists()){
				return false;
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}


	/*
     * 保存配置文件
     */
	public boolean saveConfig(Context context,String file,Properties properties){
		try{
			File fl = new File(file);
			if(!fl.exists()){
				fl.createNewFile();
				FileOutputStream s = new FileOutputStream(fl);
				properties.store(s, "");
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getFILESPATH() {
		return FILESPATH;
	}

	public String getSDPATH() {
		return SDPATH;
	}

	public boolean hasSD() {
		return hasSD;
	}

	public String getBasePath(){
		return BASE_PATH;
	}

	public static FileUtils getInst() {
		if (mInstance == null) {
			synchronized (FileUtils.class) {
				if (mInstance == null) {
					mInstance = new FileUtils();
				}
			}
		}
		return mInstance;
	}

	public boolean mkdir(File file) {
		while (!file.getParentFile().exists()) {
			mkdir(file.getParentFile());
		}
		return file.mkdir();
	}

	public void delete(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}

		if (file.isDirectory()) {
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				file.delete();
				return;
			}

			for (int i = 0; i < childFiles.length; i++) {
				delete(childFiles[i]);
			}
			file.delete();
		}
	}

	private FileUtils() {
		String sdcardState = Environment.getExternalStorageState();
		//如果没SD卡则放缓存
		if (Environment.MEDIA_MOUNTED.equals(sdcardState) || !Environment.isExternalStorageRemovable()) {
			BASE_PATH = MyApplication.getApp().getExternalCacheDir().getPath();
		} else {
			BASE_PATH = MyApplication.getApp().getCacheDirPath();
		}
	}

	//////kb
	public static long getDirSize(File file) {
		//判断文件是否存在
		if (file.exists()) {
			//如果是目录则递归计算其内容的总大小
			if (file.isDirectory()) {
				File[] children = file.listFiles();
				long size = 0;
				for (File f : children)
					size += getDirSize(f);
				return size;
			} else {
				long size = (long) file.length();
				return size;
			}
		} else {
			System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
			return 0;
		}
	}

	/**
	 * 格式化单位
	 *
	 * @param size
	 * @return
	 */
	public static String getFormatSize(double size) {
		double kiloByte = size / 1024;
		if (kiloByte < 1) {
//            return size + "Byte";
			return "0KB";
		}

		double megaByte = kiloByte / 1024;
		if (megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "KB";
		}

		double gigaByte = megaByte / 1024;
		if (gigaByte < 1) {
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "MB";
		}

		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
				+ "TB";
	}
}
