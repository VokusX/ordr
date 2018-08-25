package net.ordrapp.ramen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import net.ordrapp.ramen.ui.home.MainViewModel
import net.ordrapp.ramen.ui.OnboardingActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val fusedLocationClient: FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private var userLocation: Location? = null
    private lateinit var googleMap: GoogleMap

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        // if (userHasNotSignedIn) {
        startActivity(Intent(this, OnboardingActivity::class.java))
        // }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                userLocation = location!!
                centerToLocation()
            }
        }

        viewModel.restaurantsData.observe(this, Observer {
            // Display "it" on the map.
        })

        val map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        map.getMapAsync { googleMap = it }

        map.getMapAsync {
            googleMap = it
            centerToLocation()
        }

    }

    private fun centerToLocation() {
        userLocation ?: return
        if (::googleMap.isInitialized) {
            val lastLocation = LatLng(userLocation!!.latitude, userLocation!!.longitude)
            googleMap.addMarker(MarkerOptions().position(lastLocation))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 16.0f))
        }
    }

}
