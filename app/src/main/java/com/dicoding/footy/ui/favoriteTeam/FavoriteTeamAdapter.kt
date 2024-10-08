package com.dicoding.footy.ui.favoriteTeam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.footy.databinding.FavoriteTeamItemBinding
import com.dicoding.footy.domain.model.FavoriteTeam

class FavoriteTeamAdapter(
    private var teamList: List<FavoriteTeam>,
    private var onItemClickCallback: OnItemClickCallback,
): RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(favoriteTeam: FavoriteTeam)
    }

    inner class ViewHolder(
        private val binding: FavoriteTeamItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteTeam: FavoriteTeam) {
            Glide.with(binding.root)
                .load(favoriteTeam.badge)
                .into(binding.ivTeamBadge)
            binding.tvTeamName.text = favoriteTeam.name
            binding.tvTeamCountry.text = favoriteTeam.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavoriteTeamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(teamList[position])

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(teamList[position])
        }
    }

    fun updateTeamList(newTeamList: List<FavoriteTeam>) {
        teamList = newTeamList
        notifyDataSetChanged()
    }
}