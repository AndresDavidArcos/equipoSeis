package com.example.picobotellaequipo6.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.picobotellaequipo6.databinding.FragmentEditChallengeBinding
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.viewmodel.ChallengesViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picobotellaequipo6.R

class EditChallenge : Fragment() {
    private lateinit var binding: FragmentEditChallengeBinding
    private val challengeViewModel: ChallengesViewModel by viewModels()
    private lateinit var receivedChallenge: Challenges

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditChallengeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
    }

    private fun controladores() {
        binding.btnSaveChallenge.setOnClickListener {
            updateChallenge()
        }
    }

    private fun dataInventory(){
        val receivedBundle = arguments
        receivedChallenge = receivedBundle?.getSerializable("dataChallenge") as Challenges
        binding.etChallengeName.setText(receivedChallenge.name)

    }

    private fun updateChallenge(){
        val name = binding.etChallengeName.text.toString()
        val inventory = Challenges(receivedChallenge.id, name)
        challengeViewModel.updateChallenge(inventory)
        findNavController().navigate(R.id.action_itemEditFragment_to_homeInvetoryFragment)
    }

}