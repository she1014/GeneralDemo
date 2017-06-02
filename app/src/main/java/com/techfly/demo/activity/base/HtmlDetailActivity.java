package com.techfly.demo.activity.base;


import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.techfly.demo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Banner详情
 */
public class HtmlDetailActivity extends BaseActivity {

    @InjectView(R.id.base_wv)
    WebView base_wv;

    private String m_getIntentTitle = "";
    private String m_getIntentContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_webview);

        ButterKnife.inject(this);

        initBaseView();

        initBackButton(R.id.top_back_iv);
        setTranslucentStatus(R.color.main_bg);

        loadIntent();
    }

    private void loadIntent() {

        m_getIntentTitle = getIntent().getStringExtra(Constant.CONFIG_INTENT_TITLE);
        m_getIntentContent = getIntent().getStringExtra(Constant.CONFIG_INTENT_CONTENT);

        showProcessDialog();

        setBaseTitle(m_getIntentTitle, 0);
        initWebView(m_getIntentContent);

    }

    private void initWebView(String content) {
        WebSettings settings = base_wv.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        content = content.replaceAll("<img", "<img style=\\\"width:100% !important;\\\"");

        closeProcessDialog();
        base_wv.getSettings().setDefaultTextEncodingName("UTF-8");
        base_wv.loadData(content, "text/html; charset=UTF-8", null);//这种写法可以正确解码
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);
        closeProcessDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (base_wv != null) {
            base_wv.destroy();
        }
    }

}
