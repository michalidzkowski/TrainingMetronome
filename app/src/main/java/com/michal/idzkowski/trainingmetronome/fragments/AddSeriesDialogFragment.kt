package com.michal.idzkowski.trainingmetronome.fragments;

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.michal.idzkowski.trainingmetronome.R
import com.michal.idzkowski.trainingmetronome.interfaces.AddSeriesActionHandler
import com.michal.idzkowski.trainingmetronome.interfaces.AddSeriesFragmentAccess
import com.michal.idzkowski.trainingmetronome.viewmodels.AddSeriesViewModel
import kotlinx.android.synthetic.main.add_series.view.*

class AddSeriesDialogFragment(private var addSeriesActionHandler: AddSeriesActionHandler) :
        DialogFragment(), AddSeriesFragmentAccess, TextWatcher {
    companion object {
        const private val ADD_SERIES = "Add series"
        const private val OK = "OK"
        const private val CANCEL = "CANCEL"
    }

    private lateinit var addSeriesViewModel: AddSeriesViewModel
    private lateinit var editTextRepetitions: EditText
    private lateinit var editTextIntervals: EditText
    private lateinit var editTextAfterPause: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = View.inflate(activity, R.layout.add_series, null)
        initializeUIElements(view)
        addSeriesViewModel = AddSeriesViewModel(this, addSeriesActionHandler)

        return AlertDialog.Builder(activity)
                .setTitle(ADD_SERIES)
                .setView(view)
                .setPositiveButton(OK, { _, _ ->
                    addSeriesViewModel.addSeries(
                            editTextRepetitions.text.toString(),
                            editTextIntervals.text.toString(),
                            editTextAfterPause.text.toString())
                })
                .setNegativeButton(CANCEL, { _, _ ->
                    dialog.dismiss()
                })
                .create()
    }

    override fun onStart() {
        super.onStart()
        addSeriesViewModel.onStart()
    }

    override fun changePositiveButtonState(enabled: Boolean) {
        val alertDialog = dialog as AlertDialog
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = enabled
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        addSeriesViewModel.onTextChanged(
                editTextRepetitions.text.length,
                editTextIntervals.text.length,
                editTextAfterPause.text.length)
    }

    private fun initializeUIElements(view: View) {
        editTextRepetitions = view.editTextRepetitions
        editTextIntervals = view.editTextIntervals
        editTextAfterPause = view.editTextAfterPause

        editTextRepetitions.addTextChangedListener(this)
        editTextIntervals.addTextChangedListener(this)
        editTextAfterPause.addTextChangedListener(this)
    }
}
