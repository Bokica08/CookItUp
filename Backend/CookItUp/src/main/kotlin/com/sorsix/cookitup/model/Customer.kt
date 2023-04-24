package com.sorsix.cookitup.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.sorsix.cookitup.model.enumeration.Role
import lombok.Data
import javax.persistence.Entity
import javax.persistence.ManyToMany

@Entity
@Data
class Customer(
    userId: Long, firstname: String, lastname: String, username: String, email: String, password: String, role: Role,
    var phoneNumber: String?, var address: String?,
    @ManyToMany(mappedBy = "favoriteList")
    @JsonBackReference
    val recipeList: MutableList<Recipe> = arrayListOf()

) : User(userId, firstname, lastname, username, email, password, role,
    )