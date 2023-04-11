package com.sorsix.cookitup.model

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val orderId: Long? = null,
        val phoneNumber:String?=null,
        val address:String?=null,
        val orderStatus: OrderStatus?=null,

) {
}