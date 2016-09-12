package indoormaps.ninepatch.com.library;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import indoormaps.ninepatch.com.library.callback.OnMapViewInizializate;
import indoormaps.ninepatch.com.library.callback.OnMarkerTapListener;
import indoormaps.ninepatch.com.library.callback.OnTapListener;

/**
 * Created by luca on 6/22/16.
 */
public class IndoorViewListener {

    private Context mContext;
    private OnMarkerTapListener onMarkerTapListener;
    private OnTapListener onTapListener;
    private OnMapViewInizializate onMapViewInizializate;

    public IndoorViewListener(Context c) {
        mContext = c;
    }

    public void setOnMarkerTapListener(OnMarkerTapListener onMarkerTapListener) {
        this.onMarkerTapListener = onMarkerTapListener;
    }

    public void setOnTapListener(OnTapListener onTapListener) {
        this.onTapListener = onTapListener;
    }

    public void setOnMapViewInizializate(OnMapViewInizializate onMapViewInizializate) {
        this.onMapViewInizializate = onMapViewInizializate;
    }

    @JavascriptInterface
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void removeMarkerCallback(int markerId) {
        for (Marker marker : IndoorMapsView.markers) {
            if (marker.getId() == markerId) {
                IndoorMapsView.markers.remove(marker);
                break;
            }
        }
    }

    @JavascriptInterface
    public void onMarkerClick(int id) {
        Log.d("onMarkerClick", "onMarkerClick: " + id);
        if (onMarkerTapListener != null) {
            if (!IndoorMapsView.markers.isEmpty()) {
                Log.d("onMarkerClick", "not empty");
                for (Marker marker : IndoorMapsView.markers) {
                    Log.d("onMarkerClick", "maker searche : "+marker.getId());
                    if (marker.getId() == id) {
                        onMarkerTapListener.onMarkerTap(marker);
                        break;
                    }
                }
            }else{
                Log.d("onMarkerClick", "maker LIST EMPTY");
            }
        }
    }

    @JavascriptInterface
    public void onMapClick(double lat, double lon) {
        if (onTapListener != null)
            onTapListener.onTap(lat, lon);
    }


    public void onMapViewInizializate() {
        if (onMapViewInizializate != null) {
            onMapViewInizializate.onMapinizializate();
        }
    }

    public void onMapLoading() {
        if (onMapViewInizializate != null) {
            onMapViewInizializate.onMapLoading();
        }
    }
}
