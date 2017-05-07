package com.michal.idzkowski.trainingmetronome.viewmodels

import android.util.Log
import com.michal.idzkowski.trainingmetronome.interfaces.AddSeriesActionHandler
import com.michal.idzkowski.trainingmetronome.interfaces.AddTrainingFragmentAccess
import com.michal.idzkowski.trainingmetronome.model.Series

class AddTrainingViewModel(private val addTrainingFragmentAccess: AddTrainingFragmentAccess) :
        AddSeriesActionHandler {
    override fun onSeriesAdded(series: Series) {
        Log.e("TAG", series.toString())
    }
}