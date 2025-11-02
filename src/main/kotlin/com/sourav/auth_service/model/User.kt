package com.sourav.auth_service.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.Email
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(
    @Id
    val id: ObjectId,
    @field:Email
    val email: String,
    @JsonIgnore
    val password: String,
    val phoneNumber: String
)
