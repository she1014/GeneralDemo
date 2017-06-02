package zhou.tools.fileselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import zhou.tools.fileselector.config.FileConfig;

/**
 * 文件选择Activity
 * <p/>
 * 接收一个FileConfig对象通过Intent传入（可选）
 * <p/>
 * Created by zzhoujay on 2014/12/30 0030.
 */
public class FileSelectorActivity extends ActionBarActivity {

    public static final int FILE_SELECT_ACTIVITY_CODE = 10086;

    private FileSelector fileSelector;
    private FileConfig fileConfig;
    private Class aClass;
    private boolean actionBarModel = true;
    private MenuItem ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        aClass = intent.getClass();

        if (intent != null) {
            try {
                fileConfig = (FileConfig) intent.getSerializableExtra(FileConfig.FILE_CONFIG);
            } catch (Exception e) {
                e.printStackTrace();
                fileConfig = new FileConfig();
            }
        }


        fileSelector = new FileSelector(this, fileConfig);

        fileSelector.setOnCancelListener(new FileSelector.OnCancelListener() {
            @Override
            public void OnCancel() {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        if (fileConfig.multiModel) {
            fileSelector.setOnConfirmListener(new FileSelector.OnConfirmListener() {
                @Override
                public void OnConfirm(ArrayList<String> filePath) {

                    Intent resultIntent = new Intent(getApplicationContext(), aClass);
                    resultIntent.putExtra(FileSelector.RESULT, filePath);
                    setResult(RESULT_OK, resultIntent);
                    finish();

                }
            });
        } else {
            fileSelector.setOnSelectCompleteListener(new FileSelector.OnSelectCompleteListener() {
                @Override
                public void onSelectComplete(String filePath) {

                    Intent resultIntent = new Intent(getApplicationContext(), aClass);
                    ArrayList<String> path = new ArrayList<>(1);
                    path.add(filePath);
                    resultIntent.putExtra(FileSelector.RESULT, path);
                    setResult(RESULT_OK, resultIntent);
                    finish();

                }
            });

        }

        this.actionBarModel = fileConfig.actionBarMode;
        if (fileConfig.actionBarMode) {
            enableActionBarMode();
        }

        fileSelector.hitTitle();

        setTitle(fileConfig == null ? "选择文件" : fileConfig.title);

        setContentView(fileSelector.getFileSelector());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (actionBarModel) {

            ok = menu.add(0, 10086, 1, "确定");
            ok.setIcon(R.drawable.ic_check_white_48dp);
            ok.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            ok.setVisible(false);

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarModel) {
            switch (item.getItemId()) {
                case 10086:
                    ArrayList<String> paths = fileSelector.getSelectFilePaths();
                    Intent intent = new Intent(getApplicationContext(), aClass);
                    intent.putStringArrayListExtra(FileSelector.RESULT, paths);
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                case android.R.id.home:
                    setResult(RESULT_CANCELED);
                    finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void enableActionBarMode() {
        if (null == getActionBar() && null == getSupportActionBar()) {

            actionBarModel = false;

        } else {

            fileSelector.dismissOptionBar();

            fileSelector.setOnSelectCountChangeListener(new FileSelector.onSelectCountChangeListener() {
                @Override
                public void onSelectCountChange(int selectCount) {
                    if (selectCount > 0) {
                        ok.setVisible(true);
                    } else {
                        ok.setVisible(false);
                    }
                }
            });

            fileSelector.setOnListUpdataComplete(new FileSelector.onListUpdataComplete() {
                @Override
                public void onComplete() {
                    ok.setVisible(false);
                }
            });

            if (null == getActionBar()) {

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            } else {

                getActionBar().setDisplayHomeAsUpEnabled(true);

            }
        }
    }
}
