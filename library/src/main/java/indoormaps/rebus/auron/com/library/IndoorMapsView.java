package indoormaps.rebus.auron.com.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;


/**
 * Created by luca on 6/21/16.
 */

public class IndoorMapsView extends RelativeLayout {


    private WebView webView;

    public IndoorMapsView(Context context) {
        super(context);
        Log.d("IndoorMapsView", "base costructor called;");

    }


    public IndoorMapsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("IndoorMapsView", "second costructor called;");
        initMapsView(context);

    }

    public IndoorMapsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("IndoorMapsView", "terzo costructor called;");

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IndoorMapsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d("IndoorMapsView", "second costructor called;");
    }

    private void initMapsView(Context context) {
        webView = new WebView(context);


        WebView indoorMaps = new WebView(context);
        indoorMaps.setScrollContainer(false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        indoorMaps.setLayoutParams(params);
        this.addView(indoorMaps);
        webView.addJavascriptInterface(new IndoorViewListener(context), "Android");
        indoorMaps.setWebViewClient(new WebClient());
        indoorMaps.getSettings().setSupportZoom(true);
        indoorMaps.getSettings().setJavaScriptEnabled(true);
        indoorMaps.getSettings().setUseWideViewPort(true);
        indoorMaps.loadUrl("file:///android_asset/maps.html");
    }

    public class WebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            Log.d("reload", "reload url");
            view.loadUrl(url);
            return true;
        }
    }
}
