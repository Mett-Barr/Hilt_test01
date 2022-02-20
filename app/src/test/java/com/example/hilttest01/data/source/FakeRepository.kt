package com.example.hilttest01.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hilttest01.data.source.local.DataModel
import com.example.hilttest01.data.source.local.TestDataModel
import kotlinx.coroutines.flow.*

class FakeRepository : Repository {


    /**------------------    Flow    ----------------------**/

    private val _testData: MutableStateFlow<DataModel?> = MutableStateFlow(TestDataModel.data)
    val testData = _testData.asStateFlow()

    private val _testSize = MutableStateFlow(0)
    val testSize = _testSize.asStateFlow()

//    private var testData: DataModel? = TestDataModel.data

//    val test: Flow<DataModel?> = flow {
//        emit(testData)
//    }

//    private fun testDataModel() = flow {
//        emit(testData)
//    }

//    private fun dataSize() = flow {
//        emit(1)
//    }

    override fun getOne(): Flow<DataModel?> {
        return testData
    }

    override fun getSize(): Flow<Int> {
        return testSize
    }

    override suspend fun addOne(userItem: DataModel) {
//        testDataModel().collect {
//            testData = TestDataModel.data
//        }

        _testData.value = TestDataModel.data
        _testSize.value = 1
    }

    override suspend fun deleteOne(userItem: DataModel) {
        _testData.value = null
        _testSize.value = 0
    }


//    private fun testDataModel2(): MutableStateFlow<DataModel> {
//
//    }

    /**------------------    Livedata    ----------------------**/
    private val _testLiveData = MutableLiveData(TestDataModel.data)
    val testLiveData: LiveData<DataModel>
        get() = _testLiveData

//    private val _testSize = MutableLiveData(1)
//    val testSize: LiveData<Int>
//        get() = _testSize



}