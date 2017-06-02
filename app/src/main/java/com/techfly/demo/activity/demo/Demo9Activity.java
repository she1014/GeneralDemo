package com.techfly.demo.activity.demo;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.LableBean;
import com.techfly.demo.selfview.photepicker.PhotoPickerActivity;
import com.techfly.demo.selfview.photepicker.utils.PhotoPickerIntent;
import com.techfly.demo.selfview.richeditor.RichTextEditor;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 食品菜谱——上传
 */
public class Demo9Activity extends BaseActivity {

    @InjectView(R.id.classify_tv)
    TextView classify_tv;
    @InjectView(R.id.edit_titleEt)
    EditText edit_titleEt;
    @InjectView(R.id.edit_contentEt)
    RichTextEditor edit_contentEt;

    private static final int REQUEST_CODE_PICK_IMAGE = 1023;
    private static final int REQUEST_CODE_CLASSIFY = 1024;
    private ArrayList<String> curPhoto = null;

    //    private List<String> contentList = new ArrayList<String>();
//    private List<String> picList = new ArrayList<String>();
    private List<LableBean> picBeanList = new ArrayList<LableBean>(); //以<key,value>方式保存 文字图片路径
    private List<LableBean> idBeanList = new ArrayList<LableBean>();  //以<key,value>方式保存 文字图片id

    //    private List<String> picIdList = new ArrayList<String>();   //pic的id
    private List<Integer> locationList = new ArrayList<Integer>();
    private List<String> picList = new ArrayList<String>();//pic的path

