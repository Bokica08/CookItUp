package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.dto.CustomerInfoDTO
import com.sorsix.cookitup.model.dto.RecipeInfoDTO
import com.sorsix.cookitup.repository.CustomerRepository
import com.sorsix.cookitup.service.OrderService
import com.sorsix.cookitup.service.RecipeService
import com.sorsix.cookitup.service.ReviewService
import com.sorsix.cookitup.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = ["http://localhost:4200"], allowCredentials = "true", maxAge = 3600)
class CustomerController(val userService: UserService, val reviewService: ReviewService
,val orderService:OrderService, val recipeService: RecipeService, val customerRepository: CustomerRepository) {
    @GetMapping
    fun user(request: HttpServletRequest): Principal? {
        val authToken = request.getHeader("Authorization")
            .substring("Basic".length).trim { it <= ' ' }
        return Principal {
            String(
                Base64.getDecoder()
                    .decode(authToken)
            ).split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().get(0)
        }
    }
    @GetMapping("/info")
    fun getCustomerInfo(request:HttpServletRequest): ResponseEntity<CustomerInfoDTO> {
        println(SecurityContextHolder.getContext().authentication.name)
        return ResponseEntity.ok(this.userService.findByUsername(request.remoteUser))
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
    @GetMapping("/addFavorite/{id}")
    fun addToFavorite(@PathVariable id:Long,request: HttpServletRequest):ResponseEntity<Any>
    {
        // treba da se odi preku userService za da mozhe da se zacchuvaat promenite u baza
        // userService.addToVisited(request.remoteUser, id)
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        customer.recipeList.add(recipeService.getRecipeById(id))
        return ResponseEntity.ok(customer)
    }
    @GetMapping("/customerCount")
    fun getCustomerCount() : ResponseEntity<Any>{
        return ResponseEntity.ok(customerRepository.count())
    }


}