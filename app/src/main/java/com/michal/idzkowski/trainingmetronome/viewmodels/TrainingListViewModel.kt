package com.michal.idzkowski.trainingmetronome.viewmodels

import android.content.Context
import com.michal.idzkowski.trainingmetronome.interfaces.TrainingListActionHandler
import com.michal.idzkowski.trainingmetronome.interfaces.TrainingListFragmentAccess
import com.michal.idzkowski.trainingmetronome.model.Training
import com.michal.idzkowski.trainingmetronome.storage.TrainingStorage

class TrainingListViewModel(context: Context,
                            private var trainingList: MutableList<Training>,
                            private var trainingListFragmentAccess: TrainingListFragmentAccess)
    : TrainingListActionHandler {

    private val trainingStorage = TrainingStorage(context)

    override fun addTrainingToList(training: Training) {
        trainingList.add(training)
        trainingListFragmentAccess.onTrainingAddedToList(training)
    }

    override fun addTrainingToDatabase(training: Training) {
        trainingStorage.save(training)
    }

    override fun removeTrainingFromList(position: Int, itemCount: Int) {
        val training = trainingList[position]
        trainingList.removeAt(position)
        trainingListFragmentAccess.onTrainingRemovedFromList(position, itemCount, training)
    }

    override fun removeTrainingFromDatabase(id: Long) {
        trainingStorage.remove(id)
    }

    override fun changeTrainingOnList(position: Int) {
        // TODO implement change training functionality
    }

    override fun changeTrainingInDatabase(id: Int) {
        // TODO implement change training functionality
    }

    override fun getTrainingListFromDatabase() {
        trainingList = trainingStorage.get()
        trainingListFragmentAccess.onTrainingListGotFromDatabase()
    }

    override fun restoreRemovedTraining(position: Int, training: Training) {
        trainingList.add(position, training)
        trainingListFragmentAccess.onTrainingRestored()
    }
}