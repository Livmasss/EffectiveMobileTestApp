package com.livmas.data.localDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.livmas.data.localDataBase.daos.LikedDao
import com.livmas.data.localDataBase.daos.UserDao
import com.livmas.data.localDataBase.entities.LikedEntity
import com.livmas.data.localDataBase.entities.UserEntity

@Database(entities = [UserEntity::class, LikedEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getLikedDao(): LikedDao
}