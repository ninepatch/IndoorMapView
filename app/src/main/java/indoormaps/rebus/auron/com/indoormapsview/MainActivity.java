package indoormaps.rebus.auron.com.indoormapsview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import indoormaps.rebus.auron.com.library.IndoorMapsView;
import indoormaps.rebus.auron.com.library.Marker;
import indoormaps.rebus.auron.com.library.Style;
import indoormaps.rebus.auron.com.library.callback.OnMapViewInizializate;
import indoormaps.rebus.auron.com.library.callback.OnMarkerTapListener;
import indoormaps.rebus.auron.com.library.callback.OnTapListener;

public class MainActivity extends AppCompatActivity {

    private IndoorMapsView indoorMapsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        indoorMapsView = (IndoorMapsView) findViewById(R.id.indorMaps);
        indoorMapsView.getIndoorViewListener().setOnMapViewInizializate(new OnMapViewInizializate() {
            @Override
            public void onMapLoading() {
                //show progress
            }

            @Override
            public void onMapinizializate() {
                //dismiss progress
                indoorMapsView.setDebug(true);//enable loggin
                Marker marker = new Marker(MainActivity.this);
                marker.setId(1);
                marker.setLat(0.015874738);
                marker.setLon(0.019710949);
                marker.setName(getString(R.string.marker_name));
                marker.setImageLink("pointer.png");//from assets
                Style style = new Style(MainActivity.this);
                style.setLabelColor(R.color.colorAccent);
                style.setLabelPx(22);
                style.setShowLabel(true);
                marker.setStyle(style);

                Marker marker2 = new Marker(MainActivity.this);
                marker2.setId(2);
            //    marker2.setImageLink("http://iconshow.me/media/images/Mixed/small-n-flat-icon/png/256/map-marker.png");
                marker2.setImageLink("marker1.png");
                marker2.setName("Test 2");
                marker2.setLat(0.017874738);
                marker2.setLon(0.020710949);
                //left default style
                indoorMapsView.init("mappa2.png"); //from assets
                indoorMapsView.setBackgroundColorRes(R.color.colorPrimary);
                indoorMapsView.addMarker(marker);
                indoorMapsView.addMarker(marker2);
                indoorMapsView.addMarker(marker);
            }

        });

        indoorMapsView.getIndoorViewListener().setOnMarkerTapListener(new OnMarkerTapListener() {
            @Override
            public void onMarkerTap(Marker marker) {
                indoorMapsView.removeMarker(marker);
            }
        });

        indoorMapsView.getIndoorViewListener().setOnTapListener(new OnTapListener() {
            @Override
            public void onTap(double lat, double lon) {
                Log.d("MainActivity", "Map click: " + lat);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
