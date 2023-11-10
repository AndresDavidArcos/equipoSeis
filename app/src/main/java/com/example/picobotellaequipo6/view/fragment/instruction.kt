package com.example.picobotellaequipo6.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.ActivityMainBinding

class instruction : Fragment() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupToolbar()

    }

    private fun setupToolbar() {
        TODO("Not yet implemented")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instruction, container, false)
    }

}