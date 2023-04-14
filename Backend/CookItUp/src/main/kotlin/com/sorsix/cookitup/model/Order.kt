package com.sorsix.cookitup.model

import com.sorsix.cookitup.model.enumeration.OrderStatus
import lombok.Data
import javax.persistence.*

@Entity
@Data
@Table(name="orders")
class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val orderId: Long? = null,
        val phoneNumber:String?=null,
        val address:String?=null,
        @Enumerated(value = EnumType.STRING)
        val orderStatus: OrderStatus?=null,
        val numPersons:Int?=null,
        @ManyToOne
        @JoinColumn(name = "recipeId")
        val recipe: Recipe?=null,
        @ManyToOne
        @JoinColumn(name = "customerId")
        val customer: Customer?=null,
        @ManyToOne
        @JoinColumn(name = "adminId")
        val admin: Admin?=null,
        ) {
}