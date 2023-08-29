package com.wiquert.myfitness.adapters

import android.view.View
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wiquert.myfitness.R
import com.wiquert.myfitness.databinding.DaysListItemBinding

class DaysAdapter : ListAdapter<DayModel, DaysAdapter.DayHolder> {

    class DayHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = DaysListItemBinding.bind(view)
        fun setData(day: DayModel) = with(binding) {
            val name = root.context.getString(R.string.day) + " ${adapterPosition} + 1"
            tvName.text = name
            val exCounter = day.exercises.split(",").size.toString()
            tvExCounter.text = exCounter
        }
    }
}