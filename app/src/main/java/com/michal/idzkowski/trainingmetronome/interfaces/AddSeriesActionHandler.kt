package com.michal.idzkowski.trainingmetronome.interfaces

import com.michal.idzkowski.trainingmetronome.model.Series

interface AddSeriesActionHandler {
    fun onSeriesAdded(series: Series)
}