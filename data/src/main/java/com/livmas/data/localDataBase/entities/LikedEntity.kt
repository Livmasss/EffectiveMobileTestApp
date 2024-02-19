package com.livmas.data.localDataBase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("liked")
data class LikedEntity (
    @PrimaryKey
    val id: UUID,
    @ColumnInfo("item_id")
    val remoteItemId: Int
)