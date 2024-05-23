package com.sizran.railvaycompany
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sizran.railvaycompany.ui.FragmentHistory
import com.sizran.railvaycompany.ui.FragmentStations
import com.sizran.railvaycompany.ui.FragmentTrains

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val selectedFragment: Fragment? = when (item.itemId) {
                R.id.nav_trains -> FragmentTrains()
                R.id.nav_stations -> FragmentStations()
                R.id.nav_history -> FragmentHistory()
                else -> null
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit()
            }
            true
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FragmentTrains())
            .commit()
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.nav_stations // Set default selection
        }
    }
}
