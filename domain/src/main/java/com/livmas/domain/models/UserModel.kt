package com.livmas.domain.models

import java.util.UUID

data class UserModel (
    val id: UUID,
    val name: String,
    val lastname: String,
    val phone: String
    ) {
}