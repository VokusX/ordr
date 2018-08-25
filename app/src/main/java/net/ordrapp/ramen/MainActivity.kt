package net.ordrapp.ramen

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import net.ordrapp.ramen.ui.home.MainViewModel
import net.ordrapp.ramen.ui.home.MapsAdapter
import net.ordrapp.ramen.ui.home.RestaurantAdapter
import net.ordrapp.ramen.utils.dp
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val fusedLocationClient: FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private lateinit var googleMap: GoogleMap

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private val mapAdapter: MapsAdapter by lazy { MapsAdapter(this@MainActivity, emptyList()) }

    private val bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout> by lazy { BottomSheetBehavior.from(bottomSheet) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

        // if (userHasNotSignedIn) {
        // startActivity(Intent(this, OnboardingActivity::class.java))
        // }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                viewModel.updateUserLocation(location)
            }
        }

        resultsList.adapter = RestaurantAdapter(this, viewModel.userLocation.value)

        viewModel.restaurantsData.observe(this, Observer {
            it ?: return@Observer
            mapAdapter.restaurants = it.restaurants
            it.diffResult.dispatchUpdatesTo(mapAdapter)

            progressBar.visibility = View.GONE
            progressText.visibility = View.GONE

            val resultsAdapter = resultsList.adapter as RestaurantAdapter
            resultsAdapter.values = it.restaurants
            it.diffResult.dispatchUpdatesTo(resultsAdapter)

            bottomSheetBehavior.peekHeight = dp(116)
        })

        viewModel.userLocation.observe(this, Observer {
            centerToLocation(it)

            val resultsAdapter = resultsList.adapter as RestaurantAdapter
            resultsAdapter.userLastLocation = it
            resultsAdapter.notifyDataSetChanged()

            if (::googleMap.isInitialized) {
                val visibleRegion = googleMap.projection.visibleRegion
                viewModel.getNearbyStops(visibleRegion)
            }
        })

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {
            googleMap = it
            centerToLocation(viewModel.userLocation.value)
            mapAdapter.attach(mapView, googleMap)

            // Display the user's current location on the map.
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                googleMap.isMyLocationEnabled = true
            }

            googleMap.setOnCameraIdleListener {
                val visibleRegion = googleMap.projection.visibleRegion
                viewModel.getNearbyStops(visibleRegion)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    private fun centerToLocation(location: Location?) {
        location ?: return
        if (::googleMap.isInitialized) {
            val lastLocation = LatLng(location.latitude, location.longitude)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 16.0f))
        }
    }

}