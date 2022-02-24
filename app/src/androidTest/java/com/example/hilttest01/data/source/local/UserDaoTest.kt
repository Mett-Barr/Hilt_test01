package com.example.hilttest01.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.hamcrest.MatcherAssert.assertThat


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    private lateinit var database: UserDB

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDB() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            UserDB::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDB() = database.close()

    @Test
    fun addDao() = runBlockingTest {
        database.userDao().addUser(TestDataModel.data)

        val value = database.userDao().getOneFlow().first()

        assertEquals(value, TestDataModel.data)
    }

    @Test
    fun deleteDao() = runBlockingTest {
        database.userDao().deleteUser(TestDataModel.data)

        val value = database.userDao().getOneFlow().firstOrNull()

        assertEquals(value, null)
    }

//    @Test
//    fun test() {
//        assertEquals(1, 0)
//    }
}

@RunWith(AndroidJUnit4::class)
class Test {
    @Test
    fun test() {
        assertEquals(1, 1)
    }
}