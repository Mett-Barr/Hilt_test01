package com.example.hilttest01

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilttest01.data.source.DefaultRepository
import com.example.hilttest01.data.source.Repository
import com.example.hilttest01.data.source.local.DataModel
import com.example.hilttest01.data.source.local.TestDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val userDao: UserDao,
    private val repository: Repository
) : ViewModel() {
//    val user: Flow<DataModel> =  userDao.getOneFlow()

    val user: Flow<DataModel?> =  repository.getOne()

    val size: Flow<Int> = repository.getSize()

//    fun getOne(): DataModel? {
//
//    }

    fun init() {
        viewModelScope.launch {
//            if (repository.getSize() == 0) {
//                repository.addOne(DataModel(0, "123", "456"))
//            }

//            delay(3000)
            size.collect {
                if (it == 0) {
                    repository.addOne(TestDataModel.data)
                    Log.d("!!!!", "init: ")
                    cancel()
                }
            }
        }
    }

    fun add() {
        viewModelScope.launch {
            repository.addOne(TestDataModel.data)
        }
    }

    fun delete() {
        viewModelScope.launch {
            repository.deleteOne(TestDataModel.data)
        }
    }

    fun getSize() {
        viewModelScope.launch {
            repository.getSize()
        }
    }
}