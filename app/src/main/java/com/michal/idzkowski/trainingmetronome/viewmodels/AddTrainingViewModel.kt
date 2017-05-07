package com.michal.idzkowski.trainingmetronome.viewmodels

import android.util.Log
import com.michal.idzkowski.trainingmetronome.interfaces.AddTrainingActionHandler
import com.michal.idzkowski.trainingmetronome.interfaces.AddTrainingFragmentAccess
import com.michal.idzkowski.trainingmetronome.model.Series

class AddTrainingViewModel(private val addTrainingFragmentAccess: AddTrainingFragmentAccess):
        AddTrainingActionHandler {
    override fun onSeriesAdded(series: Series) {
        Log.e("TAG", series.toString())
    }
}