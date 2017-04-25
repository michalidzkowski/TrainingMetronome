package com.michal.idzkowski.trainingmetronome.interfaces

import com.michal.idzkowski.trainingmetronome.model.Training

interface OnTrainingChangeListener {
    fun onRemove(position: Int, training: Training)
}