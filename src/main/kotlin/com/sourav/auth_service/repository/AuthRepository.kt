package com.sourav.auth_service.repository

import com.sourav.auth_service.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface AuthRepository : MongoRepository<User, ObjectId> {
    fun login(email: String, password: String): User?
    fun findByEmailId(email: String): User?
}