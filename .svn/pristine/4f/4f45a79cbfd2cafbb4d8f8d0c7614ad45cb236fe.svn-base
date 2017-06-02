package zhou.tools.fileselector.config;

import java.io.Serializable;

import zhou.tools.fileselector.utils.FileFilter;
import zhou.tools.fileselector.utils.FileType;

/**
 * 文件选择器的配置类
 * Created by zzhoujay on 2014/12/30 0030.
 */
public class FileConfig implements Serializable {

    public static final String FILE_CONFIG="fileConfig";

    public boolean showHiddenFiles = false;
    public int theme;
    public int filterModel;
    public String[] filter;
    public boolean positiveFiter;
    public String title;//选择框的标题
    public boolean easyMode;//是否开启简易模式
    public String rootPath;//文件选择可以访问的最高路径,默认为"/"
    public String startPath;//文件选择器的开始的路径，默认为"/"
    public boolean multiModel;//多选模式
    public int selectType;//选择文件的种类:文件夹、文件、全部
    public boolean actionBarMode;//在Activity中使用ActionBar来替换一部分操作

    public FileConfig() {

        theme = FileTheme.THEME_WHITE;
        filterModel = FileFilter.FILTER_NONE;
        positiveFiter = true;
        title = "选择文件";
        easyMode = false;
        rootPath="/";
        startPath="/";
        multiModel=false;
        selectType= FileType.All;
        actionBarMode=true;

    }
}
