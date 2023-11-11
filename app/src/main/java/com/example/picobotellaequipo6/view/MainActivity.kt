package com.example.picobotellaequipo6.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.ActivityMainBinding
import com.example.picobotellaequipo6.view.fragment.SplashWindow
import com.example.picobotellaequipo6.view.fragment.home

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val SPLASH_DISPLAY_TIME: Long = 5000 // 5 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mostrar el fragmento de splash al inicio
        showFragment(SplashWindow())

        // Configurar el temporizador para reemplazar SplashFragment con HomeFragment después de SPLASH_DISPLAY_TIME
        Handler(Looper.getMainLooper()).postDelayed({
            showFragment(home())
        }, SPLASH_DISPLAY_TIME)
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}