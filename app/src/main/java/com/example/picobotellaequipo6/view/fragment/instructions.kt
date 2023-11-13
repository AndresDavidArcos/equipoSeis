package com.example.picobotellaequipo6.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.FragmentHomeBinding
import com.example.picobotellaequipo6.databinding.FragmentInstructionsBinding

class instructions : Fragment() {
    private lateinit var binding: FragmentInstructionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInstructionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).asGif().load(R.drawable.ganador_gif).into(binding.ganarGif)
        binding.contentToolbar.backToolbar.setOnClickListener{ findNavController().popBackStack() }
    }

}