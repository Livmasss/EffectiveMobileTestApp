package com.livmas.data.localDataBase.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.livmas.data.localDataBase.entities.LikedEntity
import com.livmas.data.localDataBase.entities.UserEntity

@Dao
interface LikedDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>

    @Insert
    fun insert(item: LikedEntity)

    @Delete
    fun delete(item: LikedEntity)
}