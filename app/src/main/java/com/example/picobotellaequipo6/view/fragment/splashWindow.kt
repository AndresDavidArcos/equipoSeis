package com.example.picobotellaequipo6.view.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.view.fragment.home

class SplashWindow : Fragment() {

    private val SPLASH_DISPLAY_TIME: Long = 5000 // 5 seconds

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar la ventana para que sea de pantalla completa y sin barra de título
        activity?.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        // Ocultar la barra de navegación en dispositivos que la tengan
        val decorView: View = requireActivity().window.decorView
        val uiOptions =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = uiOptions

        // Configurar el temporizador para cerrar la actividad después de SPLASH_DISPLAY_TIME
        Handler().postDelayed({
            val intent = Intent(activity, com.example.picobotellaequipo6.view.fragment.home::class.java)
            startActivity(intent)
            activity?.finish() // Cerrar la actividad actual
        }, SPLASH_DISPLAY_TIME)
    }
}
