package joey.com.leetroid.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import joey.com.leetroid.R;

public class WebFragment extends Fragment implements View.OnClickListener {

    private View mRootView;

    private WebView mWebView;

    private Button mBackButton;

    private Button mRefreshButton;

    private Button mForwardButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            View view = inflater.inflate(R.layout.web_fragment, container, false);
            initWebView(view);
            mRootView = view;
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initWebView(View view) {
        mBackButton = (Button) view.findViewById(R.id.back);
        mRefreshButton = (Button) view.findViewById(R.id.refresh);
        mForwardButton = (Button) view.findViewById(R.id.forward);
        mBackButton.setOnClickListener(this);
        mRefreshButton.setOnClickListener(this);
        mForwardButton.setOnClickListener(this);

        mWebView = (WebView) view.findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        mWebView.setInitialScale(1);

        mWebView.setWebViewClient(new LeetWebViewClient());
        mWebView.setWebChromeClient(new LeetWebChromeClient());

        mWebView.loadUrl("https://leetcode.com/discuss/");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back:
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                }
                break;
            case R.id.refresh:
                mWebView.reload();
                break;
            case R.id.forward:
                if (mWebView.canGoForward()) {
                    mWebView.goForward();
                }
                break;
        }
    }

    public class LeetWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    public class LeetWebChromeClient extends WebChromeClient {

        @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }


        @Override
        public void onPermissionRequest(PermissionRequest request) {
            super.onPermissionRequest(request);
        }
    }
}
