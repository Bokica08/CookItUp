package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.Admin
import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.enumeration.OrderStatus
import lombok.Data

@Data
data class OrderDTO(
    val phoneNumber:String?=null,
    val address:String?=null,
    val numPersons:Int?=null,
    val recipeId: Long?=null,
)