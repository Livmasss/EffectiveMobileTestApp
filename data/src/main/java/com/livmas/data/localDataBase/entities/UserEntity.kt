package com.livmas.data.localDataBase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.livmas.domain.models.UserModel
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
) {
    constructor(domainModel: UserModel): this(
        domainModel.id,
        domainModel.name,
        domainModel.lastname,
        domainModel.phone
    )
}