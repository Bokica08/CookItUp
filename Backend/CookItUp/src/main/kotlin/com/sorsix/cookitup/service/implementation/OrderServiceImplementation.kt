package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Order
import com.sorsix.cookitup.model.dto.OrderDTO
import com.sorsix.cookitup.model.enumeration.OrderStatus
import com.sorsix.cookitup.repository.CustomerRepository
import com.sorsix.cookitup.repository.OrderRepository
import com.sorsix.cookitup.repository.RecipeRepository
import com.sorsix.cookitup.service.OrderService
import com.sorsix.cookitup.service.UserService
import org.springframework.stereotype.Service

@Service
class OrderServiceImplementation(private val orderRepository: OrderRepository,
private val userService: UserService, private val recipeRepository: RecipeRepository,
private val customerRepository: CustomerRepository) : OrderService {
    override fun findAllByOrderStatus(orderStatus: OrderStatus): List<Order> {
        return orderRepository.findAllByOrderStatus(orderStatus)
    }

    override fun findAllByCustomer(customer: Customer): List<Order> {
        return orderRepository.findAllByCustomer(customer)
    }

    override fun save(orderDTO: OrderDTO, customer: Customer): Order {
        if (customer.address==null && customer.phoneNumber==null){
            customer.address = orderDTO.address;
            customer.phoneNumber = orderDTO.phoneNumber;
            customerRepository.save(customer);
        }
        val order =Order(null,orderDTO.phoneNumber,orderDTO.address,OrderStatus.Created,orderDTO.numPersons,recipeRepository.getReferenceById(
            orderDTO.recipeId!!
        ),customer,
        userService.getRandomAdmin())
        return orderRepository.save(order)
    }

    override fun getNumberOfOrders(): Long {
        return orderRepository.count()
    }
}