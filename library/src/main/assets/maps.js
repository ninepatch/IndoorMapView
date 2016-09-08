var extent = [0, 0, 3508, 2480];
var map="";
var startx = 0;
var starty = 0;
var vectorSource;
var vectorLayer;
var debug=false;
var projection = new ol.proj.Projection({
    code: 'xkcd-image',
    units: 'pixels',
    extent: extent
});

var arrayFeature = [];
vectorSource = new ol.source.Vector({
    features: arrayFeature,
});

vectorLayer = new ol.layer.Vector({
    source: vectorSource,
});

function addMarker(markerLat,markerLon,markerName,iconPath,markerId,showLabel,labelPx,labelColor){

var label="";
 var marker = new ol.Feature({
            geometry: new ol.geom.Point(ol.proj.transform([markerLat,markerLon],
                'EPSG:4326', 'EPSG:3857')),
            id: markerId,
            name: markerName,
            lat:markerLat,
            lon:markerLon,
            population: 4000,
            rainfall: 500
        });

if(showLabel) {
label=markerName;
}

var fontBuild= ""+labelPx+"px Montserrat";
printConsole(fontBuild);
          var style = new ol.style.Style({

                    stroke: new ol.style.Stroke({
                        width: 3,
                        color: [255, 0, 0, 1],
                    }),
                    text: new ol.style.Text({
                        text: label,
                        font: fontBuild,
                        offsetY: -40,
                        fill: new ol.style.Fill({
                            color: labelColor
                        })
                    }),
                    image: new ol.style.Icon({
                        src: iconPath
                    })
                });
                marker.setStyle(style);
                vectorSource.addFeature(marker);
                printConsole("add Marker, lat: "+markerLat+" lng: "+markerLon+" name: "+markerName+" iconPath: "+iconPath+" id: "+markerId);
}

function removeMarker(markerId){
printConsole("marker processing remove ,markerId: "+markerId);
  var features = vectorSource.getFeatures();
   if (features == null && features.length <= 0) {
   Android.removeMarker(-1);
   printConsole("marker not removed");
   return;
   }
    for (marker in features) {

         var properties = features[marker].getProperties();

 printConsole("marker search :"+properties.id);
    if(properties.id ==markerId){
    vectorSource.removeFeature(features[marker]);
    printConsole("marker removed");
    Android.removeMarkerCallback(markerId);
    break;
}
    }

}

function init(backgroungPath) {

printConsole("init call, mapImage path: "+backgroungPath);

    map = new ol.Map({
        layers: [
            new ol.layer.Image({
                source: new ol.source.ImageStatic({
                    url: backgroungPath,
                    projection: projection,
                    imageExtent: extent,
                })
            }),
            new ol.layer.Tile({
                source: new ol.source.TileWMS({
                    attributions: [new ol.Attribution({
                        html: "Powered by auron and bussa"
                    })]
                })
            })
        ],


        target: 'map',
        view: new ol.View({
            projection: projection,
            center: ol.extent.getCenter(extent),
            zoom: 1,
        })
    });

/*
         var deviceOrientation = new ol.DeviceOrientation({
              tracking: true
            });
*/
    //        deviceOrientation.on('change:heading', onChangeHeading);





    // Android.showToast("map start");

    map.addLayer(vectorLayer);

    map.on("click", function (evt) {
    evt.preventDefault();



        var lonlat = ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326');




        var tmp_feature = map.forEachFeatureAtPixel(evt.pixel,
            function (feature, layer) {
                return feature;
            });

        // click su feature
        if (tmp_feature) {
        printConsole("click on marker, id: "+tmp_feature.get('id'));
        Android.onMarkerClick(parseInt(tmp_feature.get('id')));
        return;
        }

        printConsole("click on map, lat: "+lonlat[0]+" lng: "+lonlat[1]);
        Android.onMapClick(lonlat[0],lonlat[1]);


    });

/*
  map.getViewport().addEventListener('touchstart', function(e){
      var touchobj = e.changedTouches[0]; // reference first touch point (ie: first finger)
      startx = parseInt(touchobj.clientX); // get x position of touch point relative to left edge of browser
      e.preventDefault();
  }, false)

  map.getViewport().addEventListener('touchend', function(e){
      var touchobj = e.changedTouches[0]; // reference first touch point (ie: first finger)
      // Calculate if there was "significant" movement of the finger
      var movementX = Math.abs(startx-touchobj.clientX);
      var movementY = Math.abs(starty-touchobj.clientY);
      if(movementX < 5 || movementY < 5)
          yourMobileTouchFunction(touchobj,e); // Less than 5 pixels => a touch click
      e.preventDefault();
  },false);


*/
}


/*
                 function onChangeHeading(event) {
                  var heading = event.target.getHeading();
                  var el = document.getElementById('location');
                  el.style['-webkit-transform'] = 'rotate('+heading+'rad)';
                  el.style['transform'] = 'rotate('+heading+'rad)';
                  el.style['transform'] = 'rotate('+heading+'rad)';
                }

 function yourMobileTouchFunction(touchobj,evt){

    var lonlat = ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326');
         console.log("coord",lonlat);

    Android.showToast("map click: "+lonlat);

 }
 */



 function setDebug(boolean){
 debug=boolean;
 }

 function changeBackgroundColor(color){
 printConsole("background color chanche to: "+color);
 document.getElementById("map").style.backgroundColor = color;
 }

function printConsole(message){

if(debug){

console.log(message);

}

}