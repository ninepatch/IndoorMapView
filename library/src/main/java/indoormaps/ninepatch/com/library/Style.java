package indoormaps.ninepatch.com.library;

import android.content.Context;
import androidx.annotation.ColorRes;

/**
 * Created by luca on 07/09/16.
 */
public class Style {

    private boolean showLabel = true;
    private int labelPx = 18;
    private String labelColor = "#000";
    private Context context;

    public Style(Context context) {
        this.context = context;
    }

    public boolean isShowLabel() {
        return showLabel;
    }

    public void setShowLabel(boolean showLabel) {
        this.showLabel = showLabel;
    }

    public double getLabelPx() {
        return labelPx;
    }

    public void setLabelPx(int labelPx) {
        this.labelPx = labelPx;
    }

    public String getLabelColor() {
        return labelColor;
    }

    @SuppressWarnings("ResourceType")
    public void setLabelColor(@ColorRes int labelColor) {
        this.labelColor = context.getResources().getString(labelColor);
    }

    public void setLabelColorHex(String labelColor) {
        this.labelColor = labelColor;
    }
}
