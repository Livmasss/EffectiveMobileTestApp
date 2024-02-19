package com.livmas.data.localDataBase.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.livmas.data.localDataBase.entities.LikedEntity

@Dao
interface LikedDao {
    @Query("SELECT * FROM liked")
    fun getAll(): List<LikedEntity>

    @Insert
    fun insert(item: LikedEntity)

    @Query("DELETE FROM liked WHERE item_id LIKE :remoteId")
    fun delete(remoteId: String)
}