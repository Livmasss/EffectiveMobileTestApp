package com.livmas.data

import androidx.room.Room
import com.livmas.data.localDataBase.AppDataBase
import com.livmas.data.localDataBase.daos.UserDao
import com.livmas.data.localDataBase.repositories.UserRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single<AppDataBase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDataBase::class.java,
            "app_database"
        ).build()
    }

    single<UserDao> {
        val database = get<AppDataBase>()
        database.getUserDao()
    }

    single<UserRepository> {
        val dao = get<AppDataBase>().getUserDao()
        UserRepository(dao)
    }
}