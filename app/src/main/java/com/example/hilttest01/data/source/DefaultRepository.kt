package com.example.hilttest01.data.source

import androidx.lifecycle.LiveData
import com.example.hilttest01.data.source.local.DataModel
import com.example.hilttest01.data.source.local.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val userDao: UserDao
) : Repository {
    override fun getOne(): Flow<DataModel> {
        return userDao.getOneFlow()
    }

    override fun getSize(): Flow<Int> {
        return userDao.size()
    }

    override suspend fun addOne(userItem: DataModel) {
        userDao.addUser(userItem)
    }

    override suspend fun deleteOne(userItem: DataModel) {
        userDao.deleteUser(userItem)
    }
}