# IndoorMapView


[![Platform (Android)](https://img.shields.io/badge/platform-Android-blue.svg?style=flat-square)](http://www.android.com)
[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/ninepatch/IndoorMapView/blob/master/License)
[![GitHub stars](https://img.shields.io/github/stars/ninepatch/IndoorMapView.svg)](https://github.com/ninepatch/IndoorMapView/stargazers)

### BETA Version

Android indoor map work in progress...

## Installation
Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency
```
dependencies {
   	        implementation 'com.github.ninepatch:IndoorMapView:Tag'
}
```

### Requirements

The library requires Android **API Level 14+**.



### How to use

#### activity_main.xml
```java
      <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:fitsSystemWindows="true"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <indoormaps.ninepatch.com.library.IndoorMapsView
        android:id="@+id/indorMaps"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```

#### MainActivity.class

```java
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
          
            }

            @Override
            public void onMapinizializate() {
                 
                 indoorMapsView.init("mappa2.png", ZOOM.LEVEL4); //image from assets or put link
                 indoorMapsView.setBackgroundColorRes(R.color.colorPrimary);
                      
                       }

        });

   
    }
}

```

#### Add marker

```java
                Marker marker = new Marker(MainActivity.this);
                marker.setId(1);
                marker.setLat(36.8271);
                marker.setLon(32.9731);
                marker.setName(getString(R.string.marker_name));
                marker.setImageLink("pointer.png");//from assets or put link
                
                //set Marker Style
                Style style = new Style(MainActivity.this);
                style.setLabelColor(R.color.colorAccent);
                style.setLabelPx(22);
                style.setShowLabel(true);
                marker.setStyle(style);
                
                indoorMapsView.addMarker(marker);


```


#### remove marker

```java

                  indoorMapsView.removeMarker(marker.getId());
                  // OR
                  indoorMapsView.removeMarker(marker);

```


#### Listner

```java


  indoorMapsView.getIndoorViewListener().setOnMarkerTapListener(new OnMarkerTapListener() {
            @Override
            public void onMarkerTap(Marker marker) {
         
            }
        });

        indoorMapsView.getIndoorViewListener().setOnTapListener(new OnTapListener() {
            @Override
            public void onTap(double lat, double lon) {
                
            }
        });

```
#### Center Map

```java

    indoorMapsView.setCenter(lat, lng);

```


#### Animation


ANIMATION type: PAN, FLY ,SPIRAL 

center map to point or marker whit animation

```java

  indoorMapsView.goToAnimate(marker, ANIMATION.PAN);

```
