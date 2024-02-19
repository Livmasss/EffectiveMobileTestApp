package com.livmas.data.localDataBase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.UUID

@Entity("users")
data class UserEntity (
    @PrimaryKey
    val id: UUID,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("lastname")
    val lastname: String,
    @ColumnInfo("phone")
    val phone: String
)