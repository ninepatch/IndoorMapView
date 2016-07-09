package indoormaps.rebus.auron.com.indoormapsview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import indoormaps.rebus.auron.com.library.IndoorMapsView;

public class MainActivity extends AppCompatActivity {

    private IndoorMapsView indoorMapsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        indoorMapsView = (IndoorMapsView) findViewById(R.id.indorMaps);

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

      //  myView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }
}
