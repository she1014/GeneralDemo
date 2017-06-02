package zhou.tools.fileselector;

import android.animation.LayoutTransition;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import zhou.tools.fileselector.adapter.FileListAdapter;
import zhou.tools.fileselector.config.FileConfig;
import zhou.tools.fileselector.config.FileTheme;
import zhou.tools.fileselector.utils.ColorUtils;
import zhou.tools.fileselector.utils.FileFilter;
import zhou.tools.fileselector.utils.FileType;
import zhou.tools.fileselector.utils.FileUtils;

/**
 * 文件选择器的核心类
 * <p/>
 * <p/>
 * Created by zzhoujay on 2014/12/30 0030.
 */
public class FileSelector {

    public static final String ICON = "icon";
    public static final String NAME = "name";
    public static final String ITEM = "item";
    public static final String PATH = "path";
    public static final String TYPE = "type";
    public static final String SELECT = "select";
    public static final String RESULT = "result";

    public static FileConfig fileConfig;

    public static FileTheme.BaseTheme theme;

    public static int filterModel;
    public static String[] filter;
    public static boolean positiveFilter;
    public static String rootPath;
    public static boolean easyMode;
    public static boolean multiMode;
    public static int selectType;


    private TextView title;
    private Button cancelButton, confirmButton;
    private ImageButton unSelectButton;
    private TextView countText;
    private LinearLayout hitBar;
    private LinearLayout optionBar;

    private OnCancelListener onCancelListener;
    private OnConfirmListener onConfirmListener;

    private Context context;

    private OnSelectCompleteListener selectCompleteListener;
    private onSelectCountChangeListener onSelectCountChangeListener;
    private onListUpdataComplete onListUpdataComplete;


    private LinearLayout fileSelector;
    private ListView fileListView;
    private FileListAdapter fileListAdapter;

    private ArrayList<HashMap<String, Object>> files;


    public FileSelector(Context context) {
        this.context = context;
        fileConfig = new FileConfig();
        initAll();
    }

    public FileSelector(Context context, FileConfig fileConfig) {
        this.context = context;
        FileSelector.fileConfig = fileConfig;
        initAll();
    }

    //执行初始化操作
    private void initAll() {

        //View的初始化
        fileSelector = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.zhou_file_selector, null);
        fileListView = (ListView) fileSelector.findViewById(R.id.zhou_file_listView);
        title = (TextView) fileSelector.findViewById(R.id.zhou_file_title);
        cancelButton = (Button) fileSelector.findViewById(R.id.zhou_file_cancel_btn);
        confirmButton = (Button) fileSelector.findViewById(R.id.zhou_file_confirm_btn);
        hitBar = (LinearLayout) fileSelector.findViewById(R.id.zhou_file_hit_bar);
        unSelectButton = (ImageButton) hitBar.findViewById(R.id.zhou_file_hit_bar_icon);
        countText = (TextView) hitBar.findViewById(R.id.zhou_file_hit_bar_text);
        hitBar.setVisibility(View.GONE);
        optionBar= (LinearLayout) fileSelector.findViewById(R.id.zhou_file_bottom);

