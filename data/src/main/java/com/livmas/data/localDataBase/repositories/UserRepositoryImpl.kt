package com.livmas.data.localDataBase.repositories

import com.livmas.data.localDataBase.daos.UserDao
import com.livmas.data.localDataBase.entities.UserEntity
import com.livmas.domain.models.UserModel
import com.livmas.domain.iRepositories.UserRepository
import java.lang.IndexOutOfBoundsException

class UserRepositoryImpl(private val dao: UserDao): UserRepository {

    override fun getAuthedUser(): UserModel? {
        val entity = try {
            dao.getAll()[0]
        } catch (e: IndexOutOfBoundsException) {
            null
        }

        return entity?.run {
            UserModel(id, name, lastname, phone)
        }
    }
    override fun deleteAuthedUser(user: UserModel) = dao.delete(UserEntity(user))

    override fun insertAuthedUser(user: UserModel) = dao.insert(UserEntity(user))
}
