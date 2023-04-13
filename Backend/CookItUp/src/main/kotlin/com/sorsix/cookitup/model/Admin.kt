package com.sorsix.cookitup.model

import com.sorsix.cookitup.model.enumeration.Role
import lombok.Data
import javax.persistence.Entity

@Entity
class Admin(
    id: Long,
    firstname: String,
    lastname: String,
    username: String,
    email: String,
    password: String,
    role: Role
) : User(id, firstname, lastname, username, email, password, role) {
}