        LayoutTransition transition = new LayoutTransition();
        transition.setAnimator(LayoutTransition.APPEARING, transition.getAnimator(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.DISAPPEARING, transition.getAnimator(LayoutTransition.DISAPPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, transition.getAnimator(LayoutTransition.CHANGE_APPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, transition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));

        LinearLayout listContainer = (LinearLayout) fileSelector.findViewById(R.id.zhou_list_container);
        listContainer.setLayoutTransition(transition);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelListener != null)
                    onCancelListener.OnCancel();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onConfirmListener != null)
                    onConfirmListener.OnConfirm(getSelectFilePaths());
            }
        });

        unSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileListAdapter.clearAllSelect();
                dismissHitBar();
            }
        });

        initConfig();

        files = FileUtils.getFileList(fileConfig.startPath);

        fileListAdapter = new FileListAdapter(context, files, multiMode);

        fileListAdapter.setOnSelectCountChangeListener(new FileListAdapter.onSelectCountChangeListener() {
            @Override
            public void onSelectCountChange(int selectCount) {
                int visible = hitBar.getVisibility();
                if (selectCount > 0) {
                    if (visible == View.GONE) {
                        showHitBar();
                    }
                    countText.setText("已选择" + selectCount + "项");
                } else {
                    if (visible == View.VISIBLE) {
                        dismissHitBar();
                    }
                }
                if(onSelectCountChangeListener!=null)
                    onSelectCountChangeListener.onSelectCountChange(selectCount);
            }
        });

        fileListView.setAdapter(fileListAdapter);

        fileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = (String) files.get(position).get(PATH);
                if (path == null)
                    return;
                int type = (int) files.get(position).get(TYPE);
                switch (type) {
                    case FileType.FOLDER:
                        updateList(path);
                        break;
                    case FileType.FILE:

                        if (!multiMode && selectCompleteListener != null) {
                            selectCompleteListener.onSelectComplete(path);
                        }

                        break;
                    case FileType.UPTO:
                        String parentPath = FileUtils.getParentPath(path);
                        updateList(parentPath);
                        break;
                }


            }
        });

    }

    //初始化配置
    private void initConfig() {
        switch (fileConfig.theme) {
            case FileTheme.THEME_WHITE:
                theme = new FileTheme.White();
                break;
            case FileTheme.THEME_BLACK:
                theme = new FileTheme.Black();
                break;
            case FileTheme.THEME_GREY:
                theme = new FileTheme.Grey();
                break;
        }

        fileSelector.setBackgroundColor(context.getResources().getColor(theme.background));

        filterModel = fileConfig.filterModel;
        if (filterModel == FileFilter.FILTER_CUSTOM)
            filter = fileConfig.filter;
        positiveFilter = fileConfig.positiveFiter;

        title.setText(fileConfig.title);
        title.setTextColor(ColorUtils.getColorFromResources(context, theme.textColor));

        cancelButton.setTextColor(ColorUtils.getColorFromResources(context,theme.textColor));
        confirmButton.setTextColor(ColorUtils.getColorFromResources(context,theme.textColor));

        rootPath = fileConfig.rootPath;
        easyMode = fileConfig.easyMode;
        if (easyMode) {
            hitBottomBar();
        }
        multiMode = fileConfig.multiModel;
        if (!multiMode) {
            hitConfirmButton();
        }

        unSelectButton.setImageResource(theme.unselectIcon);

        countText.setTextColor(ColorUtils.getColorFromResources(context, theme.textColor));
        hitBar.setBackgroundColor(ColorUtils.getColorFromResources(context, theme.hitBarBg));

        selectType = fileConfig.selectType;
    }




    private void updateList(String path) {
        ArrayList<HashMap<String, Object>> temp = null;
        try {
            temp = FileUtils.getFileList(path);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "打开失败", Toast.LENGTH_SHORT).show();
            return;
        }
        files.clear();
        files.addAll(temp);

        dismissHitBar();
        fileListAdapter.notifyDataSetChanged();
        if(onListUpdataComplete!=null)
            onListUpdataComplete.onComplete();
        fileListView.setSelection(0);
    }

    public ArrayList<String> getSelectFilePaths() {
        ArrayList<String> paths = new ArrayList<>();
        if (multiMode) {
            for (HashMap<String, Object> t : files) {
                if (t.get(SELECT) .equals( FileType.selected)) {
                    paths.add(t.get(PATH).toString());
                }
            }
        }
        return paths;
    }

    /**
     * 选择完成的回调接口（单选专属）
     */
    public static interface OnSelectCompleteListener {
        public void onSelectComplete(String filePath);
    }

    /**
     * 取消按钮的回调接口
     */
    public static interface OnCancelListener {
        public void OnCancel();
    }

    /**
     * 确定按钮回调接口（在确认按钮可见的情况下有效）
     */
    public static interface OnConfirmListener {
        public void OnConfirm(ArrayList<String> filePaths);
    }

    public static interface onSelectCountChangeListener{
        public void onSelectCountChange(int selectCount);
    }

    /**
     * 每次刷新完成的回调接口
     */
    public static interface onListUpdataComplete{
        public void onComplete();
    }





    public LinearLayout getFileSelector() {
        return fileSelector;
    }

    public void setOnSelectCompleteListener(OnSelectCompleteListener selectCompleteListener) {
        this.selectCompleteListener = selectCompleteListener;
    }

    public static void setFileConfig(FileConfig fileConfig) {
        FileSelector.fileConfig = fileConfig;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public void hitTitle() {
        fileSelector.findViewById(R.id.zhou_file_head).setVisibility(View.GONE);
    }

    public void showTitle() {
        fileSelector.findViewById(R.id.zhou_file_head).setVisibility(View.VISIBLE);
    }

    public void hitConfirmButton() {
        fileSelector.findViewById(R.id.zhou_file_confirm_btn).setVisibility(View.GONE);
    }

    public void showConfirmButton() {
        fileSelector.findViewById(R.id.zhou_file_confirm_btn).setVisibility(View.VISIBLE);
    }

    public void enableConfirmButton() {
        fileSelector.findViewById(R.id.zhou_file_confirm_btn).setEnabled(true);
    }

    public void unableConfirmButton() {
        fileSelector.findViewById(R.id.zhou_file_confirm_btn).setEnabled(false);
    }

    public void showBottomBar() {
        fileSelector.findViewById(R.id.zhou_file_bottom).setVisibility(View.VISIBLE);
    }

    public void hitBottomBar() {
        fileSelector.findViewById(R.id.zhou_file_bottom).setVisibility(View.GONE);
    }



    public void dismissHitBar() {
        hitBar.setVisibility(View.GONE);
    }

    public void showHitBar() {
        hitBar.setVisibility(View.VISIBLE);
    }

    public static FileConfig getFileConfig() {
        return fileConfig;
    }

    public void dismissOptionBar(){
        optionBar.setVisibility(View.GONE);
    }

    public void showOptionBar(){
        optionBar.setVisibility(View.VISIBLE);
    }

    public void setOnSelectCountChangeListener(FileSelector.onSelectCountChangeListener onSelectCountChangeListener) {
        this.onSelectCountChangeListener = onSelectCountChangeListener;
    }

    public void setOnListUpdataComplete(FileSelector.onListUpdataComplete onListUpdataComplete) {
        this.onListUpdataComplete = onListUpdataComplete;
    }
}
