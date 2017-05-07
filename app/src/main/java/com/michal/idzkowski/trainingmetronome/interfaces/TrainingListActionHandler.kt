package com.michal.idzkowski.trainingmetronome.interfaces

import com.michal.idzkowski.trainingmetronome.model.Training

interface TrainingListActionHandler {
    fun addTrainingToList(training: Training)
    fun addTrainingToDatabase(training: Training)
    fun removeTrainingFromList(position: Int, itemCount: Int)
    fun removeTrainingFromDatabase(id: Long)
    fun changeTrainingOnList(position: Int)
    fun changeTrainingInDatabase(id: Int)
    fun getTrainingListFromDatabase()
    fun restoreRemovedTraining(position: Int, training: Training)
}