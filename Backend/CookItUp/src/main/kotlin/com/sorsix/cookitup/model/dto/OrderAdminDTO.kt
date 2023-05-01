package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.enumeration.OrderStatus
import lombok.Data
@Data
data class OrderAdminDTO(
    val phoneNumber:String?=null,
    val address:String?=null,
    val numPersons:Int?=null,
    val recipeId: Long?=null,
    val orderStatus: OrderStatus?=null,
    )