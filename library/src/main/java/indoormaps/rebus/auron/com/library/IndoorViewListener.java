package indoormaps.rebus.auron.com.library;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by luca on 6/22/16.
 */
public class IndoorViewListener {
    Context mContext;


   public  IndoorViewListener(Context c) {
        mContext = c;
    }


    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}
