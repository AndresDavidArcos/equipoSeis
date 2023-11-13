package com.example.picobotellaequipo6.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.picobotellaequipo6.databinding.ChallengesInventoryBinding
import com.example.picobotellaequipo6.model.Challenges

class ChallengesViewHolder (binding : ChallengesInventoryBinding):RecyclerView.ViewHolder(binding.root) {
    val bindingChallenge = binding

    fun setChallengeInventory(challenges: Challenges){
        bindingChallenge.challengeDescription.text =  challenges.name
    }
}