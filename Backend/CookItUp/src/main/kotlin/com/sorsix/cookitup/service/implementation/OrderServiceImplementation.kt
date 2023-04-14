package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Order
import com.sorsix.cookitup.model.enumeration.OrderStatus
import com.sorsix.cookitup.repository.OrderRepository
import com.sorsix.cookitup.service.OrderService
import org.springframework.stereotype.Service

@Service
class OrderServiceImplementation(private val repository: OrderRepository) : OrderService {
    override fun findAllByOrderStatus(orderStatus: OrderStatus): List<Order> {
        return repository.findAllByOrderStatus(orderStatus)
    }

    override fun findAllByCustomer(customer: Customer): List<Order> {
        return repository.findAllByCustomer(customer)
    }
}