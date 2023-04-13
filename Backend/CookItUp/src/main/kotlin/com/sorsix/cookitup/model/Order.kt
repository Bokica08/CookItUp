package com.sorsix.cookitup.model

import com.sorsix.cookitup.model.enumeration.OrderStatus
import lombok.Data
import javax.persistence.*

@Entity
@Data
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val orderId: Long? = null,
        val phoneNumber:String?=null,
        val address:String?=null,
        val orderStatus: OrderStatus?=null,
        @ManyToOne
        @JoinColumn(name = "recipeId")
        val recipe: Recipe?=null,
        @ManyToOne
        @JoinColumn(name = "customerId")
        val customer: User?=null,
        @ManyToOne
        @JoinColumn(name = "adminId")
        val admin: User?=null,
        ) {
}