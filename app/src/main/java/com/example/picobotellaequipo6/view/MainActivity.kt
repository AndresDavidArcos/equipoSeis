package com.example.picobotellaequipo6.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.ActivityMainBinding
import com.example.picobotellaequipo6.view.fragment.home

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val SPLASH_DISPLAY_TIME: Long = 5000 // 5 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el temporizador para reemplazar SplashFragment con HomeFragment después de SPLASH_DISPLAY_TIME
        Handler(Looper.getMainLooper()).postDelayed({
            if (supportFragmentManager.findFragmentByTag(home::class.java.simpleName) == null) {
                showHomeFragment()
            }
        }, SPLASH_DISPLAY_TIME)
    }

    private fun showHomeFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val homeFragment = home()

        // Reemplaza R.id.parent_constraint_layout con el ID real de tu ConstraintLayout en fragment_home.xml
        transaction.replace(R.id.parent_constraint_layout, homeFragment, home::class.java.simpleName)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}