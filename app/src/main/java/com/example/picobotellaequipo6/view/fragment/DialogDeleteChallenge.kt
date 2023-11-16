package com.example.picobotellaequipo6.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.DialogLayoutDeleteChallengeBinding

class DialogDeleteChallenge : DialogFragment() {
    private lateinit var binding : DialogLayoutDeleteChallengeBinding

    //falta invocar el dialog desde challenges

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogLayoutDeleteChallengeBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.tvSI.setOnClickListener {
            //falta logica de BD (eliminar reto)

            //navigate
            findNavController().navigate(R.id.action_dialogDeleteChallenge_to_challenges2)
        }
        binding.tvNO.setOnClickListener {
            //navigate
            findNavController().navigate(R.id.action_dialogDeleteChallenge_to_challenges2)
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}