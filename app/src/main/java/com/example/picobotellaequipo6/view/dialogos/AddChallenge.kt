package com.example.picobotellaequipo6.view.dialogos

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.picobotellaequipo6.databinding.AddChallengeBinding
import com.google.android.material.button.MaterialButton
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.viewmodel.ChallengesViewModel

class AddChallengeDialog (private val challengesViewModel: ChallengesViewModel) {
    fun showDialog(context: Context,func: () -> Unit) {
        val inflater = LayoutInflater.from(context)
        val binding = AddChallengeBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.btnSave.isEnabled = false
        updateButtonColor(binding.btnSave.isEnabled, binding.btnSave)

        binding.eT.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnSave.isEnabled = s.toString().isNotEmpty()
                updateButtonColor(binding.btnSave.isEnabled, binding.btnSave)
            }

        })

        binding.btnCancel.setOnClickListener {
            alertDialog.dismiss()
            func()
        }

        binding.btnSave.setOnClickListener {
            saveChallenge(binding.eT.text.toString())
            func()
            alertDialog.dismiss()

        }

        alertDialog.show()

    }

    private fun saveChallenge(description: String) {
        val challenge = Challenges(name = description)
        challengesViewModel.saveInventory(challenge)
    }

    private fun updateButtonColor(isEnabled: Boolean, btnSave: MaterialButton) {
        if (isEnabled) {
            btnSave.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#ff9d00")) // Color naranja
        } else {
            btnSave.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#808080")) // Color gris
        }
    }
}