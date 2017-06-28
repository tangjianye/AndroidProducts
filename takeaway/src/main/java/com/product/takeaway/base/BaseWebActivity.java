package com.product.takeaway.base;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.product.takeaway.R;


/**
 * 公共WebActivity基类
 * Created by tangjy on 2017/3/1.
 */
public class BaseWebActivity extends AppCompatActivity {
    private final static String TAG = BaseWebActivity.class.getSimpleName();
    protected WebView mWebContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

//    private void initData() {
//        Bundle bundle = new Bundle();
//        bundle.putString("key", "url");
//
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        setContentView(R.layout.activity_base_web);

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


    /**
     * 加载URL地址
     *
     * @param url 网络请求地址
     */
    protected void loadUrl(String url) {
        Log.i(TAG, "loadUrl:" + url);
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
