package com.michal.idzkowski.trainingmetronome.interfaces

import com.michal.idzkowski.trainingmetronome.model.Training

interface TrainingStorageActionHandler {
    fun save(training: Training): Long
    fun remove(id: Long)
    fun update(id: Long, training: Training)
    fun get(): MutableList<Training>
}