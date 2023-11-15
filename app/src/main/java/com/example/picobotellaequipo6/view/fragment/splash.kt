package com.example.picobotellaequipo6.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.FragmentSplashBinding
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController

class splash : Fragment()  {
    private lateinit var binding: FragmentSplashBinding

    private val SPLASH_DISPLAY_TIME: Long = 5000 // 5 seconds

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gifView = binding.gifImageView
        Glide.with(this).asGif().load(R.drawable.splash_gif).into(gifView)

        Handler(Looper.getMainLooper()).postDelayed({

            findNavController().navigate(R.id.action_splash_to_home2)

        }, SPLASH_DISPLAY_TIME)

    }
}