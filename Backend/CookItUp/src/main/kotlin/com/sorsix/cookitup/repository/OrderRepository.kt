package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Order
import com.sorsix.cookitup.model.dto.OrderAdminDTO
import com.sorsix.cookitup.model.dto.OrderPreviewDTO
import com.sorsix.cookitup.model.enumeration.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findAllByOrderStatus(orderStatus: OrderStatus) : List<Order>
    fun findAllByCustomer(customer: Customer) : List<Order>
    fun save(order:Order):Order
    fun findAllByAdminUsername(username:String):List<Order>

}