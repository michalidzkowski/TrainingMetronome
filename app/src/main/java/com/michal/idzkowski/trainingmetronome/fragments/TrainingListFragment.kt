package com.michal.idzkowski.trainingmetronome.fragments

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.michal.idzkowski.trainingmetronome.R
import com.michal.idzkowski.trainingmetronome.extensions.replaceFragment
import com.michal.idzkowski.trainingmetronome.extensions.setDisplayHomeAsUpEnabled
import com.michal.idzkowski.trainingmetronome.extensions.setSupportActionBarTitle
import com.michal.idzkowski.trainingmetronome.interfaces.OnTrainingChangeListener
import com.michal.idzkowski.trainingmetronome.interfaces.TrainingListFragmentAccess
import com.michal.idzkowski.trainingmetronome.model.Series
import com.michal.idzkowski.trainingmetronome.model.Training
import com.michal.idzkowski.trainingmetronome.recyclerview.TrainingListAdapter
import com.michal.idzkowski.trainingmetronome.viewmodels.TrainingListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.training_list.view.*


class TrainingListFragment : Fragment(), View.OnClickListener, OnTrainingChangeListener,
        TrainingListFragmentAccess {

    companion object {
        private const val TITLE = "Training list"
        private const val SNACK_BAR_TEXT = "Training removed"
        private const val SNACK_BAR_BUTTON_TEXT = "UNDO"
    }

    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var adapter: TrainingListAdapter
    private lateinit var trainingListViewModel: TrainingListViewModel

    private var trainingList: MutableList<Training> = mutableListOf()
    private var temporaryTrainingList: MutableList<Training> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.training_list, container, false)
        initializeFloatingActionButton()
        setUpActionBar()

        trainingListViewModel = TrainingListViewModel(
                activity.applicationContext,
                trainingList,
                this
        )

        setUpRecyclerView(view)

        trainingListViewModel.getTrainingListFromDatabase()

        insertTraining()
        getTrainings()

        return view
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.floatingActionButton -> {
                activity.replaceFragment(AddTrainingFragment(), AddTrainingFragment.TAG)
            }
        }
    }

    override fun onRemove(position: Int, training: Training) {

    }

    override fun onTrainingAddedToList(training: Training) {
        adapter.notifyDataSetChanged()
        trainingListViewModel.addTrainingToDatabase(training)
    }

    override fun onTrainingRemovedFromList(position: Int, itemCount: Int, training: Training) {
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, itemCount)

        val snackBar: Snackbar = Snackbar.make(coordinatorLayout, SNACK_BAR_TEXT, Snackbar.LENGTH_LONG)
        snackBar.setAction(SNACK_BAR_BUTTON_TEXT, { trainingListViewModel.restoreRemovedTraining(position, training) })
        snackBar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(snackbar: Snackbar?, event: Int) {
                if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                    trainingListViewModel.removeTrainingFromDatabase(training.id)
                }
            }
        })
        snackBar.show()
    }

    override fun onTrainingChangedOnList(position: Int) {
        // TODO implement change training functionality
    }

    override fun onTrainingListGotFromDatabase() {
        adapter.notifyDataSetChanged()
    }

    override fun onTrainingRestored() {
        adapter.notifyDataSetChanged()
    }

    private fun insertTraining() {
        val series: Series = Series(10, 10, 5)
        val seriesList: MutableList<Series> = mutableListOf(series, series, series)

        val firstTraining: Training = Training(0, "First", seriesList, "#E91E63")
        val secondTraining: Training = Training(1, "Second", seriesList, "#FF5722")
        val thirdTraining: Training = Training(2, "Third", seriesList, "#3F51B5")
        val fourthTraining: Training = Training(3, "Fourth", seriesList, "#9C27B0")
        val fifthTraining: Training = Training(4, "Fifth", seriesList, "#F44336")

        temporaryTrainingList.add(firstTraining)
        temporaryTrainingList.add(secondTraining)
        temporaryTrainingList.add(thirdTraining)
        temporaryTrainingList.add(fourthTraining)
        temporaryTrainingList.add(fifthTraining)
    }

    private fun getTrainings() {
        trainingList.clear()
//        for (training in databaseHelper.getTrainings()) {
//            Log.e("TRAINING", training.id.toString())
//            adapter.addTrainingToList(0, training)
//        }

        for (training in temporaryTrainingList) {
            adapter.add(0, training)

        }
    }

    private fun initializeFloatingActionButton() {
        // Floating action button settings
        floatingActionButton = activity.floatingActionButton
        floatingActionButton.show()

        //Floating Action button popup animation
        floatingActionButton.clearAnimation()
        val animation = AnimationUtils.loadAnimation(activity, R.anim.pop_up)
        floatingActionButton.startAnimation(animation)
        floatingActionButton.setOnClickListener(this)
    }

    private fun setUpActionBar() {
        activity.setSupportActionBarTitle(TITLE)
        activity.setDisplayHomeAsUpEnabled(false)
    }

    private fun setUpRecyclerView(view: View) {
        adapter = TrainingListAdapter(trainingList, this)
        recyclerView = view.recyclerView as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity.applicationContext)
        recyclerView.adapter = adapter
        coordinatorLayout = activity.coordinatorLayout as CoordinatorLayout
    }
}

