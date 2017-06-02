package com.techfly.demo.activity.release;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.GoodBannerRvAdapter;
import com.techfly.demo.adpter.SelectorLvAdapter;
import com.techfly.demo.adpter.StyleRvAdapter;
import com.techfly.demo.bean.CityBean;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.GoodDetailBean;
import com.techfly.demo.bean.GoodStatusBean;
import com.techfly.demo.bean.LableBean;
import com.techfly.demo.bean.ProvinceBean;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.SmallCategoryBean;
import com.techfly.demo.bean.StepBean;
import com.techfly.demo.bean.StyleBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.interfaces.ItemClickListener;
import com.techfly.demo.interfaces.ItemMultClickListener;
import com.techfly.demo.selfview.ForbidScrollRecycleView;
import com.techfly.demo.selfview.FullyLinearLayoutManager;
import com.techfly.demo.selfview.TextImgItem;
import com.techfly.demo.selfview.photepicker.PhotoPickerActivity;
import com.techfly.demo.selfview.photepicker.utils.PhotoPickerIntent;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.ImageLoaderUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 商品发布
 */
public class CommentReleaseActivity<T> extends BaseActivity {

    @InjectView(R.id.release_list_pic_iv)
    ImageView release_list_pic_iv;

    @InjectView(R.id.release_banner_rv)
    ForbidScrollRecycleView release_banner_rv;
    @InjectView(R.id.release_style_rv)
    ForbidScrollRecycleView release_style_rv;

    @InjectView(R.id.release_goods_title_et)
    EditText release_goods_title_et;
//    @InjectView(R.id.release_goods_price_et)
//    EditText release_goods_price_et;
//    @InjectView(R.id.release_goods_price_unit_et)
//    EditText release_goods_price_unit_et;
//    @InjectView(R.id.release_surplus_et)
//    EditText release_surplus_et;
    @InjectView(R.id.release_price_lable_et)
    EditText release_price_lable_et;
//    @InjectView(R.id.release_market_price_tv)
//    EditText release_market_price_tv;
    @InjectView(R.id.release_feature_lable_et)
    EditText release_feature_lable_et;

    @InjectView(R.id.release_status_ti)
    TextImgItem release_status_ti;
    @InjectView(R.id.release_big_category_ti)
    TextImgItem release_big_category_ti;
    @InjectView(R.id.release_small_category_ti)
    TextImgItem release_small_category_ti;
    @InjectView(R.id.release_province_ti)
    TextImgItem release_province_ti;
    @InjectView(R.id.release_city_ti)
    TextImgItem release_city_ti;

    @InjectView(R.id.release_confirm_btn)
    Button release_confirm_btn;


    private GoodBannerRvAdapter rvAdapter;
    private FullyLinearLayoutManager linerLayoutManager;
    private List<StepBean> beanList = new ArrayList<StepBean>();

    private ArrayList<String> curPhoto = null;//当前选择图片路径

    //    private String listPath = "";//列表图片路径-【列表图为轮播图第一张】
    private ArrayList<String> bannerPathList = new ArrayList<String>();//上传前图片路径
    private ArrayList<Integer> positionList = new ArrayList<Integer>();//记录http链接路径
    private List<String> bannerUrlList = new ArrayList<String>(); //上传后地址
    private String designUrl = "";
    private int curBannerPosition = 0;

    private final static int REQUESTCODE_TYPE_DESIGN_SKETCH = 0x01;    //效果图
    private final static int REQUESTCODE_TYPE_BANNER_PICTURE = 0x02;   //Banner图片
    private final static int REQUESTCODE_TYPE_PICTURE_CUTBACK = 0x03;  //裁剪后

    private Boolean isShowDialog = true;
    private List<CityBean.DataEntity> cityList = new ArrayList<CityBean.DataEntity>();
    private List<String> provinceList = new ArrayList<String>();
    private List<LableBean.DataEntity> bigCategoryList = new ArrayList<LableBean.DataEntity>();
    private List<SmallCategoryBean.DataEntity> smallCategoryList = new ArrayList<SmallCategoryBean.DataEntity>();

    private TextImgItem[] tiArray = null;

    private User mUser = null;
    private String m_getIntentId = "";
    private Boolean m_isModify = false;//发布\修改

    private GoodDetailBean goodDetailBean = null;
    private String m_imgs = "";
    private String m_categoryId = "";
    private String m_cityId = "";

    //裁剪后
    private File cutFile = null;
    private Uri cutUri = null;

