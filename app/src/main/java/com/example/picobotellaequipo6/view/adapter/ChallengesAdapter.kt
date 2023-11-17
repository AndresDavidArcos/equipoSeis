package com.example.picobotellaequipo6.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.picobotellaequipo6.databinding.ChallengesInventoryBinding
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.view.viewholder.ChallengesViewHolder

class ChallengesAdapter(private val listChallenges:MutableList<Challenges>, private val navController: NavController):RecyclerView.Adapter<ChallengesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengesViewHolder {
        val binding = ChallengesInventoryBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ChallengesViewHolder(binding, navController)
    }

    override fun onBindViewHolder(holder: ChallengesViewHolder, position: Int) {
        val challenges = listChallenges[position]
        holder.setChallengeInventory(challenges)
    }

    override fun getItemCount(): Int {
        return listChallenges.size
    }

}