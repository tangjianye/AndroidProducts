package cn.aorise.common.core.ui.base;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.aorise.common.R;
import cn.aorise.common.core.utils.assist.AoriseLog;

public abstract class BaseWebActivity extends BaseActivity {
    private final static String TAG = BaseWebActivity.class.getSimpleName();
    protected WebView mWebContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        setContentView(R.layout.aorise_activity_base_web);

        mWebContainer = (WebView) findViewById(R.id.web_container);
        mWebContainer.setWebViewClient(new BaseWebViewClient());
        mWebContainer.setWebChromeClient(new WebChromeClient());

        WebSettings webSettings = mWebContainer.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // webSettings.setDatabasePath("/data/data/"+this.getPackageName()+"/databases/");
    }

    @Override
    protected void initEvent() {

    }

    protected void loadUrl(String url) {
        AoriseLog.i(TAG, "loadUrl:" + url);
        if (null != url) {
            mWebContainer.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebContainer.canGoBack()) {
            mWebContainer.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public static class BaseWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }
}
