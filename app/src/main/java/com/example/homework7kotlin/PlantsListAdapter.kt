package com.example.homework7kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework7kotlin.databinding.PlantsItemBinding

class PlantsListAdapter(private val onItemClick: (plants: Plants) -> Unit) :
    RecyclerView.Adapter<PlantsListAdapter.PlantsViewHolder>() {

    private var plants = mutableListOf<Plants>()

    fun addItem(plant: MutableList<Plants>) {
        plants = plant
        notifyDataSetChanged()
    }

    inner class PlantsViewHolder(private val binding: PlantsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick(plants[adapterPosition])
            }
        }

        fun onBind(plants: Plants) = with(binding) {
            Glide.with(ivImage.context)
                .load(plants.plantsImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivImage)
            tvName.text = plants.name
            tvLeaves.text = plants.leaves.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantsViewHolder {
        val binding = PlantsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantsViewHolder, position: Int) {
        holder.onBind(plants[position])
    }

    override fun getItemCount(): Int = plants.size
}