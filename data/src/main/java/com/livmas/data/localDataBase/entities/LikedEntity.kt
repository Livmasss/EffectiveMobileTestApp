package com.livmas.data.localDataBase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.livmas.domain.models.LikedItemModel
import java.util.UUID

@Entity("liked")
data class LikedEntity (
    @PrimaryKey
    val id: UUID,
    @ColumnInfo("item_id")
    val remoteItemId: String
) {
    constructor(likedModel: LikedItemModel): this(UUID.randomUUID(), likedModel.id)
}