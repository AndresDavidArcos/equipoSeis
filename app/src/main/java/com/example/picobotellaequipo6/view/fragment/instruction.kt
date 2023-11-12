package com.example.picobotellaequipo6.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.picobotellaequipo6.databinding.FragmentInstructionBinding
import com.bumptech.glide.Glide
import com.example.picobotellaequipo6.R

class Instruction : Fragment() {

    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inicializa la propiedad binding antes de usarla
        binding = FragmentInstructionBinding.inflate(inflater)
        val gifView = binding.ganarGif
        Glide.with(this).asGif().load(R.drawable.ganador_gif).into(gifView)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
    }

    private fun controladores() {
        // Ahora puedes acceder a la propiedad binding sin problemas
        binding.contentToolbar.backToolbar.setOnClickListener{ findNavController().popBackStack() }
    }
}
