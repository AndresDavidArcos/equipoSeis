package com.example.picobotellaequipo6.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.DialogLayoutDeleteChallengeBinding
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.viewmodel.ChallengesViewModel

class DialogDeleteChallenge : DialogFragment() {
    private lateinit var binding : DialogLayoutDeleteChallengeBinding
    private val challengeViewModel: ChallengesViewModel by viewModels()
    private lateinit var receivedChallenge: Challenges

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogLayoutDeleteChallengeBinding.inflate(LayoutInflater.from(context))

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
    private fun dataChallenge() {
        val receivedBundle = arguments
        receivedChallenge  = receivedBundle?.getSerializable("reto") as Challenges
        binding.tvChallengeName.setText(receivedChallenge.name)
    }

    private fun controladores() {
        binding.tvSI.setOnClickListener {
            deleteChallenge()
            findNavController().navigate(R.id.action_dialogDeleteChallenge_to_challenges2)
        }
        binding.tvNO.setOnClickListener{
            findNavController().navigate(R.id.action_dialogDeleteChallenge_to_challenges2)
        }
    }

    private fun deleteChallenge(){
        challengeViewModel.deleteChallenge(receivedChallenge)
        challengeViewModel.getListChallenge()
    }
}