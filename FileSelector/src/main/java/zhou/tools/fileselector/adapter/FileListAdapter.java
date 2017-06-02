package zhou.tools.fileselector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import zhou.tools.fileselector.FileSelector;
import zhou.tools.fileselector.R;
import zhou.tools.fileselector.utils.FileType;

/**
 * Created by zzhoujay on 2014/12/30 0030.
 */

public class FileListAdapter extends BaseAdapter {

    private Context context;
    private List<HashMap<String, Object>> values;
    private boolean multi;

    private boolean isSafe;
    private int selectCount;

    private onSelectCountChangeListener onSelectCountChangeListener;

    public FileListAdapter(Context context, List<HashMap<String, Object>> values, boolean flag) {

        this.context = context;
        this.values = values;
        this.multi = flag;
        this.selectCount = 0;
        this.isSafe = true;

    }

    @Override
    public int getCount() {
        return values == null ? 0 : values.size();
    }

    @Override
    public Object getItem(int position) {
        return values == null ? null : values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.zhou_file_list_item, null);

            holder = new Holder();
            holder.name = (TextView) convertView.findViewById(R.id.zhou_file_item_name);
            holder.icon = (ImageView) convertView.findViewById(R.id.zhou_file_item_icon);
            holder.check = (CheckBox) convertView.findViewById(R.id.zhou_file_item_check);

            holder.name.setTextColor(context.getResources().getColor(FileSelector.theme.textColor));

            if (multi) {
                holder.check.setVisibility(View.VISIBLE);
            } else {
                holder.check.setVisibility(View.GONE);
            }

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        String name = values.get(position).get(FileSelector.NAME).toString();
        int icon = (int) values.get(position).get(FileSelector.ICON);


        if (name != null) {
            holder.name.setText(name);
        }

        if (icon != 0) {
            holder.icon.setImageResource(icon);
        }


        //判断是否为多选情况
        if (multi) {

            isSafe = false;

            int checkedState = (int) values.get(position).get(FileSelector.SELECT);

            //必须在设置状态之前绑定监听器，不然会产生一些莫名其妙的Bug（想了好久想出来的，居然突然就忘了。。。）
            holder.check.setOnCheckedChangeListener(new CheckBoxListener(position));


            if (checkedState == FileType.forbid) {
                holder.check.setVisibility(View.INVISIBLE);
            } else {
                holder.check.setVisibility(View.VISIBLE);
                switch (checkedState) {
                    case FileType.selected:
                        holder.check.setChecked(true);
                        break;
                    case FileType.unselect:
                        holder.check.setChecked(false);
                        break;
                }
            }

            isSafe = true;

        } else {
//            holder.check.setVisibility(View.GONE);
        }


        return convertView;
    }

    private class Holder {
        ImageView icon;
        TextView name;
        CheckBox check;
    }

    /**
     * 自定义CheckBox状态改变监听器
     * <p/>
     * 可以接收绑定监听器时的position（这很重要）
     */
    private class CheckBoxListener implements CompoundButton.OnCheckedChangeListener {

        private int index;

        public CheckBoxListener(int index) {
            this.index = index;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!isSafe)
                return;

            if (isChecked)
                selectCount++;
            else
                selectCount--;

            if (onSelectCountChangeListener != null)
                onSelectCountChangeListener.onSelectCountChange(getSelectCount());

            values.get(index).put(FileSelector.SELECT, isChecked ? FileType.selected : FileType.unselect);
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        selectCount = 0;
    }

    public int getSelectCount() {
        return selectCount;
    }

    public static interface onSelectCountChangeListener {
        public void onSelectCountChange(int selectCount);
    }

    public void setOnSelectCountChangeListener(FileListAdapter.onSelectCountChangeListener onSelectCountChangeListener) {
        this.onSelectCountChangeListener = onSelectCountChangeListener;
    }

    public void clearAllSelect() {
        for (HashMap<String, Object> v : values) {
            if (v.get(FileSelector.SELECT).equals(FileType.selected))
                v.put(FileSelector.SELECT, FileType.unselect);
        }
        notifyDataSetChanged();
    }

}
