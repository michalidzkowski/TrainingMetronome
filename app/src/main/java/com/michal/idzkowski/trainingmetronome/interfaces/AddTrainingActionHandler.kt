package com.michal.idzkowski.trainingmetronome.interfaces

import com.michal.idzkowski.trainingmetronome.model.Series

interface AddTrainingActionHandler {
    fun onSeriesAdded(series: Series)
}