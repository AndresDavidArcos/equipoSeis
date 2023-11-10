package com.example.picobotellaequipo6.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.picobotellaequipo6.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.picobotellaequipo6.R // Asegúrate de reemplazar con tu paquete correcto
import com.example.picobotellaequipo6.view.fragment.SplashFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mostrar el fragmento de splash al inicio
        showFragment(SplashFragment())
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}