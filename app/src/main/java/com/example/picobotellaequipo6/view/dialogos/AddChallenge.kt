package com.example.picobotellaequipo6.view.dialogos

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.picobotellaequipo6.databinding.AddChallengeBinding
import com.google.android.material.button.MaterialButton
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.viewmodel.ChallengesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddChallengeDialog(private val challengesViewModel: ChallengesViewModel) {
    fun showDialog(context: Context, func: () -> Unit) {
        val inflater = LayoutInflater.from(context)
        val binding = AddChallengeBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.guardar.isEnabled = false
        colorBoton(binding.guardar.isEnabled, binding.guardar)
        binding.eT.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                binding.guardar.isEnabled = s.toString().isNotEmpty()
                colorBoton(binding.guardar.isEnabled, binding.guardar)
            }
        })
        binding.cancelar.setOnClickListener {
            alertDialog.dismiss()
            func()
        }
        binding.guardar.setOnClickListener {
            saveChallenge(binding.eT.text.toString(), func, alertDialog)
        }
        alertDialog.show()
    }

    private fun saveChallenge(description: String, func: () -> Unit, alertDialog: AlertDialog) {
        GlobalScope.launch(Dispatchers.Main) {
            challengesViewModel.saveInventory(Challenges(name = description))
            func()
            alertDialog.dismiss()
        }
    }

    private fun colorBoton(enable: Boolean, guardar: MaterialButton) {
        if (enable) {
            guardar.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ff9d00"))
        } else {
            guardar.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#808080"))
        }
    }
}
