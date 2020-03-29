package indoormaps.ninepatch.com.indoormapsview;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import indoormaps.ninepatch.com.library.IndoorMapsView;
import indoormaps.ninepatch.com.library.Marker;
import indoormaps.ninepatch.com.library.Style;
import indoormaps.ninepatch.com.library.animate.ANIMATION;
import indoormaps.ninepatch.com.library.callback.OnMapViewInizializate;
import indoormaps.ninepatch.com.library.callback.OnMarkerTapListener;
import indoormaps.ninepatch.com.library.callback.OnTapListener;
import indoormaps.ninepatch.com.library.zoom.ZOOM;

public class MainActivity extends AppCompatActivity {

    private IndoorMapsView indoorMapsView;
    private Marker marker, marker2;

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
                marker = new Marker(MainActivity.this);
                marker.setId(1);
                marker.setLat(36.8271);
                marker.setLon(32.9731);
                marker.setName(getString(R.string.marker_name));
                marker.setImageLink("pointer.png");//from assets
                Style style = new Style(MainActivity.this);
                style.setLabelColor(R.color.colorAccent);
                style.setLabelPx(22);
                style.setShowLabel(true);
                marker.setStyle(style);

                marker2 = new Marker(MainActivity.this);
                marker2.setId(2);
                //marker2.setImageLink("http://iconshow.me/media/images/Mixed/small-n-flat-icon/png/256/map-marker.png");
                marker2.setImageLink("marker1.png");
                marker2.setName("Test 2");
                marker2.setLat(3418.9296);
                marker2.setLon(2287.5654);

                //left default style
                indoorMapsView.init("mappa2.png", ZOOM.LEVEL4); //image from assets
                indoorMapsView.setBackgroundColorRes(R.color.colorPrimary);
                indoorMapsView.addMarker(marker);
                indoorMapsView.addMarker(marker2);
                //indoorMapsView.addMarker(marker);

                // indoorMapsView.setZoom(ZOOM.LEVEL4);
            }
        });

        indoorMapsView.getIndoorViewListener().setOnMarkerTapListener(new OnMarkerTapListener() {
            @Override
            public void onMarkerTap(Marker marker) {
                indoorMapsView.goToAnimate(marker, ANIMATION.FLY);
                // indoorMapsView.removeMarker(marker);
            }
        });

        indoorMapsView.getIndoorViewListener().setOnTapListener(new OnTapListener() {
            @Override
            public void onTap(double lat, double lon) {
                Log.d("MainActivity", "Map click: " + lat + "lng : " + lon);
                indoorMapsView.setCenter(lat, lon);
            }
        });

        indoorMapsView.getIndoorViewListener().setOnMarkerTapListener(new OnMarkerTapListener() {
            @Override
            public void onMarkerTap(final Marker currentMarker) {

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(MainActivity.this);
                }
                builder.setTitle("Delete marker")
                        .setMessage("Are you sure you want to delete this marker?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                indoorMapsView.removeMarker(currentMarker.getId());
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setCancelable(true)
                        .show();
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
