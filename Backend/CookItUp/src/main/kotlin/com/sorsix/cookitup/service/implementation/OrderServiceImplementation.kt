package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Order
import com.sorsix.cookitup.model.dto.OrderDTO
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

    override fun save(orderDTO: OrderDTO): Order {
        val order:Order=Order(null,orderDTO.phoneNumber,orderDTO.address,orderDTO.orderStatus,orderDTO.numPersons,orderDTO.recipe,orderDTO.customer,orderDTO.admin)
        return repository.save(order)
    }
}