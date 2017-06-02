package com.techfly.demo.activity.demo;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.MainActivity;
import com.techfly.demo.util.MediaUtils;

import java.io.File;
import java.util.ArrayList;

import zhou.tools.fileselector.FileSelector;
import zhou.tools.fileselector.FileSelectorActivity;
import zhou.tools.fileselector.FileSelectorAlertDialog;
import zhou.tools.fileselector.FileSelectorDialog;
import zhou.tools.fileselector.config.FileConfig;
import zhou.tools.fileselector.config.FileTheme;
import zhou.tools.fileselector.utils.FileFilter;


public class Demo24Activity extends ActionBarActivity {

    private Spinner spinnerTheme;
    private Spinner spinnerFilter;
    private CheckBox checkBox;
    private String[] themes;
    private String[] filters;
    private FileConfig fileConfig;
    private CheckBox showHit;
    private CheckBox multi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo24);
        fileConfig = new FileConfig();
        initView();
    }

    private void initView() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileSelectorDialog fileDialog = new FileSelectorDialog();
                fileDialog.setOnSelectFinish(new FileSelectorDialog.OnSelectFinish() {
                    @Override
                    public void onSelectFinish(ArrayList<String> paths) {
                        Toast.makeText(getApplicationContext(), paths.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putSerializable(FileConfig.FILE_CONFIG, fileConfig);
                fileDialog.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fileDialog.show(ft, "fileDialog");
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FileSelectorActivity.class);
                fileConfig.startPath = Environment.getExternalStorageDirectory().getPath();

                fileConfig.rootPath = "/";
                intent.putExtra(FileConfig.FILE_CONFIG, fileConfig);
                startActivityForResult(intent, 0);
            }
        });

        spinnerTheme = (Spinner) findViewById(R.id.spinner_theme);
        spinnerFilter = (Spinner) findViewById(R.id.spinner_filter);
        checkBox = (CheckBox) findViewById(R.id.checkBox_mode);

        themes = getResources().getStringArray(R.array.theme_selects);
        filters = getResources().getStringArray(R.array.filter_selects);

        ArrayAdapter<String> adapterTheme = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, themes);
        ArrayAdapter<String> adapterFilter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filters);

        spinnerTheme.setAdapter(adapterTheme);
        spinnerFilter.setAdapter(adapterFilter);

        spinnerTheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int t;
                switch (position) {
                    case 1:
                        t = FileTheme.THEME_WHITE;
                        break;
                    case 2:
                        t = FileTheme.THEME_BLACK;
                        break;
                    case 3:
                        t = FileTheme.THEME_GREY;
                        break;
                    default:
                        t = FileTheme.THEME_WHITE;
                }
                fileConfig.theme = t;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int t;
                switch (position) {
                    case 1:
                        t = zhou.tools.fileselector.utils.FileFilter.FILTER_NONE;
                        break;
                    case 2:
                        t = FileFilter.FILTER_IMAGE;
                        break;
                    case 3:
                        t = FileFilter.FILTER_TXT;
                        break;
                    case 4:
                        t = FileFilter.FILTER_VIDEO;
                        break;
                    case 5:
                        t = FileFilter.FILTER_AUDIO;
                        break;
                    default:
                        t = FileFilter.FILTER_NONE;
                }
                fileConfig.filterModel = t;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fileConfig.positiveFiter = isChecked;
            }
        });

        showHit = (CheckBox) findViewById(R.id.checkBox_show_hit);
        showHit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fileConfig.showHiddenFiles = isChecked;
            }
        });

        multi = (CheckBox) findViewById(R.id.checkBox_multi);
        multi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fileConfig.multiModel = isChecked;
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileSelectorAlertDialog fileSelectorAlertDialog = new FileSelectorAlertDialog(Demo24Activity.this);
                fileSelectorAlertDialog.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> list = data.getStringArrayListExtra(FileSelector.RESULT);
//                Toast.makeText(getApplicationContext(), list.toString(), Toast.LENGTH_SHORT).show();

                if (list.size() > 0) {

                    String path = list.get(0);

                    Log.i("TTSS", "是否视频类型:" + MediaUtils.isVideoFileType(path) + ",path=" + path);

                    Log.i("TTSS","路径:"+getUrlFilePath(path)+",文件名:"+getUrlFileName(path));

                    if (MediaUtils.isVideoFileType(path)) {
                        showVideoDialog(this, path);

                    }else{
                        Toast.makeText(getApplicationContext(), "非视频文件无法播放!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
     * 显示登陆提示框【注：跳转时记得结束当前活动Activity】
     */
    public static void showVideoDialog(final Context context,final String filePath) {
        final AlertDialog dialog = new AlertDialog.Builder(context,R.style.MyDialog).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.toastdig);  //添加动画
        View view = View.inflate(context, R.layout.dialog_middle_login, null);
        window.setContentView(view);

        TextView tv1 = (TextView) view.findViewById(R.id.middle_tv);
        TextView tv2 = (TextView) view.findViewById(R.id.cancel);
        TextView tv3 = (TextView) view.findViewById(R.id.ok);

        tv1.setText("请选择操作方式");
        tv2.setText("隐藏");
        tv3.setText("播放");

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"隐藏文件成功",Toast.LENGTH_SHORT).show();

                String m_new_path = getUrlFilePath(filePath) + "/." +getUrlFileName(filePath);

                Log.i("TTSS","新路径:"+m_new_path);

                File file = new File(filePath);
                File n_file = new File(m_new_path);
                file.renameTo(n_file);

                dialog.dismiss();
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "播放", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(filePath), "video/mp4");
                context.startActivity(intent);

                dialog.dismiss();

            }
        });
    }

    /*
	 * 提取链接/路径中的文件名
	 */
    public static String getUrlFileName(String url){
        return url.substring(url.lastIndexOf("/") + 1);
    }

    /*
	 * 提取链接/路径中的文件名
	 */
    public static String getUrlFilePath(String url){
        return url.substring(0, url.lastIndexOf("/"));
    }

    /**
     *  @Description 对文件进行重新命名
     *  @param fileInfo 原始文件信息
     *  @param newName 新名称（有后缀）
     *  @param admitCopyName 当命名冲突时，是否允许生成副本名（如果是多选重命名的话，是需要允许的；单个文件重命名则设置为不允许）
     *  @return 是否修改成功
     */
    public boolean rename(String o_path, String n_path) {
        File file = new File(o_path);
        File n_file = new File("/storage/emulated/0/.80s测试短片2.mp4");
        file.renameTo(n_file);
        return true;
    }

}
