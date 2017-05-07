package com.michal.idzkowski.trainingmetronome.viewmodels

import com.michal.idzkowski.trainingmetronome.interfaces.AddSeriesActionHandler
import com.michal.idzkowski.trainingmetronome.interfaces.AddSeriesFragmentAccess
import com.michal.idzkowski.trainingmetronome.model.Series

class AddSeriesViewModel(private val addSeriesFragmentAccess: AddSeriesFragmentAccess,
                         private val addSeriesActionHandler: AddSeriesActionHandler) {

    fun onStart(){
        addSeriesFragmentAccess.changePositiveButtonState(false)
    }

    fun onTextChanged(repetitionsValue: Int, intervalValue: Int, AfterPauseValue: Int) {
        if (repetitionsValue > 0 && intervalValue > 0 && AfterPauseValue > 0) {
            addSeriesFragmentAccess.changePositiveButtonState(true)
        } else {
            addSeriesFragmentAccess.changePositiveButtonState(false)
        }
    }

    fun addSeries(repetitionsValue: String, intervalValue: String, afterPauseValue: String){
        addSeriesActionHandler.onSeriesAdded(
                Series(
                        repetitionsValue.toInt(),
                        intervalValue.toInt(),
                        afterPauseValue.toInt()))
    }
}