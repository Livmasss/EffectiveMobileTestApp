package com.livmas.data.localDataBase.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.livmas.data.localDataBase.entities.UserEntity

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserEntity)

    @Delete
    fun deleteAll(user: UserEntity)

    @Query("SELECT * FROM users")
    fun getById(user: UserEntity)
}