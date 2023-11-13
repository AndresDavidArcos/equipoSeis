package com.example.picobotellaequipo6.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.FragmentChallengesBinding
import com.example.picobotellaequipo6.databinding.FragmentHomeBinding
class challenges : Fragment() {
    private lateinit var binding: FragmentChallengesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChallengesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.contentToolbar.backToolbar.setOnClickListener{ findNavController().popBackStack() }
    }

}