package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Order
import com.sorsix.cookitup.model.dto.OrderDTO
import com.sorsix.cookitup.model.dto.OrderPreviewDTO
import com.sorsix.cookitup.model.enumeration.OrderStatus
import java.util.*

interface OrderService {
    fun findAllByOrderStatus(orderStatus: OrderStatus) : List<Order>
    fun findAllByCustomer(customer: Customer) : List<OrderPreviewDTO>
    fun save(orderDTO: OrderDTO, customer: Customer) : Order
    fun getNumberOfOrders() : Long
    fun getOrderPreview(id:Long): OrderPreviewDTO
    fun getOrdersByAdmin(username:String):List<Order>
    fun getOrder(id:Long): Order
    fun changeStatus(order: Order,status: OrderStatus):Order

}