    private String m_title = "";
    private String m_tags = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo9);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);

        setTranslucentStatus(R.color.main_bg);

        initBaseView();
        setBaseTitle("Demo9", "提交");
        initBackButton(R.id.top_back_iv);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("TTSS", "onActivityResult,requestCode=" + requestCode + ",requestCode=" + resultCode);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            curPhoto = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
            if(curPhoto.size() > 0){

                String path = "file://"+curPhoto.get(0);
                Uri uri = Uri.parse(path);
                insertBitmap(getRealFilePath(uri));
            }
            return;
        }
        Log.i("TTSS", "onActivityResult,data=" + data.getStringExtra(Constant.CONFIG_INTENT_TITLE));
        if(requestCode == REQUEST_CODE_CLASSIFY){
            String reback = data.getStringExtra(Constant.CONFIG_INTENT_TITLE);
            String[] split = reback.split(",");
            if(split.length == 2){
                classify_tv.setText(split[1]);
                m_tags = split[0];
            }
        }
    }

    /**
     * 添加图片到富文本剪辑器
     *
     * @param imagePath
     */
    private void insertBitmap(String imagePath) {
        edit_contentEt.insertImage(imagePath);
    }

    /**
     * 负责处理编辑数据提交等事宜，请自行实现
     */
    protected void dealEditData(List<RichTextEditor.EditData> editList) {

        boolean isContinue = true;

//        Log.i("TTSS","dealEditData="+picBeanList.size()+",isContinue="+isContinue);

        for (RichTextEditor.EditData itemData : editList) {
            if (itemData.inputStr != null) {

                LableBean bean = new LableBean(itemData.inputStr,"");
                picBeanList.add(bean);
            } else if (itemData.imagePath != null) {
                LableBean bean = new LableBean("",itemData.imagePath);
                picBeanList.add(bean);
            }
        }

//        Log.i("TTSS","Size111="+picBeanList.size()+",isContinue="+isContinue);

        if(picBeanList.size() == 1){
            if(TextUtils.isEmpty(picBeanList.get(0).getId()) && TextUtils.isEmpty(picBeanList.get(0).getName())){
                ToastUtil.DisplayToast(Demo9Activity.this, "正文内容不能为空!");
                isContinue = false;
                clearAll();
                return;
            }
        }

//        Log.i("TTSS","Size222="+picBeanList.size()+",isContinue="+isContinue);

        if(isContinue){
            //移除最后一个为空的数据
            if(picBeanList.size() > 0 && TextUtils.isEmpty(picBeanList.get(picBeanList.size()-1).getId())){
                picBeanList.remove(picBeanList.size() - 1);
            }

            for(int i = 0;i < picBeanList.size();i++){
                if(!TextUtils.isEmpty(picBeanList.get(i).getName())){
                    locationList.add(i);
                    picList.add(picBeanList.get(i).getName());
                }
            }

            uploadImages();
        }

    }

    /*
     * 清除ArrayList
     */
    private void clearAll(){

        picList.clear();
        locationList.clear();
        picBeanList.clear();
        idBeanList.clear();
    }

    /**
     * 根据Uri获取图片文件的绝对路径
     */
    public String getRealFilePath(final Uri uri) {
        if (null == uri) {
            return null;
        }

        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /*
     * 上传步骤图片，成功后上传整个菜谱
     */
    private void uploadImages(){

        for(int i=0;i < picBeanList.size();i++){
            Log.i("TTSS",i+"=上传前=>" + picBeanList.get(i).getId()+","+picBeanList.get(i).getName());
        }

        if (picList != null && !picList.isEmpty()) {
            //先调用图片上传接口
            /*AppClient.getInstance().uploadImgs(Constant.CURRENT_UID,picList,this, new GetBeanCallBack<ImgBean>() {
                @Override
                public void getResult(ImgBean imgBean, int type) {
                    if (type == Constant.REQUEST_TYPE_UPLOAD_PICS) {
                        //上传成功
                        if (imgBean != null) {
                            if (imgBean.getData() != null) {
                                for(int i = 0;i < imgBean.getData().size();i++){
//                                    Log.i("TTSS",i+"=>"+imgBean.getData().get(i).getData().getId()+",位置:"+locationList.get(i));

                                    LableBean bean = new LableBean("1","22");
                                    picBeanList.set(locationList.get(i),bean);
                                }
                            }
//                            uploadFoodMenuApi(Constant.CURRENT_UID, m_title, m_tags, picBeanList);
                            clearAll();
                        }
                    } else {
                        ToastUtil.DisplayToast(Demo9Activity.this, "上传图片失败，请重试!");
                        clearAll();
                    }
                }
            });*/
        } else {
//            uploadFoodMenuApi(Constant.CURRENT_UID, m_title, m_tags, picBeanList);
            clearAll();
        }
    }

    //提交
    @OnClick(R.id.top_right_tv)
    public void submit(){

        m_title = edit_titleEt.getEditableText().toString();
//        m_tags = classify_tv.getText().toString();

        if(TextUtils.isEmpty(m_title)){
            ToastUtil.DisplayToast(this,"标题不能为空！");
            return;
        }
        if(TextUtils.isEmpty(m_tags)){
            ToastUtil.DisplayToast(this,"请选择分类！");
            return;
        }

        List<RichTextEditor.EditData> editList = edit_contentEt.buildEditData();


        // 下面的代码可以上传、或者保存，请自行实现
        dealEditData(editList);

    }

    //选择分类
    @OnClick(R.id.rl_mine2)
    public void selectClassify(){
        ToastUtil.DisplayToast(this,"跳转到分类?");
//        Intent intent = new Intent(Demo9Activity.this,FoodClassifyActivity.class);
//        startActivityForResult(intent, REQUEST_CODE_CLASSIFY);
    }

    //文字编辑
    @OnClick(R.id.edit_wordLinear)
    public void contentEdit(){
        ToastUtil.DisplayToast(this, "文字编辑");
    }

    //图文编辑
    @OnClick(R.id.edit_picLinear)
    public void picEdit(){
//        ToastUtil.DisplayToast(this,"图文编辑");
        PhotoPickerIntent intent = new PhotoPickerIntent(Demo9Activity.this);
        intent.setPhotoCount(1);
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    //删除
    @OnClick(R.id.edit_deleteEt)
    public void delete(){
//        ToastUtil.DisplayToast(this,"删除?");
        edit_titleEt.setText("");
//        edit_contentEt.setText("");
    }

    public void onEventMainThread(EventBean bean) {
        if (bean.getAction().equals(EventBean.EVENT_CLOSE_CURRENT_ACTIVITY)) {
            this.finish();
        }
//        if (bean.getAction().equals(EventBean.EVENT_SAVE_AND_EXIT)) {
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
//        ToastUtil.DisplayToast(this,"草稿已保存!");
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        if(type == Constant.API_REQUEST_SUCCESS){
            try{
               /* Gson gson = new Gson();
                FocusBean data = gson.fromJson(result,FocusBean.class);
                if(data != null){
                    if(data.getState() == 1 && data.getData().equals("true")){
                        DialogUtil.showUploadSuccess(Demo9Activity.this, "食品菜谱上传成功,是否离开?");
                    }else{
                        ToastUtil.DisplayToast(this,data.getData());
                    }
                }*/
            }catch(Exception e){
                ToastUtil.DisplayToast(this,Constant.TOAST_MEG_REBACK_ERROR);
                e.printStackTrace();
            }
        }
    }
}
