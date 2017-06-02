package zhou.tools.fileselector;

import android.app.AlertDialog;
import android.content.Context;

import java.util.ArrayList;

import zhou.tools.fileselector.config.FileConfig;

/**
 * Created by zzhoujay on 2015/3/13 0013.
 */
public class FileSelectorAlertDialog {

    private FileSelector fileSelector;
    private OnSelectFinishListener onSelectFinishListener;
    private AlertDialog alertDialog;

    public FileSelectorAlertDialog(Context context) {
        this(context, new FileConfig());
    }

    public FileSelectorAlertDialog(Context context, FileConfig fileConfig) {
        fileSelector = new FileSelector(context, fileConfig);

        if (fileConfig.multiModel) {
            fileSelector.setOnConfirmListener(new FileSelector.OnConfirmListener() {
                @Override
                public void OnConfirm(ArrayList<String> filePaths) {
                    if (onSelectFinishListener != null)
                        onSelectFinishListener.selectFinish(filePaths);
                }
            });
        } else {
            fileSelector.setOnSelectCompleteListener(new FileSelector.OnSelectCompleteListener() {
                @Override
                public void onSelectComplete(String filePath) {
                    if(onSelectFinishListener!=null){
                        ArrayList<String> paths=new ArrayList<String>(1);
                        paths.add(filePath);
                        onSelectFinishListener.selectFinish(paths);
                    }
                }
            });
        }

        fileSelector.setOnCancelListener(new FileSelector.OnCancelListener() {
            @Override
            public void OnCancel() {
                alertDialog.dismiss();
            }
        });

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setView(fileSelector.getFileSelector());
        alertDialog=builder.create();
    }

    public void show(){
        alertDialog.show();
    }

    public static interface OnSelectFinishListener {
        public void selectFinish(ArrayList<String> paths);
    }

    public OnSelectFinishListener getOnSelectFinishListener() {
        return onSelectFinishListener;
    }

    public void setOnSelectFinishListener(OnSelectFinishListener onSelectFinishListener) {
        this.onSelectFinishListener = onSelectFinishListener;
    }
}
