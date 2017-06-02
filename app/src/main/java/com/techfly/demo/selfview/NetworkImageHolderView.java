package com.techfly.demo.selfview;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.CBPageAdapter;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.activity.base.HtmlDetailActivity;
import com.techfly.demo.bean.BannerBean;
import com.techfly.demo.util.ImageLoaderUtil;
import com.techfly.demo.util.PreferenceUtil;


/**
 * Created by Administrator on 2015/11/10.
 */
public class NetworkImageHolderView implements CBPageAdapter.Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(final Context context, final int position, String data) {
        if (!TextUtils.isEmpty(data)) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new Gson();
                    String result = PreferenceUtil.getSharePreference(context, Constant.CONFIG_PREFERENCE_INDEX_BANNER);
                    BannerBean data = gson.fromJson(result, BannerBean.class);
                    if (data != null) {
                        Intent intent = new Intent(context, HtmlDetailActivity.class);
                        intent.putExtra(Constant.CONFIG_INTENT_ID, data.getData().get(position).getId() + "");
                        context.startActivity(intent);
                    }
                }
            });
            ImageLoader.getInstance().displayImage(data, imageView, ImageLoaderUtil.mBannerLoaderOptions);
        }
    }
}
