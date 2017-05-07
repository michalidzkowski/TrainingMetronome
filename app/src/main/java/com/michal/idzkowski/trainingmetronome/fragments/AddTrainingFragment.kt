package com.michal.idzkowski.trainingmetronome.fragments

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.android.colorpicker.ColorPickerDialog
import com.android.colorpicker.ColorPickerSwatch
import com.michal.idzkowski.trainingmetronome.R
import com.michal.idzkowski.trainingmetronome.extensions.setDisplayHomeAsUpEnabled
import com.michal.idzkowski.trainingmetronome.extensions.setSupportActionBarTitle
import com.michal.idzkowski.trainingmetronome.interfaces.AddTrainingFragmentAccess
import com.michal.idzkowski.trainingmetronome.viewmodels.AddTrainingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_training.view.*


class AddTrainingFragment : Fragment(), View.OnClickListener, ColorPickerSwatch
.OnColorSelectedListener, View.OnFocusChangeListener, AddTrainingFragmentAccess {
    companion object {
        const val TAG = "AddTrainingFragment"
        const val TITLE = "Add training"
    }

    private lateinit var addTrainingViewModel: AddTrainingViewModel
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var editTextTrainingName: EditText
    private lateinit var colorPickerButton: Button
    private lateinit var colorPickerButtonDrawable: Drawable
    private var selectedColor = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.add_training, container, false)
        setUpActionBar()
        initializeUIElements(view)
        addTrainingViewModel = AddTrainingViewModel(this)
        return view
    }

    override fun onClick(view: View) {
        editTextTrainingName.clearFocus()
        when (view.id) {
            R.id.floatingActionButton -> {
//                val seriesDialogFragment = AddSeriesDialogFragment(addTrainingViewModel)
//                seriesDialogFragment.show(fragmentManager, "TAG")
            }
            R.id.colorPickerButton -> {
                val colorPickerDialog = ColorPickerDialog()
                colorPickerDialog.initialize(R.string.repetitions_number,
                        activity.resources.getIntArray(R.array.colors),
                        selectedColor, 5, 18)
                colorPickerDialog.setOnColorSelectedListener(this)
                colorPickerDialog.show(activity.fragmentManager, "ffds")
            }
        }
    }

    override fun onColorSelected(color: Int) {
        selectedColor = color
        setColorPickerButtonBackgroundColor(selectedColor)
    }

    override fun onFocusChange(view: View, focused: Boolean) {
        when (view.id) {
            R.id.editTextTrainingName -> {
                if (!focused) {
                    val inputMethodManager = activity.getSystemService(
                            Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(editTextTrainingName.windowToken, 0)
                }
            }
        }
    }

    private fun initializeFloatingActionButton() {
        floatingActionButton = activity.floatingActionButton
        floatingActionButton.show()

        floatingActionButton.clearAnimation()
        val animation = AnimationUtils.loadAnimation(activity, R.anim.pop_up)
        floatingActionButton.startAnimation(animation)
        floatingActionButton.setOnClickListener(this)
    }

    private fun initializeColorSelector(view: View) {
        selectedColor = ContextCompat.getColor(activity, R.color.materialIndigo500)
        colorPickerButton = view.colorPickerButton
        colorPickerButtonDrawable = ContextCompat.getDrawable(activity, R.drawable.color_picker_button)
        setColorPickerButtonBackgroundColor(selectedColor)
        colorPickerButton.setOnClickListener(this)
    }

    private fun initializeUIElements(view: View) {
        editTextTrainingName = view.editTextTrainingName
        editTextTrainingName.onFocusChangeListener = this

        initializeFloatingActionButton()
        initializeColorSelector(view)
    }

    private fun setUpActionBar() {
        activity.setSupportActionBarTitle(TITLE)
        activity.setDisplayHomeAsUpEnabled(true)
    }

    private fun setColorPickerButtonBackgroundColor(color: Int) {
        colorPickerButtonDrawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        colorPickerButton.setBackgroundDrawable(colorPickerButtonDrawable)

    }
}