package com.example.picobotellaequipo6.view.viewholder

import android.os.Bundle
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.ChallengesInventoryBinding
import com.example.picobotellaequipo6.model.Challenges

class ChallengesViewHolder (binding : ChallengesInventoryBinding, navController: NavController):RecyclerView.ViewHolder(binding.root) {
    val bindingChallenge = binding
    val navController = navController

    fun setChallengeInventory(challenges: Challenges){
        bindingChallenge.challengeDescription.text = challenges.name
        bindingChallenge.deleteChallenge.setOnClickListener{
            val bundle = Bundle()
            bundle.putSerializable("reto", challenges)
            navController.navigate(R.id.action_challenges2_to_dialogDeleteChallenge, bundle)
        }
    }
}