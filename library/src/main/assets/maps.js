var puntatori = [{
    lat: 0.01623104774679782,
    long: 0.00436225936948631,
    name: 'Prova',
    numero: '1'
} ];


var extent = [0, 0, 3508, 2480];
var map;
  var startx = 0;
  var starty = 0;


var projection = new ol.proj.Projection({
    code: 'xkcd-image',
    units: 'pixels',
    extent: extent
});

var arrayFeature = [];

function addMarker(markerLat,markerLon,markerName,iconPath){

 var marker = new ol.Feature({
            geometry: new ol.geom.Point(ol.proj.transform([markerLat,markerLon],
                'EPSG:4326', 'EPSG:3857')),
            name: markerName,
            population: 4000,
            rainfall: 500
        });


          var style = new ol.style.Style({

                    stroke: new ol.style.Stroke({
                        width: 3,
                        color: [255, 0, 0, 1],
                    }),
                    text: new ol.style.Text({
                        text: markerName,
                        font: '18px Montserrat',
                        offsetY: -40,
                        fill: new ol.style.Fill({
                            color: '#a22e44'
                        })
                    }),
                    image: new ol.style.Icon({
                        src: iconPath
                    })
                });
                marker.setStyle(style);
                arrayFeature.push(marker);

}


var vectorSource;
var vectorLayer;



function init(backgroungPath) {

vectorSource = new ol.source.Vector({
    features: arrayFeature,
});


vectorLayer = new ol.layer.Vector({
    source: vectorSource,
});

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


         var deviceOrientation = new ol.DeviceOrientation({
              tracking: true
            });

            deviceOrientation.on('change:heading', onChangeHeading);



console.log("finish");

//alert("ciao sono un alert");

    Android.showToast("map start");

    map.addLayer(vectorLayer);

    map.on("click", function (evt) {
    console.log("CLIK MAPS");
        evt.preventDefault();

        var lonlat = ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326');
        console.log(lonlat);
/*

        var tmp_feature = map.forEachFeatureAtPixel(evt.pixel,
            function (feature, layer) {
                return feature;
            });
        // click su feature
        if (tmp_feature) {



        }
  */  });


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





}
                 function onChangeHeading(event) {
                  var heading = event.target.getHeading();
                  var el = document.getElementById('location');
                  el.style['-webkit-transform'] = 'rotate('+heading+'rad)';
                  el.style['transform'] = 'rotate('+heading+'rad)';
                }

 function yourMobileTouchFunction(touchobj,evt){

    var lonlat = ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326');
         console.log("coord",lonlat);

    Android.showToast("map click: "+lonlat);

 }




