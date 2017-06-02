package com.techfly.demo.activity.demo;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.util.LogsUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Demo28Activity extends BaseActivity {

    @InjectView(R.id.demo28_show_tv)
    TextView demo28_show_tv;
    @InjectView(R.id.webview)
    WebView webview;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo28);

        ButterKnife.inject(this);

        initBaseView();
        setBaseTitle("Demo28", 0);
        initBackButton(R.id.top_back_iv);
        setTranslucentStatus(R.color.main_bg);

        initWebView();

//        loadData();

        loadurl(webview,"https://www.12306.cn/mormhweb/");
    }

    private void initWebView() {

        showProcessDialog();

        webview.getSettings().setJavaScriptEnabled(true);//可用JS
        webview.getSettings().setAllowFileAccess(true);  //设置可以访问文件
        webview.getSettings().setDomStorageEnabled(true);//用于持久化的本地存储，除非主动删除数据，否则数据是永远不会过期的。

        webview.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);//滚动条风格，为0就是不给滚动条留空间，滚动条覆盖在网页上

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        webview.setWebChromeClient(
                new WebChromeClient() {
                    public void onProgressChanged(WebView view, int progress) {// 载入进度改变而触发
                        if (progress == 100) {
                            closeProcessDialog();
                        }
                        super.onProgressChanged(view, progress);
                    }

                    //扩展支持alert事件
                    @Override
                    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("xxx提示").setMessage(message).setPositiveButton("确定", null);
                        builder.setCancelable(false);
                        builder.setIcon(R.drawable.ic_launcher);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        result.confirm();
                        return true;
                    }
                }
        );

        webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    public void loadurl(final WebView view, final String url) {
        view.loadUrl(url);//载入网页
    }

    private void setHtmlData(String content) {
        WebSettings settings = webview.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webview.getSettings().setDefaultTextEncodingName("UTF-8");
        webview.loadData(content, "text/html; charset=UTF-8", null);//这种写法可以正确解码
    }


    private void loadData() {
        getHttpsTestApi("https://www.12306.cn/mormhweb/",1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        closeProcessDialog();
        LogsUtil.normal("result=" + result + ",type=" + type);
        setHtmlData(result);
    }
}
