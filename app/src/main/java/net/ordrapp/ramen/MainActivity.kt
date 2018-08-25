package net.ordrapp.ramen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.SupportMapFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        map.getMapAsync {

        }
    }

}
