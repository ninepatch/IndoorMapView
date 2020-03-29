package indoormaps.ninepatch.com.library;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import java.util.LinkedList;
import java.util.List;

import indoormaps.ninepatch.com.library.animate.ANIMATION;
import indoormaps.ninepatch.com.library.zoom.ZOOM;


/**
 * Created by luca on 6/21/16.
 */

public class IndoorMapsView extends RelativeLayout {


    private static final String TAG = IndoorMapsView.class.getName();
    protected static List<Marker> markers;
    private static boolean DEBUG = false;
    private IndoorViewListener indoorViewListener;
    private WebView indoorMaps;
    private Context context;
    private ZOOM zoom;

    public IndoorMapsView(Context context) {
        super(context);
    }

    public IndoorMapsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        indoorViewListener = new IndoorViewListener(context);
        this.context = context;
        initMapsView(context);
    }

    public IndoorMapsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IndoorMapsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public int getMarkerCount() {
        return markers.size();
    }

    @SuppressWarnings("ResourceType")
    public void setBackgroundColorRes(@ColorRes int color) {
        String colors = getResources().getString(color);
        changeBackgroundColor(colors);
    }


    @SuppressWarnings("ResourceType")
    public void setBackgroundColor(@ColorInt int color) {
        String colors = getResources().getString(color);
        changeBackgroundColor(colors);
    }

    public IndoorViewListener getIndoorViewListener() {
        return indoorViewListener;
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    private void initMapsView(Context context) {
        markers = new LinkedList<>();
        indoorViewListener.onMapLoading();
        indoorMaps = new WebView(context);
        indoorMaps.setScrollContainer(false);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        indoorMaps.setLayoutParams(params);
        this.addView(indoorMaps);
        indoorMaps.getSettings().setJavaScriptEnabled(true);
        indoorMaps.addJavascriptInterface(getIndoorViewListener(), "Android");
        indoorMaps.setWebViewClient(new WebClient());
        indoorMaps.getSettings().setSupportZoom(true);
        indoorMaps.getSettings().setUseWideViewPort(true);
        indoorMaps.setWebChromeClient(new ChromeClient());
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            indoorMaps.setWebContentsDebuggingEnabled(false);
        }
        */
        indoorMaps.loadUrl("file:///android_asset/maps.html");
    }


    public void addMarker(Marker marker) {

        for (Marker markerTest : markers) {
            if (markerTest.getId() == marker.getId()) {
                if (DEBUG)
                    Log.e(TAG, "marker id was duplicated ---- id: " + marker.getId());

                return;
            }
        }

        indoorMaps.loadUrl("javascript:addMarker(" + marker.getLat() + ","
                + marker.getLon() + ",'"
                + marker.getName() + "','"
                + marker.getImageLink() + "','"
                + marker.getId() + "','"
                + marker.getStyle().isShowLabel() + "','"
                + marker.getStyle().getLabelPx() + "','"
                + marker.getStyle().getLabelColor()
                + "');");

        markers.add(marker);
    }

    public void removeMarker(Marker marker) {
        Log.d(TAG, "remove marker");
        removeMarker(marker.getId());
    }


    public void removeMarker(final int markerId) {
        Log.d(TAG, "remove marker id: " + markerId);
        indoorMaps.post(new Runnable() {
            @Override
            public void run() {
                indoorMaps.loadUrl("javascript:removeMarker(" + markerId + ");");
            }
        });

        for (Marker markerTest : markers) {
            if (markerTest.getId() == markerId) {
                if (DEBUG)
                    Log.e(TAG, "marker id was duplicated ---- id: " + markerId);
                markers.remove(markerTest);
                return;
            }
        }


    }

    public void setZoom(ZOOM zoom) {
        setZoom(zoom.value());
    }

    public ZOOM getZoom() {
        return zoom;
    }

    public void setZoom(final int zoom) {
        this.zoom = ZOOM.fromInt(zoom);
        indoorMaps.post(new Runnable() {
            @Override
            public void run() {
                indoorMaps.loadUrl("javascript:setZoom('" + zoom + "');");
            }
        });

    }

    public int getZoomIntValue() {
        return zoom.value();
    }

    public void setCenter(Marker marker) {
        setCenter(marker.getLat(), marker.getLon());
    }

    public void setCenter(final double lat, final double lng) {
        indoorMaps.post(new Runnable() {
            @Override
            public void run() {
                indoorMaps.loadUrl("javascript:setCenter('" + lat + "','" + lng + "');");
            }
        });

    }

    public void goToAnimate(Marker marker) {
        goToAnimate(marker.getLat(), marker.getLon(), ANIMATION.PAN);
    }

    public void goToAnimate(Marker marker, ANIMATION animation) {
        goToAnimate(marker.getLat(), marker.getLon(), animation);
    }

    public void goToAnimate(final double lat, final double lng, final ANIMATION animation) {
        indoorMaps.post(new Runnable() {
            @Override
            public void run() {
                indoorMaps.loadUrl("javascript:goToAnimate('" + lat + "','" + lng + "','" + animation.value() + "');");
            }
        });

    }

    public void init(String path, ZOOM zoom) {
        Log.d(TAG, "zoom: " + zoom.value());
        init(path, zoom.value());
    }

    public void init(String path, int zoomLevel) {
        this.zoom = ZOOM.fromInt(zoomLevel);
        indoorMaps.loadUrl("javascript:init('" + path + "','" + zoomLevel + "');");
    }

    private void changeBackgroundColor(String color) {
        String colors = color.substring(3, color.length()).toUpperCase();
        indoorMaps.loadUrl("javascript:changeBackgroundColor('#" + colors + "');");
    }

    public void setDebug(boolean debug) {
        DEBUG = debug;
        indoorMaps.loadUrl("javascript:setDebug(" + debug + ");");
    }


    public class ChromeClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            //  Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            //    return super.onConsoleMessage(consoleMessage);
            if (DEBUG)
                Log.d("IndorMaps", consoleMessage.message());
            return true;
        }
    }

    public class WebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            indoorViewListener.onMapViewInizializate();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            // TODO Auto-generated method stub
            view.loadUrl(request.toString());
            return true;
        }
    }
}
