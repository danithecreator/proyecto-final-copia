function crearMapa(lugares) {
    console.log(lugares)
    mapboxgl.accessToken = 'pk.eyJ1IjoiZGFuaXRoZWNyZWF0b3IiLCJhIjoiY2tyMnZkbzd4MXJ1cDJ1bzdlNHpmdnNxYyJ9.789aY-Czm5oJfDpOtj-8eA';
    let map = new mapboxgl.Map({
        container: "mapaHome",
        style: 'mapbox://styles/mapbox/streets-v11'
    });


    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },

        trackUserLocation: true,

    }))

    map.on("load", function () {
        //  ubicacionActual(map);
        ubicarLugares(lugares, map);
    })

}

function ubicacionActual(map) {
    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {
            map.flyTo({
                center: [position.coords.longitude, position.coords.latitude],
                zoom: 15

            })
        })
    }
}

function ubicarLugares(lugares, map) {

    let bounds = new mapboxgl.LngLatBounds()


    for (let l of lugares) {
        let el = document.createElement('div');
        if (l.tipo === 'Bar') {
            el.className = 'markerBar'
        } else {
            if (l.tipo === 'Cafe') {
                el.className = 'markerCafe'
            } else {
                if (l.tipo === 'Hotel') {
                    el.className = 'markerHotel'
                } else {
                    el.className = 'markerRestaurante'
                }
            }
        }
        new mapboxgl.Marker(el).setLngLat([l.lng, l.lat]).setPopup(new mapboxgl.Popup({
            closeButton: false,
            closeOnClick: true
        }).setHTML(
            "<a href='http://localhost:8080/detalleLugar.xhtml?lugar="
            + l.id + "'>" +
            '<h4>' + l.nombre + '</h4>' +
            '<p>' + l.tipo + '</p>' +
            '<p> ' + l.descripcion + '</p>' +
            "</a>"
        )).addTo(map)

        bounds.extend([l.lng, l.lat])
    }
    map.fitBounds(bounds,
        {
            padding: 100
        }
    )
}





