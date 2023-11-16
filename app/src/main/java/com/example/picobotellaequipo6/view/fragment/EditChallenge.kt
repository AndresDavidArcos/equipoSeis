package com.example.picobotellaequipo6.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.picobotellaequipo6.databinding.FragmentEditChallengeBinding
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.viewmodel.ChallengesViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picobotellaequipo6.R

class EditChallenge : DialogFragment() {
    private lateinit var binding: FragmentEditChallengeBinding
    private val challengeViewModel: ChallengesViewModel by viewModels()
    private lateinit var receivedChallenge: Challenges

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        binding = FragmentEditChallengeBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInventory()
        controladores()
    }

    private fun dataInventory(){
        val receivedBundle = arguments
        receivedChallenge = receivedBundle?.getSerializable("dataChallenge") as Challenges
        binding.etChallengeName.setText(receivedChallenge.name)

    }

    private fun controladores() {
        binding.btnSaveChallenge.setOnClickListener {
            updateChallenge()
        }
        binding.btnCancel.setOnClickListener{
            findNavController().navigate(R.id.action_editChallenge_to_challenges2)
        }
    }

    private fun updateChallenge(){
        val name = binding.etChallengeName.text.toString()
        val challenge = Challenges(receivedChallenge.id, name)
        challengeViewModel.updateChallenge(challenge)
        findNavController().navigate(R.id.action_editChallenge_to_challenges2)
    }

}