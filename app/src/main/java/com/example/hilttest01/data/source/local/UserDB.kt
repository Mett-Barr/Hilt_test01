package com.example.hilttest01.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataModel::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao() : UserDao
}