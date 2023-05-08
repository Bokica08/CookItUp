package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Order
import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.CategoryDTO
import com.sorsix.cookitup.model.dto.IngredientDTO
import com.sorsix.cookitup.model.enumeration.OrderStatus
import com.sorsix.cookitup.service.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/api/admin")
class AdminController(
    val categoryService: CategoryService,
    val ingredientService:IngredientService,
    val userService: UserService,
    val orderService: OrderService,
    val adminService: AdminService
) {
    @PostMapping("/category")
    fun addCategory(@RequestBody categoryDTO: CategoryDTO) : ResponseEntity<Category> {
      return ResponseEntity.ok(categoryService.save(categoryDTO))
    }
    @PostMapping("/ingredient")
    fun addIngredient(@RequestBody ingredientDTO: IngredientDTO) : ResponseEntity<Ingredient> {
        return ResponseEntity.ok(ingredientService.save(ingredientDTO))
    }

    @GetMapping("/pending")
    fun findPendingAdmins(): List<User?>? {
        return userService.findAllPendingAdmins()
    }

    @GetMapping("/pending/authorizeAdmin")
    fun authorizeAdmin(@RequestParam username: String?): ResponseEntity<User?>? {
        return userService.authorizePendingAdmin(username)
            .map { user -> ResponseEntity.ok().body(user) }
            .orElseGet { ResponseEntity.badRequest().build() }
    }
    @GetMapping("/orders")
    fun getAllOrders(request:HttpServletRequest):ResponseEntity<List<Order>>
    {
        return ResponseEntity.ok(orderService.getOrdersByAdmin(request.remoteUser))
    }
    @GetMapping("/changeStatus/{id}")
    fun changeStatus(@PathVariable id:Long):ResponseEntity<Order>
    {
        val order=orderService.getOrder(id)
        orderService.changeStatus(order,OrderStatus.Processing)
        return ResponseEntity.ok(order)
    }
    @GetMapping("/users")
    fun getUsersStatistic(request:HttpServletRequest):ResponseEntity<Any>
    {
        return ResponseEntity.ok(adminService.getUsersStatistic())
    }
    @GetMapping("/recipesCreated")
    fun getRecipesCreated(request:HttpServletRequest):ResponseEntity<Any>
    {
        return ResponseEntity.ok(adminService.getRecipesCreated())
    }
    @GetMapping("/customersCreated")
    fun getCustomersCreated(request:HttpServletRequest):ResponseEntity<Long>
    {
        return ResponseEntity.ok(adminService.getCustomersCreated())
    }
    @GetMapping("/ordersCreated")
    fun getOrdersCreated(request:HttpServletRequest):ResponseEntity<Long>
    {
        return ResponseEntity.ok(adminService.getOrdersCreated())
    }
    @GetMapping("/reviewsCreated")
    fun getReviewsCreated(request:HttpServletRequest):ResponseEntity<Long>
    {
        return ResponseEntity.ok(adminService.getReviewsCreated())
    }
}