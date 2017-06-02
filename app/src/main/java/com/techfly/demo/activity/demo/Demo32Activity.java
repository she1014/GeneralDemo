package com.techfly.demo.activity.demo;


import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.EditText;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity3;
import com.techfly.demo.util.LogsUtil;
import com.tencent.smtt.sdk.TbsVideo;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * 腾讯X5 内核 WebView，待优化，点击链接无法跳转
 */
public class Demo32Activity extends BaseActivity3 {

    @InjectView(R.id.tencent_webview)
    WebView base_wv;
    @InjectView(R.id.top_title_et)
    EditText top_title_et;

    private String m_getIntentTitle = "";
    private String m_getIntentContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo32);

        ButterKnife.inject(this);

        setTranslucentStatus(R.color.main_bg);

        getWindow().setFormat(PixelFormat.TRANSLUCENT);//（这个对宿主没什么影响，建议声明）
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        initWebView();
//        loadurl(base_wv, "http://www.baidu.com");
        loadurl(base_wv, "http://i2e.cn/m/jd/");
//


    }

    @OnClick(R.id.top_back_iv)
    public void exit(){
        this.finish();
    }

    @OnClick(R.id.top_right2_tv)
    public void goBrowse(){
//        loadurl(base_wv, "http://www.baidu.com");
        String url = top_title_et.getEditableText().toString().trim();
        if(url.startsWith("http://")){
            loadurl(base_wv, url);
        }else{
            loadurl(base_wv, "http://www."+url);
        }
    }

    @OnClick(R.id.top_right_tv)
    public void goPlay(){
        if(TbsVideo.canUseTbsPlayer(this)){
            TbsVideo.openVideo(this,"http://mvvideo2.meitudata.com/5785a7e3e6a1b824.mp4");
//            TbsVideo.openVideo(this,"/storage/emulated/0/DCIM/Camera/视频/VID_20161210_153323.mp4");
        }
    }


    private void initWebView() {

        base_wv.getSettings().setJavaScriptEnabled(true);//可用JS
//        base_wv.getSettings().setAllowFileAccess(true);  //设置可以访问文件
//        base_wv.getSettings().setDomStorageEnabled(true);//用于持久化的本地存储，除非主动删除数据，否则数据是永远不会过期的。
//        base_wv.clearCache(true);
//        base_wv.setSaveEnabled(false);
//        base_wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//        base_wv.getSettings().setPluginsEnabled(true);
//        base_wv.getSettings().setAllowFileAccess(true);
//        base_wv.getSettings().setPluginState(WebSettings.PluginState.ON);
//        base_wv.getSettings().setUseWideViewPort(true);
//        base_wv.getSettings().setLoadWithOverviewMode(true);
//        base_wv.getSettings().setSavePassword(true);
//        base_wv.getSettings().setSaveFormData(true);
//        base_wv.getSettings().setAllowContentAccess(true);
//        base_wv.getSettings().setAppCacheEnabled(false);
//        base_wv.getSettings().setBuiltInZoomControls(false);
//        base_wv.getSettings().setUseWideViewPort(true);
//        base_wv.getSettings().setLoadWithOverviewMode(true);
//        base_wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        base_wv.getSettings().setAllowFileAccess(true);  //设置可以访问文件
//        base_wv.getSettings().setDomStorageEnabled(true);//用于持久化的本地存储，除非主动删除数据，否则数据是永远不会过期的。
//        base_wv.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);//滚动条风格，为0就是不给滚动条留空间，滚动条覆盖在网页上

        base_wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                LogsUtil.normal("webView="+webView+",s="+s);
                webView.loadUrl(s);
                return true;
//                return super.shouldOverrideUrlLoading(webView, s);
            }
        });

        base_wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                LogsUtil.normal("i=" + i);
                if(i == 0){
                    showProcessDialog();
                }else if(i == 100){
                    closeProcessDialog();
                }
                super.onProgressChanged(webView, i);
            }
        });
    }

    public void loadurl(WebView webView, String url) {
        webView.loadUrl(url);//载入网页
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (base_wv != null && base_wv.canGoBack()) {
                base_wv.goBack();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);
//        closeProcessDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (base_wv != null) {
            base_wv.destroy();
        }
    }

}
