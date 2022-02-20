package com.example.hilttest01

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.hilttest01.data.source.FakeRepository
import com.example.hilttest01.data.source.Repository
import com.example.hilttest01.data.source.local.TestDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: MainViewModel
    private lateinit var repository: FakeRepository

    @Before
    fun setup() {
        repository = FakeRepository()
        viewModel = MainViewModel(repository)
    }

    @Test
    fun delete() = runBlocking {
        viewModel.delete()
        val value = viewModel.user

        assertEquals(value.first(), null)
    }

    @Test
    fun add() = runBlocking{
        viewModel.add()
        val value = viewModel.user

//        assertThat(value.first(), )
        assertEquals(value.first(), TestDataModel.data)
     }
}