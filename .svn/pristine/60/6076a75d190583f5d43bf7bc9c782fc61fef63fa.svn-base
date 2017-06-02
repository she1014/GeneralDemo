package zhou.tools.fileselector.utils;

import java.io.Serializable;

import zhou.tools.fileselector.FileSelector;

/**
 * 文件过滤的类
 * Created by zzhoujay on 2014/12/30 0030.
 */
public class FileFilter implements Serializable {

    public static final int FILTER_IMAGE = 1;
    public static final int FILTER_TXT = 2;
    public static final int FILTER_AUDIO = 3;
    public static final int FILTER_VIDEO = 4;
    public static final int FILTER_CUSTOM = 10;
    public static final int FILTER_NONE = -1;

    public static final String[] IMAGE_FILTER = new String[]{
            "png", "jpg", "bmp"
    };

    public static final String[] TXT_FILTER = new String[]{
            "txt", "c", "cpp", "java", "xml"
    };

    public static final String[] AUDIO_FILTER = new String[]{
            "mp3", "m4a"
    };

    public static final String[] VIDEO_FILTER = new String[]{
            "mp4", "avi", "flv"
    };

    /**
     * 过滤处理
     *
     * @param lastName 文件后缀名
     * @return 匹配成功：true
     */
    public static boolean doFilter(String lastName) {
        if (lastName == null)
            return false;

        int model = FileSelector.filterModel;
        String[] filter = null;
        switch (model) {
            case FILTER_AUDIO:
                filter = AUDIO_FILTER;
                break;
            case FILTER_IMAGE:
                filter = IMAGE_FILTER;
                break;
            case FILTER_TXT:
                filter = TXT_FILTER;
                break;
            case FILTER_VIDEO:
                filter = VIDEO_FILTER;
                break;
            default:
                filter = FileSelector.filter;
        }

        for (String f : filter) {
            if (f.equals(lastName))
                return true;
        }

        return false;
    }
}