    private StyleRvAdapter styleRvAdapter;
    private FullyLinearLayoutManager linerLayoutManager2;
    private List<StyleBean> styleBeans = new ArrayList<StyleBean>();

    private int selectType = 0;//1-列表图；2-轮播图
    private int originStyleNum = 0;    //记录修改情况下的规格数目【已有规格不可置空，可置库存为0】

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_release);

        ButterKnife.inject(this);
        EventBus.getDefault().register(this);

        initBaseView();
        initBackButton(R.id.top_back_iv);
        setBaseTitle("发布商品", 0);

        setTranslucentStatus(R.color.main_bg);

        initView();

        setLinearLayoutRecyclerView();

        setLinearLayoutRecyclerView2();

        loadIntent();

        initEdit();
    }

    private void initView() {
        mUser = SharePreferenceUtils.getInstance(this).getUser();
        tiArray = new TextImgItem[]{release_status_ti, release_big_category_ti, release_small_category_ti, release_province_ti, release_city_ti};
    }

    /*
     * 默认一张示意图
     */
    private void initData() {
        for (int i = 0; i < 1; i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon_defualt_null);
            StepBean bean = new StepBean(i + "", (i + 1) + "", "", "", bmp);
            beanList.add(bean);
            bannerPathList.add("");
        }
    }

    private void loadIntent() {

        m_getIntentId = getIntent().getStringExtra(Constant.CONFIG_INTENT_GOODS_ID);

        if (m_getIntentId != null) {
            m_isModify = true;
        }

        if (m_isModify) {
            //修改-加载已有数据
            release_confirm_btn.setText("确认修改");
            setBaseTitle("商品修改", 0);

            showProcessDialog();
            getGoodsDetailApi(mUser.getmId(), mUser.getmToken(), m_getIntentId);
        } else {
            //新增-加载默认
            initData();
            addStyleItem();
        }
    }

    private void initEdit() {
        InputFilter[] emojiFilters = {CommonUtils.emojiFilter};
        release_goods_title_et.setFilters(emojiFilters);
        release_price_lable_et.setFilters(emojiFilters);
        release_feature_lable_et.setFilters(emojiFilters);
    }


    private void updateView(GoodDetailBean.DataEntity data) {

        designUrl  = data.getGoods_info().getImg();
        ImageLoader.getInstance().displayImage(designUrl, release_list_pic_iv, ImageLoaderUtil.mEmptyIconLoaderOptions);

        String[] picArray = data.getGoods_info().getImages().split(" ");

        List<StepBean> stepBeanList = new ArrayList<StepBean>();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon_defualt_null);
        for (int i = 0; i < picArray.length; i++) {

            bannerUrlList.add(picArray[i]);

            StepBean emptyBean = new StepBean(i + "", (i + 1) + "", "", "", bmp, picArray[i]);
            stepBeanList.add(emptyBean);
            bannerPathList.add(picArray[i]);
        }
        rvAdapter.addAll(stepBeanList);

        release_goods_title_et.setText(data.getGoods_info().getTitle()+"");
        release_feature_lable_et.setText(data.getGoods_info().getFeature_labels()+"");
        release_price_lable_et.setText(data.getGoods_info().getText()+"");

        m_categoryId = data.getGoods_info().getCategory_id()+"";


        //以下正常
        List<StyleBean> beans = new ArrayList<StyleBean>();
        for(int i = 0;i < data.getGoods_speces().size();i++){

            GoodDetailBean.DataEntity.GoodsSpecesEntity goodEntiy = data.getGoods_speces().get(i);

            StyleBean bean  = new StyleBean(goodEntiy.getId(),goodEntiy.getTitle(),goodEntiy.getGoods_price().getPrice_id(),goodEntiy.getGoods_price().getPrice()+"",
                    goodEntiy.getGoods_num().getNum_id(),goodEntiy.getGoods_num().getRest_num()+"");
            beans.add(bean);
        }
        styleRvAdapter.addAll(beans);

        originStyleNum = styleBeans.size();

        for(int i = 0;i < styleBeans.size();i++){
            LogsUtil.normal(i+"=getText=>"+styleBeans.get(i).getText());
        }

    }

    /**
     * 移除一栏，同时移除list中对应条目
     *
     * @param position
     */
    private void removeItem(int position) {
        rvAdapter.remove(position);
        bannerPathList.remove(position);
    }

    /*
     * 添加一栏，同时添加list
     */
    private void insertItem() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon_defualt_null);
        StepBean bean = new StepBean("0", "0", "0", "0", bmp);
        rvAdapter.insert(bean, rvAdapter.getItemCount());
        bannerPathList.add("");
    }

    private void setLinearLayoutRecyclerView() {

        linerLayoutManager = new FullyLinearLayoutManager(this);
        linerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        release_banner_rv.setLayoutManager(linerLayoutManager);
        rvAdapter = new GoodBannerRvAdapter(this, beanList);

        // 为Item具体实例点击3种事件
        rvAdapter.setItemClickListener(new ItemMultClickListener() {

            @Override
            public void onItemSubViewClick(int choice, int postion) {
                if (choice == 0) {  //添加

                    curBannerPosition = postion;

                    PhotoPickerIntent intent = new PhotoPickerIntent(CommentReleaseActivity.this);
                    intent.setPhotoCount(1);
                    startActivityForResult(intent, REQUESTCODE_TYPE_BANNER_PICTURE);

                } else if (choice == 1) {//删除
                    removeItem(postion);
                }
            }

            @Override
            public void onItemClick(View view, int postion) {
            }
        });
        // 设置Adapter
        release_banner_rv.setAdapter(rvAdapter);

    }

    private void setLinearLayoutRecyclerView2() {
        // 如果布局大小一致有利于优化
//        index_rank_rv.setHasFixedSize(true);
        linerLayoutManager2 = new FullyLinearLayoutManager(this);
        //设置方向
        linerLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        release_style_rv.setLayoutManager(linerLayoutManager2);

        // 使用RecyclerView提供的默认的动画效果
        release_style_rv.setItemAnimator(new DefaultItemAnimator());

//        Log.i("TTSS", "IngredientRvAdapter(this,ingredientList)=>" + styleBeans.size());
        styleRvAdapter = new StyleRvAdapter(this, styleBeans);
        // 为Item具体实例点击3种事件
        styleRvAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
//                ToastUtil.DisplayToast(AddIngredientActivity.this, "跳转到详细界面？");
            }

            @Override
            public void onItemLongClick(View view, int postion) {
            }

            @Override
            public void onItemSubViewClick(View view, int postion) {
//                ToastUtil.DisplayToast(DraftBoxActivity.this, "关注或取消关注!");
            }
        });

        // 设置Adapter
        release_style_rv.setAdapter(styleRvAdapter);
    }

    /*
     * type:状态=0,大类=1,小类=2,省份=3,城市=4
     */
    private void showSelectDialog(final List<T> selectList, final Context context, final int type) {

        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        View view = View.inflate(context, R.layout.dialog_select_list, null);
        window.setContentView(view);

        final TextView titleTv = (TextView) view.findViewById(R.id.dialog_title_tv);
        TextView cancelTv = (TextView) view.findViewById(R.id.dialog_cancel_tv);
        ListView lv = (ListView) view.findViewById(R.id.dialog_lv);

        final SelectorLvAdapter adapter = new SelectorLvAdapter(context, selectList);
        lv.setAdapter(adapter);

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        adapter.setItemClickListener(new ItemMultClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

                tiArray[type].setText(adapter.getItem(postion).toString());

                if (type == 1) {
                    isShowDialog = false;
                    getCategoryListApi(mUser.getiCode(), bigCategoryList.get(postion).getId() + "");
                    m_categoryId = bigCategoryList.get(postion).getId() + "";
                } else if (type == 2) {
                    m_categoryId = smallCategoryList.get(postion).getId() + "";
                } else if (type == 3) {
                    isShowDialog = false;
                    getCitysListApi(provinceList.get(postion));
                } else if (type == 4) {
                }

                dialog.dismiss();
            }

            @Override
            public void onItemSubViewClick(int choice, int postion) {
            }
        });

    }

    @OnClick(R.id.release_status_ti)
    public void selectStatus() {
        getGoodsListStatusApi();
    }

    @OnClick(R.id.release_big_category_ti)
    public void selectBigCategory() {
        getFruitCategoriesInfoApi(mUser.getiCode());
    }

    @OnClick(R.id.release_small_category_ti)
    public void selectSmallCategory() {

        String curBigCategory = release_big_category_ti.getText();

        LogsUtil.normal("大类数量=" + bigCategoryList.size());

        if (bigCategoryList.size() == 0) {
            ToastUtil.DisplayToast(this, "请先选择商品大类!");
            return;
        }

        for (int i = 0; i < bigCategoryList.size(); i++) {
            if (bigCategoryList.get(i).getName().equals(curBigCategory)) {
                getCategoryListApi(mUser.getiCode(), bigCategoryList.get(i).getId() + "");
                break;
            }
        }
    }

    @OnClick(R.id.release_province_ti)
    public void selectProvince() {
        getProvinceListApi();
    }

    @OnClick(R.id.release_city_ti)
    public void selectCity() {
        if (provinceList.size() == 0) {
            ToastUtil.DisplayToast(this, "请先选择省份!");
            return;
        }
        String curProvince = release_province_ti.getText();
        getCitysListApi(curProvince);
    }

    @OnClick(R.id.release_list_pic_iv)
    public void addListPicture() {
        PhotoPickerIntent intent = new PhotoPickerIntent(this);
        intent.setPhotoCount(1);
        startActivityForResult(intent, REQUESTCODE_TYPE_DESIGN_SKETCH);
    }

    @OnClick(R.id.release_add_item_rl)
    public void addNewItem() {
        insertItem();
    }

    @OnClick(R.id.release_add_style_linear)
    public void addStyleItem() {
        StyleBean bean = new StyleBean("", "", "");
        styleRvAdapter.insert(bean, styleRvAdapter.getItemCount());
    }

    @OnClick(R.id.release_confirm_btn)
    public void submit() {

        LogsUtil.normal("submit.designUrl="+designUrl+",styleBeans.size()="+styleBeans.size());

        if(designUrl.isEmpty()){
            ToastUtil.DisplayToast(CommentReleaseActivity.this,"请先上传商品图片!");
            return;
        }

        bannerUrlList.clear();
        bannerUrlList.add(designUrl);

        /*if(bannerUrlList.isEmpty()){
            ToastUtil.DisplayToast(CommentReleaseActivity.this,"至少需上传一张轮播图片!");
            return;
        }*/

        String title = release_goods_title_et.getEditableText().toString();
        String descrip = release_feature_lable_et.getEditableText().toString();
        String priceFeature = release_price_lable_et.getEditableText().toString();

//        LogsUtil.normal("title="+title+",descrip="+descrip+",priceFeature="+priceFeature);

        if(title.isEmpty()){
            ToastUtil.DisplayToast(CommentReleaseActivity.this,"商品名称不能为空!");
            return;
        }
        /*if(descrip.isEmpty()){
            ToastUtil.DisplayToast(CommentReleaseActivity.this,"商品描述不能为空!");
            return;
        }
        if(priceFeature.isEmpty()){
            ToastUtil.DisplayToast(CommentReleaseActivity.this,"价格标签不能为空!");
            return;
        }*/

        if(m_isModify){

            for(int i = 0;i < originStyleNum;i++){
                if(styleBeans.get(i).toString().isEmpty()){
                    ToastUtil.DisplayToast(CommentReleaseActivity.this,"第"+(i+1)+"条规格不能置空!");
                    return;
                }
            }

        }else{

            int baseCount = styleRvAdapter.getBaseDataCount();
            int fullCount = styleRvAdapter.getFullDataCount();

            LogsUtil.normal("基本消息数量:"+baseCount+"\n完整消息数目:"+fullCount);

            if(baseCount > 1){
                ToastUtil.DisplayToast(CommentReleaseActivity.this, "当前添加了多条规格\n规格描述不能为空!");
                return;
            }else if(baseCount == 1 && fullCount >= 1){
                ToastUtil.DisplayToast(CommentReleaseActivity.this, "当前添加了多条规格\n规格描述不能为空!");
                return;
            }else if(baseCount == 0 && fullCount == 0){
                ToastUtil.DisplayToast(CommentReleaseActivity.this, "至少需要添加一条规格!");
                return;
            }else{
                LogsUtil.normal("1条或多条规格可用,通过");
            }
        }

        if(m_categoryId.isEmpty()){
            ToastUtil.DisplayToast(CommentReleaseActivity.this,"请选择商品种类!");
            return;
        }

        showProcessDialog();
        if(m_isModify){
            postGoodModifyInfoApi(mUser.getmId(), mUser.getmToken(), m_getIntentId, designUrl, CommonUtils.listToString(bannerUrlList), title, "",
                    descrip, priceFeature, m_categoryId, styleBeans);
        }else{
            postReleaseGoodApi(mUser.getmId(), mUser.getmToken(),mUser.getiCode(),designUrl,CommonUtils.listToString(bannerUrlList),title,"",
                    descrip,priceFeature,m_categoryId,styleBeans);
        }

    }


    public void onEventMainThread(EventBean bean) {
        if (bean.getAction().equals(EventBean.EVENT_UPLOAD_FINISH)) {
            LogsUtil.normal("EVENT_UPLOAD_FINISH");
        }

        if (bean.getAction().equals(EventBean.EVENT_CLOSE_WAIT_DIALOG)) {
            closeProcessDialog();
        }

        if (bean.getAction().equals(EventBean.EVENT_CLOSE_CURRENT_ACTIVITY)) {
            setResult(RESULT_OK);
            this.finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == REQUESTCODE_TYPE_DESIGN_SKETCH || requestCode == REQUESTCODE_TYPE_BANNER_PICTURE) {
            curPhoto = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
            cutFile = new File(curPhoto.get(0));
            cutUri = Uri.fromFile(cutFile);
        }

        switch (requestCode) {
            case REQUESTCODE_TYPE_DESIGN_SKETCH:

                selectType = 1;

                if (data == null) {
                    return;
                }

                if (curPhoto.size() > 0) {
                    startImageZoom(cutUri, 4, 3, 1000, 750);
                }
                break;

            case REQUESTCODE_TYPE_BANNER_PICTURE:

                selectType = 2;

                if (data == null) {
                    return;
                }
                if (curPhoto.size() > 0) {
                    startImageZoom(cutUri, 1, 1, 800, 800);
                }
                break;

            case REQUESTCODE_TYPE_PICTURE_CUTBACK:

//                LogsUtil.normal("裁剪结束1:" + cutUri);

                if (data == null) {
                    return;
                }

                //file:///storage/emulated/0/Download/upload.png
                Bitmap bm = CommonUtils.getBitmapFromUri(cutUri, this);
//                LogsUtil.normal("裁剪结束2:" + bm);

                if (bm == null) {

                } else {

                    switch (selectType){
                        case 1://列表
                            release_list_pic_iv.setScaleType(ImageView.ScaleType.FIT_XY);
                            release_list_pic_iv.setImageBitmap(bm);
                            break;
                        case 2://轮播
                            StepBean picBean = new StepBean(curPhoto.get(0));
                            rvAdapter.updata(picBean, curBannerPosition);
                            bannerPathList.set(curBannerPosition, curPhoto.get(0));
                            break;
                    }

                    //上传
//                    postUploadPictureApi(mUser.getmId(), mUser.getmToken(), cutUri + "", Constant.UPLOAD_TYPE_GOOD_DESIGN, CommentReleaseActivity.this);
                    postUploadPictureApi(mUser.getmId(), mUser.getmToken(), curPhoto.get(0), Constant.UPLOAD_TYPE_GOOD_DESIGN, CommentReleaseActivity.this);

                }

                break;
        }
    }

    private void startImageZoom(Uri uri,int ax,int ay,int ox,int oy) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Log.i("TTSS", "startImageZoom..1");
            String url = getPath(PersonDataActivity.this, uri);
            intent.setDataAndType(Uri.fromFile(new File(url)), "image*//*");
        } else {
            Log.i("TTSS", "startImageZoom..2");
            intent.setDataAndType(uri, "image*//*");
        }*/
        intent.putExtra("crop", "false");
        intent.putExtra("aspectX", ax);
        intent.putExtra("aspectY", ay);
        intent.putExtra("outputX", ox);
        intent.putExtra("outputY", oy);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//设置返回路径
        intent.putExtra("return-data", false);        //设置为不返回数据
        startActivityForResult(intent, REQUESTCODE_TYPE_PICTURE_CUTBACK);
    }


    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        closeProcessDialog();
