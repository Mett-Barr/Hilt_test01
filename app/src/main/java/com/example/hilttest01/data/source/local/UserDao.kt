package com.example.hilttest01.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("select * from userTable")
    fun getAllUser(): LiveData<List<DataModel>>

    @Query("select * from userTable where userID= 1")
    fun getOne(): LiveData<DataModel>

    @Query("select * from userTable where userID= 1")
    fun getOneFlow(): Flow<DataModel>

    @Query("select COUNT(*) from userTable")
    fun size(): Flow<Int>

    @Query("UPDATE userTable SET fullName =:updateName, Designation=:updateDesignation where userID=:id")
    fun updateUser(id: Int, updateName: String, updateDesignation: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(addUser: DataModel)

    @Delete
    suspend fun deleteUser(deleteUser: DataModel)
}