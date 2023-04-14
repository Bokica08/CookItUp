package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Order
import com.sorsix.cookitup.model.enumeration.OrderStatus

interface OrderService {
    fun findAllByOrderStatus(orderStatus: OrderStatus) : List<Order>
    fun findAllByCustomer(customer: Customer) : List<Order>
}