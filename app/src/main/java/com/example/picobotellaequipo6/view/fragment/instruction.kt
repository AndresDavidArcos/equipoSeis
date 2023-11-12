package com.example.picobotellaequipo6.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.picobotellaequipo6.databinding.FragmentInstructionBinding
import com.bumptech.glide.Glide

class Instruction : Fragment() {

    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstructionBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    val gifView = binding.ganadorGifInstruction
    //Glide.with(this).asGif().load(com.example.picobotellaequipo6.R.drawable.ganador_gif).into(gifView)

    //val gifView = binding.gifImageView
    //Glide.with(this).asGif().load(com.example.picobotellaequipo6.R.drawable.splash_gif).into(gifView)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
    }

    private fun controladores() {
        //binding.contentToolbar.toolbar.back_toolbar.setOnClickListener { findNavController().popBackStack() }
        binding.contentToolbar.backToolbar.setOnClickListener{ findNavController().popBackStack() }
    }

}