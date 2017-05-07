package com.michal.idzkowski.trainingmetronome

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.michal.idzkowski.trainingmetronome.extensions.addFragment
import com.michal.idzkowski.trainingmetronome.fragments.TrainingListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(TrainingListFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }
}