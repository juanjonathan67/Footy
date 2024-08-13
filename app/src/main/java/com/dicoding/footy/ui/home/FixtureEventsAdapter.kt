package com.dicoding.footy.ui.home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.footy.R
import com.dicoding.footy.databinding.MatchEventsItemBinding
import com.dicoding.footy.domain.model.Event
import com.dicoding.footy.domain.model.EventType

class FixtureEventsAdapter(
    private var eventsList: List<Event>,
    private var homeTeamId: Int,
): RecyclerView.Adapter<FixtureEventsAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: MatchEventsItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            val stringBuilder = StringBuilder("${event.time.elapsed}")
            if (event.time.extra != null) {
                stringBuilder.append("+${event.time.extra}")
            }
            stringBuilder.append("'")
            binding.tvEventMinute.text = stringBuilder.toString()

            val home = event.teamId == homeTeamId

            val circle = binding.tvEventMinute.background as GradientDrawable
            circle.mutate()
            circle.setStroke(2, if (home) Color.RED else Color.BLUE)
            binding.ivEventIndicatorLeft.visibility = if (home) View.VISIBLE else View.GONE
            binding.ivEventIndicatorRight.visibility = if (home) View.GONE else View.VISIBLE

            if (event.type != EventType.SUBSTITUTION) {
                binding.tvPlayerNameLeft.visibility = if (home) View.VISIBLE else View.GONE
                binding.tvPlayerNameRight.visibility = if (home) View.GONE else View.VISIBLE
                binding.tvPlayerSubInLeft.visibility = View.GONE
                binding.tvPlayerSubOutLeft.visibility = View.GONE
                binding.tvPlayerSubInRight.visibility = View.GONE
                binding.tvPlayerSubOutRight.visibility = View.GONE
                if (home){
                    binding.tvPlayerNameLeft.text = event.player1Name
                }
                else {
                    binding.tvPlayerNameRight.text = event.player2Name
                }
            } else {
                binding.tvPlayerNameLeft.visibility = View.GONE
                binding.tvPlayerNameRight.visibility = View.GONE
                binding.tvPlayerSubInLeft.visibility = if (home) View.VISIBLE else View.GONE
                binding.tvPlayerSubOutLeft.visibility = if (home) View.VISIBLE else View.GONE
                binding.tvPlayerSubInRight.visibility = if (home) View.GONE else View.VISIBLE
                binding.tvPlayerSubOutRight.visibility = if (home) View.GONE else View.VISIBLE
                if (home) {
                    binding.tvPlayerSubInLeft.text = event.player1Name
                    binding.tvPlayerSubOutLeft.text = event.player2Name
                } else {
                    binding.tvPlayerSubInRight.text = event.player1Name
                    binding.tvPlayerSubOutRight.text = event.player2Name
                }
            }

            when (event.type) {
                EventType.YELLOW_CARD -> {
                    Glide.with(binding.root)
                        .load(R.drawable.ic_game_card_yellow_24dp)
                        .into(if (home) binding.ivEventIndicatorLeft else binding.ivEventIndicatorRight)
                }
                EventType.RED_CARD -> {
                    Glide.with(binding.root)
                        .load(R.drawable.ic_game_card_red_24dp)
                        .into(if (home) binding.ivEventIndicatorLeft else binding.ivEventIndicatorRight)
                }
                EventType.GOAL -> {
                    Glide.with(binding.root)
                        .load(R.drawable.ic_football_24dp)
                        .into(if (home) binding.ivEventIndicatorLeft else binding.ivEventIndicatorRight)
                }
                EventType.SUBSTITUTION -> {
                    Glide.with(binding.root)
                        .load(R.drawable.ic_substitute_24dp)
                        .into(if (home) binding.ivEventIndicatorLeft else binding.ivEventIndicatorRight)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MatchEventsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = eventsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventsList[position])
    }

    fun updateEventsList(newEventsList: List<Event>) {
        eventsList = newEventsList
        notifyDataSetChanged()
    }
}