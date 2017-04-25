package com.michal.idzkowski.trainingmetronome.recyclerview

import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.michal.idzkowski.trainingmetronome.R
import com.michal.idzkowski.trainingmetronome.interfaces.OnTrainingChangeListener
import com.michal.idzkowski.trainingmetronome.model.Training
import kotlinx.android.synthetic.main.training_card_item.view.*

class TrainingListAdapter(var trainings: MutableList<Training>,
                          var onTrainingChangeListener: OnTrainingChangeListener)
    : RecyclerView.Adapter<TrainingListAdapter.ViewHolder>() {

    fun add(position: Int, training: Training) {
        trainings.add(position, training)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        onTrainingChangeListener.onRemove(position, trainings[position])
        trainings.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = trainings[position].name
        holder.tvNumberOfSeries.text = trainings[position].seriesList.size.toString()
        holder.tvNumberOfRepetitions.text = getRepetitionsNumber(position).toString()
        holder.tvDuration.text = getDuration(position)
        holder.trainingPosition = position
        holder.cardView.setCardBackgroundColor(Color.parseColor(trainings[position].color))
        holder.ivRemove.setOnClickListener { remove(position) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.training_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trainings.size
    }

    private fun getDuration(position: Int): String {
        var duration: Int = 0

        trainings[position].seriesList.forEach {
            duration = it.repetitions * it.intervals + it.afterPause
        }

        return duration.toString()
    }

    private fun getRepetitionsNumber(position: Int): Int {
        var repetitionNumber: Int = 0
        for ((repetitions) in trainings[position].seriesList) {
            repetitionNumber += repetitions
        }
        return repetitionNumber
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.tvName
        val tvNumberOfSeries: TextView = itemView.tvNumberOfSeries
        val tvNumberOfRepetitions: TextView = itemView.tvNumberOfRepetitions
        val tvDuration: TextView = itemView.tvDuration
        val ivRemove: ImageView = itemView.ivRemove
        val cardView: CardView = itemView.cardView
        var trainingPosition: Int = 0
    }
}