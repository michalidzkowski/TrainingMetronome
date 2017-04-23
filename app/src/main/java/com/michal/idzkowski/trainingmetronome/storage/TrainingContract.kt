package com.michal.idzkowski.trainingmetronome.storage

class TrainingContract {
    companion object {
        val tableName: String = "Trainings"
        val columnId: String = "id"
        val columnName: String = "name"
        val columnColor: String = "color"

        val createTable: String = "CREATE TABLE if not exists $tableName (" +
                "$columnId integer PRIMARY KEY autoincrement, " +
                "$columnName text, " +
                "$columnColor text " +
                ")"

        val dropTable: String = "DROP TABLE if exists ${tableName}"

        fun selectAllTrainings(): String {
            return "SELECT * FROM ${tableName}"
        }
    }
}