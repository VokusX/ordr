package net.ordrapp.ramen.ui.home

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import net.ordrapp.ramen.data.Restaurant
import nz.co.trademe.mapme.annotations.AnnotationFactory
import nz.co.trademe.mapme.annotations.MapAnnotation
import nz.co.trademe.mapme.annotations.MarkerAnnotation
import nz.co.trademe.mapme.googlemaps.GoogleMapMeAdapter

class MapsAdapter(context: Context, var restaurants: List<Restaurant>) : GoogleMapMeAdapter(context) {

    override fun onCreateAnnotation(factory: AnnotationFactory<GoogleMap>, position: Int, annotationType: Int): MapAnnotation {
        val item = restaurants[position]
        val restaurantLocation : nz.co.trademe.mapme.LatLng = nz.co.trademe.mapme.LatLng(item.location.lat, item.location.lon)
        return factory.createMarker(restaurantLocation, null, item.name)
    }

    override fun onBindAnnotation(annotation: MapAnnotation, position: Int, payload: Any?) {
        if (annotation is MarkerAnnotation) {
            val item = restaurants[position]
            annotation.title = item.name
        }
    }

    override fun getItemCount(): Int = restaurants.size
}