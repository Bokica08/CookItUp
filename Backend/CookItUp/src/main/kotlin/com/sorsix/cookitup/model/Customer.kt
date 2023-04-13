package com.sorsix.cookitup.model

import com.sorsix.cookitup.model.enumeration.Role
import lombok.Data
import javax.persistence.Entity

@Entity
@Data
class Customer(
    userId: Long, firstname: String, lastname: String, username: String, email: String, password: String, role: Role,
    var phoneNumber: String, var address: String
) : User(userId, firstname, lastname, username, email, password, role) {
    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

}