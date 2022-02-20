package com.example.hilttest01.data.source

import androidx.lifecycle.LiveData
import com.example.hilttest01.data.source.local.DataModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getOne(): Flow<DataModel?>

    fun getSize(): Flow<Int>

    suspend fun addOne(userItem: DataModel)

    suspend fun deleteOne(userItem: DataModel)
}