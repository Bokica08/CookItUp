package com.sorsix.cookitup.model

import com.sorsix.cookitup.model.enumeration.Role
import lombok.Data
import javax.persistence.Entity

@Entity
@Data
class Admin(
    id: Long,
    firstname: String,
    lastname: String,
    username: String,
    email: String,
    password: String,
    role: Role
) : User(id, firstname, lastname, username, email, password, role) {
    override fun getUsername(): String {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }
    override fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

}