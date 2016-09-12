package indoormaps.ninepatch.com.library;

import android.content.Context;

import indoormaps.ninepatch.com.library.zoom.ZOOM;

/**
 * Created by luca on 05/09/16.
 */
public class Marker {

    private int id;
    private String name;
    private double lat;
    private double lon;
    private String imageLink;
    private Context context;
    private Style style;


    public Marker(Context context) {
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Style getStyle() {
        this.style = style == null ? new Style(context) : style;
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
