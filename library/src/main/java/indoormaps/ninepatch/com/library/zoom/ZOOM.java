package indoormaps.ninepatch.com.library.zoom;

/**
 * Created by luca on 08/09/16.
 */
public enum ZOOM {

    LEVEL1(1),//max
    LEVEL2(2),
    LEVEL3(3),
    LEVEL4(4),
    LEVEL5(5),
    LEVEL6(6),
    LEVEL7(7),
    LEVEL8(8),
    LEVEL9(9),
    LEVEL10(10);//min

    private int value;

    ZOOM(int newValue) {
        value = newValue;
    }

    public static ZOOM fromInt(int zoomLevel) {
        switch (zoomLevel) {
            case 1:
                return LEVEL1;
            case 2:
                return LEVEL2;
            case 3:
                return LEVEL3;
            case 4:
                return LEVEL4;
            case 5:
                return LEVEL5;
            case 6:
                return LEVEL6;
            case 7:
                return LEVEL7;
            case 8:
                return LEVEL8;
            case 9:
                return LEVEL9;
            case 10:
                return LEVEL10;
            default:
                return LEVEL1;

        }

    }

    public int value() {
        return value;
    }
}
