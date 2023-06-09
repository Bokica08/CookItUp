package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Order
import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.*
import com.sorsix.cookitup.model.enumeration.OrderStatus
import com.sorsix.cookitup.service.*
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = ["http://localhost:4200"], allowCredentials = "true", maxAge = 3600)
class CustomerController(val userService: UserService,
                         val reviewService: ReviewService,
                         val orderService:OrderService,
                         val recipeService: RecipeService,
                         val customerService: CustomerService) {
    @GetMapping
    fun user(request: HttpServletRequest): Principal? {
        val authToken = request.getHeader("Authorization")
            .substring("Basic".length).trim { it <= ' ' }
        return Principal {
            String(
                Base64.getDecoder()
                    .decode(authToken)
            ).split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        }
    }
    @GetMapping("/info")
    fun getCustomerInfo(request:HttpServletRequest): ResponseEntity<CustomerInfoDTO> {
        return ResponseEntity.ok(this.userService.findByUsername(request.remoteUser))
    }
    @GetMapping("/myReviews")
    fun getCustomerReviews(request: HttpServletRequest):ResponseEntity<List<ReviewPreviewDTO>>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        return ResponseEntity.ok(reviewService.findAllByCustomer(customer))

    }
    @GetMapping("/myOrders")
    fun getCustomerOrders(request: HttpServletRequest):ResponseEntity<List<OrderPreviewDTO>>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        return ResponseEntity.ok(orderService.findAllByCustomer(customer))
    }
    @GetMapping("/myFavorites")
    fun getCustomerFavorites(request:HttpServletRequest):ResponseEntity<List<RecipeInfoDTO>>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        val recipesForCustomer:List<RecipeInfoDTO> =customer.recipeList.map { r->recipeService.getDetailsForRecipe(r.recipeId!!) }
        return ResponseEntity.ok(recipesForCustomer)
    }
    @GetMapping("/myRecipes")
    fun getCustomerRecipes(request:HttpServletRequest):ResponseEntity<List<RecipePreviewDTO>>
    {

        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        val recipes = recipeService.findAllByCustomer(customer)
        return ResponseEntity.ok(recipes)
    }
    @GetMapping("/addFavorite/{id}")
    fun addToFavorite(@PathVariable id:Long,request: HttpServletRequest):ResponseEntity<Customer>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        userService.addToFavorites(request.remoteUser,id)
        return ResponseEntity.ok(customer)
    }
    @DeleteMapping("/deleteFavorite/{id}")
    fun deleteFromFavorite(@PathVariable id:Long,request: HttpServletRequest):ResponseEntity<Customer>
    {
        val customer=userService.getCustomerByUsername(request.remoteUser)
        userService.deleteFromFavorites(request.remoteUser,id)
        return ResponseEntity.ok(customer)

    }
    @GetMapping("/customersCount")
    fun getCustomerCount() : ResponseEntity<Long>{
        return ResponseEntity.ok(customerService.count())
    }
    @GetMapping("/myReviewsPreview")
    fun getCustomerReviewsPreview(request: HttpServletRequest):ResponseEntity<List<ReviewPreviewDTO>>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        val recipes = reviewService.findAllByCustomer(customer)
        return if (recipes.size<=5){
            ResponseEntity.ok(recipes)
        } else{
            ResponseEntity.ok(recipes.subList(0,5))
        }

    }
    @GetMapping("/myOrdersPreview")
    fun getCustomerOrdersPreview(request: HttpServletRequest):ResponseEntity<List<OrderPreviewDTO>>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        val orders = orderService.findAllByCustomer(customer)
        return if (orders.size<=5){
            ResponseEntity.ok(orders)
        } else{
            ResponseEntity.ok(orders.subList(0,5))
        }
    }
    @GetMapping("/myFavoritesPreview")
    fun getCustomerFavoritesPreview(request:HttpServletRequest):ResponseEntity<List<RecipePreviewDTO>>
    {
        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        val recipesForCustomer:List<RecipePreviewDTO> =customer.recipeList.map { r->recipeService.getPreviewForRecipe(r.recipeId!!) }
        return if (recipesForCustomer.size<=5){
            ResponseEntity.ok(recipesForCustomer)
        } else{
            ResponseEntity.ok(recipesForCustomer.subList(0,5))
        }
    }
    @GetMapping("/myRecipesPreview")
    fun getCustomerRecipesPreview(request:HttpServletRequest):ResponseEntity<List<RecipePreviewDTO>>
    {

        val customer: Customer =userService.getCustomerByUsername(request.remoteUser)
        val recipes = recipeService.findAllByCustomer(customer)
        return if (recipes.size<=5){
            ResponseEntity.ok(recipes)
        } else{
            ResponseEntity.ok(recipes.subList(0,5))
        }
    }
    @GetMapping("/getCustomerPhoneNumberAndAddress")
    fun getCustomerPhoneNumberAndAddress(request: HttpServletRequest):ResponseEntity<CustomerInfoDTO>{
        return ResponseEntity.ok(userService.findByUsername(request.remoteUser));
    }
    @GetMapping("/changeStatusToCanceled/{id}")
    fun changeStatusToCanceled(@PathVariable id:Long):ResponseEntity<Order>
    {
        val order=orderService.getOrder(id)
        orderService.changeStatus(order, OrderStatus.Canceled)
        return ResponseEntity.ok(order)
    }
    @GetMapping("/changeStatusToFinished/{id}")
    fun changeStatusToFinished(@PathVariable id:Long):ResponseEntity<Order>
    {
        val order=orderService.getOrder(id)
        orderService.changeStatus(order, OrderStatus.Finished)
        return ResponseEntity.ok(order)
    }
    @GetMapping("/getUserByUsername")
    fun getUserByUsername(@RequestParam username:String):ResponseEntity<User>{
        return ResponseEntity.ok(userService.getUserByUsername(username))
    }
    @GetMapping("/userRecipesPreview")
    fun getUserRecipesPreview(@RequestParam username:String):ResponseEntity<List<RecipePreviewDTO>>
    {
        val customer: Customer =userService.getCustomerByUsername(username)
        val recipes = recipeService.findAllByCustomer(customer)
        return if (recipes.size<=5){
            ResponseEntity.ok(recipes)
        } else{
            ResponseEntity.ok(recipes.subList(0,5))
        }
    }


}