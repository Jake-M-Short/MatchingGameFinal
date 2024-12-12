package com.example.matchinggame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val navController = findNavController(R.id.nav_host_fragment)
            setupActionBarWithNavController(navController)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            // Log the error for debugging
            println("Error setting up NavController: ${e.message}")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
