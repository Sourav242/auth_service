package com.sourav.auth_service.controller

import com.sourav.auth_service.model.User
import com.sourav.auth_service.repository.AuthRepository
import com.sourav.auth_service.utils.Constants.DEMO_OWNER_ID
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authRepository: AuthRepository
) {
    data class UserRequest(
        val email: String,
        val password: String,
        val phoneNumber: String = ""
    )

    @GetMapping("/login")
    fun login(
        userRequest: UserRequest
    ): User? {
        val user = authRepository.findByEmailId(userRequest.email)
            ?: throw IllegalArgumentException("User not found")
        return if (user.password == userRequest.password) {
            user
        } else {
            throw IllegalArgumentException("Password miss match")
        }
    }

    @PostMapping("/register")
    fun register(
        userRequest: UserRequest
    ): User {
        val existingUser = authRepository.findByEmailId(userRequest.email)
        if (existingUser != null) {
            throw IllegalArgumentException("User already exists")
        }
        val newUser = User(
            id = ObjectId.get(),
            email = userRequest.email,
            password = userRequest.password,
            phoneNumber = userRequest.phoneNumber
        )
        return authRepository.save(newUser)
    }

    @GetMapping("/login")
    fun details(): User? {
        val ownerId = ObjectId(DEMO_OWNER_ID) // Placeholder ownerId
        val user = authRepository.findById(ownerId)
        return if (user.isPresent) {
            user.get()
        } else {
            null
        }
    }
}