//
        Gson gson = new Gson();

        try {
            switch (type) {
                case Constant.API_GET_GOODS_LIST_STATUS_SUCCESS:
                    result = CommonUtils.removeBrace(result);
                    GoodStatusBean data1 = gson.fromJson(result, GoodStatusBean.class);
                    if (data1 != null) {
                        showSelectDialog((List<T>) data1.getData(), this, 0);
                    }
                    break;
                case Constant.API_GET_FRUIT_CATEGORIES_SUCCESS:
                    result = CommonUtils.removeBrace(result);
                    LableBean data2 = gson.fromJson(result, LableBean.class);
                    if (data2 != null) {
                        bigCategoryList = data2.getData();
                        showSelectDialog((List<T>) data2.getData(), this, 1);
                    }
                    break;
                case Constant.API_GET_CATEGORY_LIST_SUCCESS:
                    result = CommonUtils.removeBrace(result);
                    SmallCategoryBean data3 = gson.fromJson(result, SmallCategoryBean.class);
                    if (data3 != null) {
                        smallCategoryList = data3.getData();
                        if (isShowDialog) {
                            showSelectDialog((List<T>) data3.getData(), this, 2);
                        } else {
                            isShowDialog = true;
                            if (data3.getData().size() > 0) {
                                //选择大类后，默认选中第一个小类，同时默认ID
                                m_categoryId = data3.getData().get(0).getId()+"";
                                release_small_category_ti.setText(data3.getData().get(0).getName());
                            } else {
                                release_small_category_ti.setText("");
                            }
                        }
                    }
                    break;
                case Constant.API_GET_PROVINCE_LIST_SUCCESS:
                    result = CommonUtils.removeBrace(result);
                    ProvinceBean data4 = gson.fromJson(result, ProvinceBean.class);
                    if (data4 != null) {
                        provinceList = data4.getData();
                        showSelectDialog((List<T>) data4.getData(), this, 3);
                    }
                    break;
                case Constant.API_GET_CITY_LIST_SUCCESS:
                    result = CommonUtils.removeBrace(result);
                    CityBean data5 = gson.fromJson(result, CityBean.class);
                    if (data5 != null) {
                        cityList = data5.getData();
                        if (isShowDialog) {
                            showSelectDialog((List<T>) data5.getData(), this, 4);
                        } else {
                            isShowDialog = true;
                            if (data5.getData().size() > 0) {
                                release_city_ti.setText(data5.getData().get(0).getName());
                            } else {
                                release_city_ti.setText("");
                            }
                        }
                    }
                    break;

                case Constant.API_POST_GOODS_RELEASE_SUCCESS:

                    closeProcessDialog();

                    ReasultBean data6 = gson.fromJson(result, ReasultBean.class);
                    if (data6 != null) {
                        DialogUtil.showSuccessDialog(this, data6.getData(), EventBean.EVENT_CLOSE_CURRENT_ACTIVITY);
                    }
                    break;

                case Constant.API_POST_GOODS_MODIFY_SUCCESS:

                    closeProcessDialog();

                    ReasultBean data8 = gson.fromJson(result, ReasultBean.class);
                    if (data8 != null) {
                        DialogUtil.showSuccessDialog(this, data8.getData(), EventBean.EVENT_CLOSE_CURRENT_ACTIVITY);
                    }
                    break;

                case Constant.API_GET_GOODS_DETAIL_SUCCESS:
                    result = CommonUtils.removeBrace(result);
                    LogsUtil.normal("Detail.result="+result);
                    goodDetailBean = gson.fromJson(result, GoodDetailBean.class);

                    if (goodDetailBean != null) {
                        closeProcessDialog();
                        updateView(goodDetailBean.getData());
                    } else {
                        ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                    }
                    break;

                case Constant.API_POST_GOODS_EDIT_STATUS_SUCCESS:
                    ReasultBean data7 = gson.fromJson(result, ReasultBean.class);
                    if (data7 != null) {
                        DialogUtil.showSuccessDialog(this, data7.getData(), EventBean.EVENT_CLOSE_CURRENT_ACTIVITY);
                    } else {
                        ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                    }
                    break;
                case Constant.API_POST_UPLOAD_PICTURE_SUCCESS:
                    LogsUtil.normal("图片上传成功:curBannerPosition="+curBannerPosition+",selectType="+selectType+",result="+result);
                    ReasultBean data = gson.fromJson(result, ReasultBean.class);
                    if (data != null && data.getCode().equals("000")) {
                        LogsUtil.normal("图片上传成功:selectType="+selectType+",getData="+data.getData());
                        if(selectType == 1){
                            designUrl = data.getData();
                        }else if(selectType == 2){
                            if(curBannerPosition < bannerUrlList.size()){
                                bannerUrlList.set(curBannerPosition, data.getData());
                            }else{
                                bannerUrlList.add(data.getData());
                            }
                        }
                    }
                    break;

            }
        } catch (Exception e) {
            ToastUtil.DisplayToastDebug(CommentReleaseActivity.this, Constant.TOAST_MEG_REBACK_ERROR + "\n" + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
