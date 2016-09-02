package indoormaps.rebus.auron.com.library;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;


/**
 * Created by luca on 6/21/16.
 */

public class IndoorMapsView extends RelativeLayout {



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

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    private void initMapsView(Context context) {



        WebView indoorMaps = new WebView(context);
        indoorMaps.setScrollContainer(false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        indoorMaps.setLayoutParams(params);
        this.addView(indoorMaps);
        indoorMaps.getSettings().setJavaScriptEnabled(true);
        indoorMaps.addJavascriptInterface(new IndoorViewListener(context), "Android");
        indoorMaps.setWebViewClient(new WebClient());
        indoorMaps.getSettings().setSupportZoom(true);

  //      indoorMaps.evaluateJavascript();

        indoorMaps.getSettings().setUseWideViewPort(true);
        indoorMaps.setWebChromeClient(new ChromeClient());
        indoorMaps.loadUrl("file:///android_asset/maps.html");
    }

    public class ChromeClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            return true;//super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }

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
