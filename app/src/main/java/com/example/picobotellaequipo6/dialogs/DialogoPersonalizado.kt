package com.example.clase6.Dialogos

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.picobotellaequipo6.databinding.ShowChallengeBinding
import com.example.picobotellaequipo6.viewmodel.ChallengesViewModel

class DialogoPersonalizado {
        companion object{
            fun showDialogPersonalizado(
                context: Context, challenge: String, pokemons: ChallengesViewModel
            ) {
                val inflater = LayoutInflater.from(context)
                val binding = ShowChallengeBinding.inflate(inflater)
                binding.texto.text = challenge

                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                alertDialog.setCancelable(false)
                alertDialog.setView(binding.root)

                binding.btnAceptar.setOnClickListener {
                    alertDialog.dismiss()
                }
                alertDialog.show()
            }
        }
    }