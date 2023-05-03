package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Order
import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.CategoryDTO
import com.sorsix.cookitup.model.dto.IngredientDTO
import com.sorsix.cookitup.model.enumeration.OrderStatus
import com.sorsix.cookitup.repository.CategoryRepository
import com.sorsix.cookitup.repository.IngredientRepository
import com.sorsix.cookitup.service.OrderService
import com.sorsix.cookitup.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/api/admin")
class AdminController(
    val categoryRepository: CategoryRepository,
    val ingredientRepository: IngredientRepository,
    val userService: UserService,
    val orderService: OrderService,
) {
    // Add category to the system
    @PostMapping("/category")
    fun addCategory(@RequestBody categoryDTO: CategoryDTO) : ResponseEntity<Any> {
      return ResponseEntity.ok(categoryRepository.save(Category(null,categoryDTO.name)))
    }
    // Add ingredient to the system
    @PostMapping("/ingredient")
    fun addIngredient(@RequestBody ingredientDTO: IngredientDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(ingredientRepository.save(Ingredient(null,ingredientDTO.name,ingredientDTO.description)))
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

}