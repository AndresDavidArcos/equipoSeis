package com.example.picobotellaequipo6.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.FragmentChallengesBinding
import com.example.picobotellaequipo6.databinding.FragmentHomeBinding
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.view.adapter.ChallengesAdapter
import com.example.picobotellaequipo6.viewmodel.ChallengesViewModel

class challenges : Fragment() {
    private lateinit var binding: FragmentChallengesBinding
    private val challengesViewModel: ChallengesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChallengesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contentToolbar.backToolbar.setOnClickListener{ findNavController().popBackStack() }
        controladores()
        observerViewModel()
    }

    private fun observerViewModel() {
        observerListInventory()
    }
    private fun observerListInventory() {
        challengesViewModel.getListInvetory()
        challengesViewModel.listChallenge.observe(viewLifecycleOwner){ lista->
            val recycler = binding.recyclerview
            val layoutManager = LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val adapter = ChallengesAdapter(lista)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    }

    private fun controladores() {
//        recycler()
    }
    fun recycler(){
//        val listaR =  mutableListOf(
//            Challenges("1"),
//            Challenges("2"),
//            Challenges("3"),
//            Challenges("4"),
//            Challenges("5"),
//            Challenges("6"),
//            Challenges("7"),
//            Challenges("8"),
//            Challenges("9"),
//            Challenges("10")
//        )
//        val recycler = binding.recyclerview
//        recycler.layoutManager = LinearLayoutManager(context)
//        val adapter = ChallengesAdapter(listaR)
//        recycler.adapter = adapter
//        adapter.notifyDataSetChanged()
    }

}