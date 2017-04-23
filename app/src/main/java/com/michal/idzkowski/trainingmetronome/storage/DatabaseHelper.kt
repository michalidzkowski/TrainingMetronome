package com.michal.idzkowski.trainingmetronome.storage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.michal.idzkowski.trainingmetronome.model.Series
import com.michal.idzkowski.trainingmetronome.model.Training

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,
        DatabaseConfiguration.NAME, null, DatabaseConfiguration.VERSION) {
    companion object DatabaseConfiguration {
        private const val NAME = "trainingMetronomeDatabase"
        private const val VERSION = 1
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.execSQL(SeriesContract.dropTable)
        database.execSQL(TrainingContract.dropTable)
        onCreate(database)
    }

    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL(SeriesContract.createTable)
        database.execSQL(TrainingContract.createTable)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertTraining(training: Training): Long {
        val db: SQLiteDatabase = this.writableDatabase

        val values: ContentValues = ContentValues()
        values.put(TrainingContract.columnName, training.name)
        values.put(TrainingContract.columnColor, training.color)

        val trainingId = db.insert(TrainingContract.tableName, null, values)
        insertSeriesList(db, training.seriesList, trainingId)
        return trainingId
    }

    fun getTrainings(): MutableList<Training> {
        val trainingList: MutableList<Training> = arrayListOf()
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery(TrainingContract.selectAllTrainings(), null)

        if (cursor.moveToFirst()) {
            do {
                val training: Training = Training(
                        cursor.getLong(cursor.getColumnIndex(TrainingContract.columnId)),
                        cursor.getString(cursor.getColumnIndex(TrainingContract.columnName)),
                        getSeriesListByTrainingId(db, cursor.getLong(cursor.getColumnIndex(TrainingContract.columnId))),
                        cursor.getString(cursor.getColumnIndex(TrainingContract.columnColor))
                )

                trainingList.add(training)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return trainingList
    }

    fun removeTraining(trainingId: Long) {
        val db: SQLiteDatabase = this.writableDatabase
        Log.e("DELETE", db.delete(TrainingContract.tableName, TrainingContract.columnId + " = ?", arrayOf(trainingId.toString())).toString())
        Log.e("DELETE", db.delete(SeriesContract.tableName, SeriesContract.columnTrainingId + " = ?", arrayOf(trainingId.toString())).toString())
    }

    private fun insertSeriesList(db: SQLiteDatabase, seriesList: MutableList<Series>, trainingId: Long) {
        for ((repetitions, intervals, afterPause) in seriesList) {
            val values: ContentValues = ContentValues()
            values.put(SeriesContract.columnTrainingId, trainingId)
            values.put(SeriesContract.columnRepetitions, repetitions)
            values.put(SeriesContract.columnIntervals, intervals)
            values.put(SeriesContract.columnAfterPause, afterPause)
            db.insert(SeriesContract.tableName, null, values)
        }
    }

    private fun getSeriesListByTrainingId(db: SQLiteDatabase, trainingId: Long): MutableList<Series> {
        val seriesList: MutableList<Series> = arrayListOf()
        val cursor: Cursor = db.rawQuery(SeriesContract.selectSeriesByTrainingId(trainingId), null)

        if (cursor.moveToFirst()) {
            do {
                val series: Series = Series(
                        cursor.getInt(cursor.getColumnIndex(SeriesContract.columnRepetitions)),
                        cursor.getInt(cursor.getColumnIndex(SeriesContract.columnIntervals)),
                        cursor.getInt(cursor.getColumnIndex(SeriesContract.columnAfterPause))
                )

                seriesList.add(series)
            } while (cursor.moveToNext())
        }

        cursor.close()

        return seriesList
    }
}