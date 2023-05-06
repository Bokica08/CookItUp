package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.dto.UserStatistic
import com.sorsix.cookitup.repository.CustomerRepository
import com.sorsix.cookitup.repository.OrderRepository
import com.sorsix.cookitup.repository.RecipeRepository
import com.sorsix.cookitup.repository.ReviewRepository
import com.sorsix.cookitup.service.AdminService
import org.springframework.stereotype.Service

@Service
class AdminServiceImplementation(private val customerRepository: CustomerRepository,
private val orderRepository: OrderRepository, private val reviewRepository: ReviewRepository,
private val recipeRepository: RecipeRepository) : AdminService {
    override fun getUsersStatistic(): List<UserStatistic> {
        return customerRepository.findAll().map { it-> UserStatistic(
            it.userId,
            it.username,
            recipeRepository.findAllByCustomer(it).size,reviewRepository.findAllByCustomer(it).size,orderRepository.findAllByCustomer(it).size,it.email,it.role.toString()
        ) }
    }

    override fun getCustomersCreated(): Long {
        return customerRepository.count()
    }

    override fun getOrdersCreated(): Long {
        return orderRepository.count()
    }

    override fun getReviewsCreated(): Long {
        return reviewRepository.count()
    }

    override fun getRecipesCreated(): Long {
        return recipeRepository.count()
    }
}