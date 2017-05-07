package com.michal.idzkowski.trainingmetronome.interfaces

import com.michal.idzkowski.trainingmetronome.model.Training

interface TrainingListFragmentAccess {
    fun onTrainingAddedToList(training: Training)
    fun onTrainingRemovedFromList(position: Int, itemCount: Int, training: Training)
    fun onTrainingChangedOnList(position: Int)
    fun onTrainingListGotFromDatabase()
    fun onTrainingRestored()
}