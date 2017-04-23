package com.michal.idzkowski.trainingmetronome.extensions

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.michal.idzkowski.trainingmetronome.R

fun Activity.setSupportActionBarTitle(title: String) {
    val activity: AppCompatActivity = this as AppCompatActivity
    activity.supportActionBar?.title = title
}

fun Activity.setDisplayHomeAsUpEnabled(flag: Boolean) {
    val activity: AppCompatActivity = this as AppCompatActivity
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(flag)
}

fun Activity.replaceFragment(fragment: Fragment, tag: String) {
    val activity: AppCompatActivity = this as AppCompatActivity
    val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.container, fragment)
    fragmentTransaction.addToBackStack(tag)
    fragmentTransaction.commit()
}

fun Activity.addFragment(fragment: Fragment) {
    val activity: AppCompatActivity = this as AppCompatActivity
    val initialTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
    initialTransaction.add(R.id.container, fragment)
    initialTransaction.commit()
}