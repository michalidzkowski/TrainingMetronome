package com.michal.idzkowski.trainingmetronome.storage


class SeriesContract {
    companion object {
        const val tableName: String = "Series"
        const val columnId: String = "id"
        const val columnTrainingId: String = "training_id"
        const val columnRepetitions: String = "repetitions"
        const val columnIntervals: String = "intervals"
        const val columnAfterPause: String = "after_pause"

        const val createTable: String = "CREATE TABLE if not exists $tableName (" +
                "$columnId integer PRIMARY KEY autoincrement, " +
                "$columnTrainingId integer, " +
                "$columnRepetitions integer, " +
                "$columnIntervals integer, " +
                "$columnAfterPause integer" +
                ")"

        const val dropTable: String = "DROP TABLE if exists $tableName"

        fun selectSeriesByTrainingId(trainingId: Long): String {
            return "SELECT * FROM $tableName WHERE $columnTrainingId=$trainingId"
        }

    }
}