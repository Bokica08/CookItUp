package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.*
import com.sorsix.cookitup.model.dto.*
import com.sorsix.cookitup.model.enumeration.OrderStatus
import com.sorsix.cookitup.repository.CustomerRepository
import com.sorsix.cookitup.repository.ImageRepository
import com.sorsix.cookitup.repository.OrderRepository
import com.sorsix.cookitup.repository.RecipeRepository
import com.sorsix.cookitup.service.OrderService
import com.sorsix.cookitup.service.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderServiceImplementation( val orderRepository: OrderRepository,
val userService: UserService,val recipeRepository: RecipeRepository, val imageRepository: ImageRepository,
val customerRepository: CustomerRepository) : OrderService {
    override fun findAllByOrderStatus(orderStatus: OrderStatus): List<Order> {
        return orderRepository.findAllByOrderStatus(orderStatus)
    }

    override fun findAllByCustomer(customer: Customer): List<OrderPreviewDTO> {
        return orderRepository.findAllByCustomer(customer).map {
            this.getOrderPreview(it.orderId!!)
        }
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

    override fun save(orderDTO: OrderDTO): Order {
        val order =Order(null,orderDTO.phoneNumber,orderDTO.address,OrderStatus.Created,orderDTO.numPersons,recipeRepository.getReferenceById(
            orderDTO.recipeId!!
        ),null,userService.getRandomAdmin())
        return orderRepository.save(order)
    }

    override fun getNumberOfOrders(): Long {
        return orderRepository.count()
    }

    override fun getOrderPreview(id: Long): OrderPreviewDTO {
        val order:Order = orderRepository.getReferenceById(id);
        val recipe:Recipe = recipeRepository.getReferenceById(order.recipe!!.recipeId!!)
        val imageList: List<Image> = imageRepository.getAllByRecipe(recipe)
        val recipePreview =  RecipePreviewDTO(
            recipe.recipeId,
            recipe.name,
            recipe.numPersons,
            recipe.difficultyLevel,
            recipe.prepTime,
            recipe.avRating,
            recipe.viewCount,
            recipe.createdOn!!.toLocalDate(),
            recipe.customer!!.username,
            imageList
        )
        return OrderPreviewDTO(
            order.orderId!!,
            order.phoneNumber,
            order.address,
            order.numPersons,
            recipePreview,
            order.orderStatus.toString()
        )
    }
    override fun getOrdersByAdmin(username: String): List<Order> {
        return orderRepository.findAllByAdminUsername(username)
    }

    override fun getOrder(id: Long): Order {
        return orderRepository.getReferenceById(id)

    }

    override fun changeStatus(order: Order,status: OrderStatus): Order {
        order.orderStatus=status
        orderRepository.save(order)
        return order
    }

    override fun deleteByRecipe(recipe: Recipe): Any {
        val orders:List<Order> = orderRepository.findAllByRecipe(recipe)
        return orderRepository.deleteAll(orders)
    }

    override fun getOrdersByStatusAndUser(status: OrderStatus, customer: Customer):List<OrderPreviewDTO> {
        return orderRepository.findAllByOrderStatusAndCustomer(status, customer)
                .map { getOrderPreview(it.orderId!!) }
    }
}