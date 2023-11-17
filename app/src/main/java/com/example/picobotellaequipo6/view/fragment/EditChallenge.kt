package com.example.picobotellaequipo6.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.viewmodel.ChallengesViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.DialogEditChallengeBinding

class EditChallenge : DialogFragment() {
    private lateinit var binding: DialogEditChallengeBinding
    private val challengeViewModel: ChallengesViewModel by viewModels()
    private lateinit var receivedChallenge: Challenges

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        binding = DialogEditChallengeBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataChallenge()
        controladores()
    }

    private fun dataChallenge(){
        val receivedBundle = arguments
        receivedChallenge = receivedBundle?.getSerializable("reto") as Challenges
        binding.etChallengeName.setText(receivedChallenge.name, TextView.BufferType.EDITABLE)

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