package zhou.tools.fileselector;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import zhou.tools.fileselector.config.FileConfig;

/**
 * 文件选择器Dialog态
 *
 * @param FileConfig 接收一个FileConfig对象，通过setArguments传入（可选）
 *                   <p/>
 *                   Created by zzhoujay on 2014/12/30 0030.
 */
public class FileSelectorDialog extends DialogFragment {
    private FileSelector fileSelector;
    private FileConfig fileConfig;
    private OnSelectFinish onSelectFinish;
    private OnSelectCancel onSelectCancel;


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            try {
                fileConfig = (FileConfig) bundle.getSerializable(FileConfig.FILE_CONFIG);
            } catch (Exception e) {
                e.printStackTrace();
                fileConfig = new FileConfig();
            }
        }

        fileSelector = new FileSelector(getActivity(), fileConfig);
        fileSelector.setOnCancelListener(new FileSelector.OnCancelListener() {
            @Override
            public void OnCancel() {
                if (onSelectCancel != null)
                    onSelectCancel.onSelectCancel();
                dismiss();
            }
        });

        boolean multi = fileConfig.multiModel;
        if (multi) {
            fileSelector.setOnConfirmListener(new FileSelector.OnConfirmListener() {
                @Override
                public void OnConfirm(ArrayList<String> filePaths) {
                    if (onSelectFinish != null)
                        onSelectFinish.onSelectFinish(filePaths);
                    dismiss();
                }
            });
        } else {
            fileSelector.setOnSelectCompleteListener(new FileSelector.OnSelectCompleteListener() {
                @Override
                public void onSelectComplete(String filePath) {
                    if (onSelectFinish != null) {
                        ArrayList<String> ps = new ArrayList<String>(1);
                        ps.add(filePath);
                        onSelectFinish.onSelectFinish(ps);
                    }
                    dismiss();
                }
            });
        }
        this.setStyle(STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fileSelector.getFileSelector();
    }

    /**
     * 选择完成后的回调接口
     */
    public static interface OnSelectFinish {
        //返回一个路径的数组
        public void onSelectFinish(ArrayList<String> paths);
    }

    /**
     * 取消选择的回调接口
     */
    public static interface OnSelectCancel {

        public void onSelectCancel();
    }

    public void setOnSelectFinish(OnSelectFinish onSelectFinish) {
        this.onSelectFinish = onSelectFinish;
    }

    public void setOnSelectCancel(OnSelectCancel onSelectCancel) {
        this.onSelectCancel = onSelectCancel;
    }
}
