package indoormaps.ninepatch.com.library.animate;

/**
 * Created by luca on 09/09/16.
 */
public enum ANIMATION {

    FLY("FLY"), SPIRAL("SPIRAL"), PAN("PAN");
    private String pan;

    ANIMATION(String pan) {
        this.pan = pan;
    }

    public String value() {
        return pan;
    }
}
