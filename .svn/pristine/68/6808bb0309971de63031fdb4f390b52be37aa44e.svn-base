package zhou.tools.fileselector.utils;

import java.util.Comparator;
import java.util.HashMap;

import zhou.tools.fileselector.FileSelector;

/**
 * 文件比较的类
 * Created by zzhoujay on 2014/12/30 0030.
 */
public class FileCompare implements Comparator<HashMap<String,Object>>{
    @Override
    public int compare(HashMap<String, Object> lhs, HashMap<String, Object> rhs) {
        String n1= (String) lhs.get(FileSelector.NAME);
        String n2= (String) rhs.get(FileSelector.NAME);
        return n1.toUpperCase().compareTo(n2.toUpperCase());
    }
}
