package zhou.tools.fileselector.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import zhou.tools.fileselector.FileSelector;

/**
 * 文件的各种相关的操作
 *
 * Created by zzhoujay on 2014/12/30 0030.
 */
public class FileUtils {

    private static FileCompare fileCompare = new FileCompare();

    //获取文件列表
    public static ArrayList<HashMap<String, Object>> getFileList(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {

                File[] files = file.listFiles();

                ArrayList<HashMap<String, Object>> ds = new ArrayList<>();
                ArrayList<HashMap<String, Object>> fs = new ArrayList<>();


                if (!path.equals(FileSelector.rootPath)) {

                    HashMap<String, Object> up = new HashMap<>(3);
                    up.put(FileSelector.NAME, "...返回上一级");
                    up.put(FileSelector.ICON, FileSelector.theme.upfolder);
                    up.put(FileSelector.PATH, path);
                    up.put(FileSelector.TYPE, FileType.UPTO);
                    up.put(FileSelector.SELECT,FileType.forbid);

                    ds.add(up);
                }


                for (File e : files) {

                    HashMap<String, Object> value = new HashMap<>(3);
                    String p = e.getAbsolutePath();
                    String name = getFileName(p);

                    /**
                     * 隐藏文件处理
                     */
                    if (isHitFiles(name)) {
                        if (!FileSelector.fileConfig.showHiddenFiles) {
                            continue;
                        }
                    }

                    /**
                     * 文件夹处理
                     */
                    if (e.isDirectory()) {

                        value.put(FileSelector.NAME, name);
                        value.put(FileSelector.PATH, p);
                        value.put(FileSelector.ICON, FileSelector.theme.folder);
                        value.put(FileSelector.TYPE, FileType.FOLDER);
                        value.put(FileSelector.SELECT,FileSelector.selectType==FileType.FILE?FileType.forbid:FileType.unselect);

                        ds.add(value);

                    } else {//文件处理


                        String lastName=getFileLastName(name);

                        //如果需要过滤
                        if(FileSelector.filterModel!=FileFilter.FILTER_NONE){
                            //如果是positive过滤
                            if(FileSelector.positiveFilter){
                                //如果匹配不成功,则跳过此次循环
                                if(!FileFilter.doFilter(lastName)){
                                    continue;
                                }

                            }else {//native 过滤
                                //如果匹配成功则跳过此次循环
                                if(FileFilter.doFilter(lastName)){
                                    continue;
                                }
                            }

                        }



                        int type = getFileType(name);

                        int icon;
                        switch (type) {
                            case FileType.AUDIO:
                                icon = FileSelector.theme.audioIcon;
                                break;
                            case FileType.VIDEO:
                                icon = FileSelector.theme.videoIcon;
                                break;
                            case FileType.TEX:
                                icon = FileSelector.theme.txtIcon;
                                break;
                            case FileType.IMAGE:
                                icon = FileSelector.theme.imageIcon;
                                break;
                            default:
                                icon = FileSelector.theme.otherIcon;


                        }

                        value.put(FileSelector.NAME, name);
                        value.put(FileSelector.PATH, p);
                        value.put(FileSelector.ICON, icon);
                        value.put(FileSelector.TYPE, FileType.FILE);
                        value.put(FileSelector.SELECT,FileSelector.selectType==FileType.FOLDER?FileType.forbid:FileType.unselect);

                        fs.add(value);
                    }
                }

                Collections.sort(ds, fileCompare);
                Collections.sort(fs, fileCompare);

                ds.addAll(fs);
                return ds;
            }
        }


        return null;
    }


    //获取文件的种类
    public static int getFileType(String name) {
        int type = FileType.OTHER;

        String last = getFileLastName(name);
        if (last == null)
            return type;

        if (last.equals("jpg") | last.equals("png") | last.equals("bmp")) {
            type = FileType.IMAGE;
        } else if (last.equals("avi") | last.equals("mp4") | last.equals("mkv") | last.equals("flv")) {
            type = FileType.VIDEO;
        } else if (last.equals("txt")) {
            type = FileType.TEX;
        } else if (last.equals("mp3")) {
            type = FileType.AUDIO;
        }
        return type;
    }


    //获取文件的后缀名
    public static String getFileLastName(String name) {
        if (!name.contains(".")) {
            return null;
        }
        return name.substring(name.lastIndexOf(".") + 1);
    }

    //通过文件路径获取文件的名字
    public static String getFileName(String path) {
        return path.substring(path.lastIndexOf('/') + 1, path.length());
    }

    //获取文件父目录
    public static String getParentPath(String path) {
        File file = new File(path);

        if (file.exists()) {
            return file.getParent();
        }

        return null;
    }

    //判断文件是否是隐藏文件
    public static boolean isHitFiles(String name) {
        return name.substring(0, 1).equals(".");
    }

}
