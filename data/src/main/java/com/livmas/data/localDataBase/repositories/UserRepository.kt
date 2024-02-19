package com.livmas.data.localDataBase.repositories

import com.livmas.data.localDataBase.daos.UserDao
import com.livmas.data.localDataBase.entities.UserEntity
import java.lang.IndexOutOfBoundsException

class UserRepository(private val dao: UserDao) {

    fun getAuthedUser(): UserEntity? = try {
            dao.getAll()[0]
        } catch (e: IndexOutOfBoundsException) {
            null
        }

    fun deleteAuthedUser(user: UserEntity) = dao.delete(user)

    fun insertAuthedUser(user: UserEntity) = dao.insert(user)
}