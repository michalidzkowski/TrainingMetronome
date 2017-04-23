package com.michal.idzkowski.trainingmetronome.storage

import android.content.Context
import com.michal.idzkowski.trainingmetronome.interfaces.TrainingStorageActionHandler
import com.michal.idzkowski.trainingmetronome.model.Training

class TrainingStorage(context: Context) : TrainingStorageActionHandler {

    private val databaseHelper: DatabaseHelper = DatabaseHelper(context)

    override fun save(training: Training): Long {
        return databaseHelper.insertTraining(training)
    }

    override fun remove(id: Long) {
        databaseHelper.removeTraining(id)
    }

    override fun update(id: Long, training: Training) {
        // TODO implement update training
    }

    override fun get(): MutableList<Training> {
        return databaseHelper.getTrainings()
    }
}