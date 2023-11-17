package com.example.picobotellaequipo6.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picobotellaequipo6.databinding.FragmentChallengesBinding
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.view.adapter.ChallengesAdapter
import com.example.picobotellaequipo6.view.dialogos.AddChallengeDialog
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
        binding.contentToolbar.backToolbar.setOnClickListener { findNavController().popBackStack() }
        controladores()
        observerViewModel()
    }

    private fun observerViewModel() {
        observerListInventory()
    }

    private fun observerListInventory() {
        challengesViewModel.getListInvetory()
        challengesViewModel.listInventory.observe(viewLifecycleOwner) { lista ->
            val recycler = binding.recyclerview
            val layoutManager = LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val reversedList: MutableList<Challenges> = lista.toMutableList().asReversed()
            val adapter = ChallengesAdapter(reversedList)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()


//            Toast.makeText(requireContext(),"aymamamia",Toast.LENGTH_LONG).show()
        }
//        Toast.makeText(requireContext(),"ObserverListInvenroty",Toast.LENGTH_LONG).show()


    }

    private fun controladores() {
//        recycler()
        observerViewModel()
        alertDia()

    }

    private fun alertDia() {
        val addChallengeDialog = AddChallengeDialog(challengesViewModel)
        binding.fbagregar.setOnClickListener {
            addChallengeDialog.showDialog(binding.root.context, this::observerListInventory)

        }

    }
}