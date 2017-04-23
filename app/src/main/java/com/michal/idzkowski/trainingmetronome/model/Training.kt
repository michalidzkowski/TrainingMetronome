package com.michal.idzkowski.trainingmetronome.model

data class Training(var id: Long, val name: String, val seriesList: MutableList<Series>, val color: String)