package com.livmas.data

import androidx.room.Room
import com.livmas.data.localDataBase.AppDataBase
import com.livmas.data.localDataBase.daos.LikedDao
import com.livmas.data.localDataBase.daos.UserDao
import com.livmas.data.localDataBase.repositories.LikedRepositoryImpl
import com.livmas.data.localDataBase.repositories.UserRepositoryImpl
import com.livmas.data.retrofit.repositories.CatalogRepositoryImpl
import com.livmas.domain.iRepositories.LikedRepository
import com.livmas.domain.iRepositories.UserRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single<AppDataBase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDataBase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<UserDao> {
        val database = get<AppDataBase>()
        database.getUserDao()
    }

    single<UserRepository> {
        val dao = get<AppDataBase>().getUserDao()
        UserRepositoryImpl(dao)
    }

    single<LikedDao> {
        val database = get<AppDataBase>()
        database.getLikedDao()
    }

    single<LikedRepository> {
        val dao = get<LikedDao>()
        LikedRepositoryImpl(dao, CatalogRepositoryImpl())
    }
}