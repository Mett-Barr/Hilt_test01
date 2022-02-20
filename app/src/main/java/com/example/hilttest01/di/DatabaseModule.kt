package com.example.hilttest01.di

import android.content.Context
import androidx.room.Room
import com.example.hilttest01.data.source.DefaultRepository
import com.example.hilttest01.data.source.Repository
import com.example.hilttest01.data.source.local.UserDB
import com.example.hilttest01.data.source.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): UserDB {
        return Room.databaseBuilder(
            appContext,
            UserDB::class.java,
            "logging.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDB): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideRepository(userDao: UserDao) = DefaultRepository(userDao) as Repository
}