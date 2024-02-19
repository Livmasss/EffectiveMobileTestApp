package com.livmas.data

import androidx.room.Room
import com.livmas.data.localDataBase.AppDataBase
import com.livmas.data.localDataBase.daos.UserDao
import com.livmas.data.localDataBase.repositories.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<AppDataBase> {
        Room.databaseBuilder(
            context = get(),
            AppDataBase::class.java,
            "database-name"
        ).build()
    }
    single<UserRepository> {
        val dao = get<AppDataBase>().getUserDao()
        UserRepository(dao)
    }
}