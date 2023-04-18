package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.dto.CustomerInfoDTO
import com.sorsix.cookitup.model.dto.RecipeInfoDTO
import com.sorsix.cookitup.service.OrderService
import com.sorsix.cookitup.service.RecipeService
import com.sorsix.cookitup.service.ReviewService
import com.sorsix.cookitup.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/customer")
class CustomerController(val userService: UserService, val reviewService: ReviewService
,val orderService:OrderService, val recipeService: RecipeService) {
    @GetMapping("/info")
    fun getCustomerInfo(request:HttpServletRequest): ResponseEntity<CustomerInfoDTO> {
        return ResponseEntity.ok(this.userService.findByUsername(SecurityContextHolder.getContext().authentication.name))
    }
    @GetMapping("/myReviews")
    fun getCustomerReviews(request: HttpServletRequest):ResponseEntity<Any>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        return ResponseEntity.ok(reviewService.findAllByCustomer(customer))

    }
    @GetMapping("/myOrders")
    fun getCustomerOrders(request: HttpServletRequest):ResponseEntity<Any>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        return ResponseEntity.ok(orderService.findAllByCustomer(customer))
    }
    @GetMapping("/myFavorites")
    fun getCustomerFavorites(request:HttpServletRequest):ResponseEntity<Any>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        val recipesForCustomer:List<RecipeInfoDTO> =customer.recipeList.map { r->recipeService.getDetailsForRecipe(r.recipeId!!) }
        return ResponseEntity.ok(recipesForCustomer)
    }
    @PostMapping("/addFavorite/{id}")
    fun addToFavorite(@PathVariable id:Long,request: HttpServletRequest):ResponseEntity<Any>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        customer.recipeList.add(recipeService.getRecipeById(id))
        return ResponseEntity.ok(customer)
    }


}