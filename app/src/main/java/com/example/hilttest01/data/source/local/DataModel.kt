package com.example.hilttest01.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class DataModel(
    @PrimaryKey(autoGenerate = true)
    var userID: Int = 0,
    @ColumnInfo(name = "fullName")
    val userName: String = "null",
    @ColumnInfo(name = "Designation")
    val userDesignation: String = "null"
)

object TestDataModel {
    val data = DataModel(1, "123", "456")
}
