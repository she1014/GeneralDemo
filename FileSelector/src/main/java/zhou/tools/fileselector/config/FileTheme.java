package zhou.tools.fileselector.config;

import java.io.Serializable;
import java.util.ArrayList;

import zhou.tools.fileselector.R;

/**
 * 主题
 * Created by zzhoujay on 2014/12/30 0030.
 */
public class FileTheme implements Serializable{

    public static final int THEME_WHITE=1;
    public static final int THEME_BLACK=2;
    public static final int THEME_GREY=3;
    public static final int ThEME_CUSTOM=10;

    //所有主题的基类,可以通过继承它来实现自定义主题  ??(库工程可以访问外部的资源文件吗）
    public static class BaseTheme implements Serializable{

        public int folder;//文件夹图标
        public int upfolder;//上级文件夹图标
        public int background;//整体背景颜色
        public int txtIcon;//文本文件图标
        public int audioIcon;//音频文件图标
        public int videoIcon;//视频文件图标
        public int imageIcon;//图片文件图标
        public int otherIcon;//其他文件图标
        public int textColor;//文字颜色
        public int unselectIcon;//取消选择的图标
        public int hitBarBg;//hitBar背景
        public int okIcon;

    }

    //白色主题
    public static class White extends BaseTheme implements Serializable{

        public White(){

            background=R.color.white_bg;
            textColor=R.color.black_text_color;
            hitBarBg=R.color.hitBar_background_white;

            folder=R.drawable.ic_folder_black_48dp;
            upfolder=R.drawable.ic_folder_open_black_48dp;

            txtIcon=R.drawable.ic_description_black_48dp;
            audioIcon=R.drawable.ic_album_black_48dp;
            videoIcon=R.drawable.ic_theaters_black_48dp;
            otherIcon=R.drawable.ic_insert_drive_file_black_48dp;
            imageIcon=R.drawable.ic_insert_photo_black_48dp;
            unselectIcon=R.drawable.ic_close_black_48dp;

        }

    }

    //黑色主题
    public static class Black extends BaseTheme implements Serializable{

        public Black(){

            background= R.color.black_bg;
            textColor=R.color.white_text_color;
            hitBarBg=R.color.hitBar_backgroung_black;

            folder=R.drawable.ic_folder_white_48dp;
            upfolder=R.drawable.ic_folder_open_white_48dp;

            txtIcon=R.drawable.ic_description_white_48dp;
            audioIcon=R.drawable.ic_album_white_48dp;
            videoIcon=R.drawable.ic_theaters_white_48dp;
            otherIcon=R.drawable.ic_insert_drive_file_white_48dp;
            imageIcon=R.drawable.ic_insert_photo_white_48dp;
            unselectIcon=R.drawable.ic_close_white_48dp;
        }

    }

    //灰色主题
    public static class Grey extends BaseTheme implements Serializable{

        public Grey(){

            background=R.color.grey_bg;
            textColor=R.color.grey_text_color;
            hitBarBg=R.color.hitBar_background_grey;

            folder=R.drawable.ic_folder_grey600_48dp;
            upfolder=R.drawable.ic_folder_open_grey600_48dp;

            txtIcon=R.drawable.ic_description_grey600_48dp;
            audioIcon=R.drawable.ic_album_grey600_48dp;
            videoIcon=R.drawable.ic_theaters_grey600_48dp;
            otherIcon=R.drawable.ic_insert_drive_file_grey600_48dp;
            imageIcon=R.drawable.ic_insert_photo_grey600_48dp;
            unselectIcon=R.drawable.ic_close_grey600_48dp;
        }
    }
}
