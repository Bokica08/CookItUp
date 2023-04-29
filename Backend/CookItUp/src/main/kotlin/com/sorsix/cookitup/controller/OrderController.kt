package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.dto.OrderDTO
import com.sorsix.cookitup.repository.CustomerRepository
import com.sorsix.cookitup.service.OrderService
import com.sorsix.cookitup.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = ["http://localhost:4200"], allowCredentials = "true", maxAge = 3600)
class OrderController(val orderService: OrderService, val userService: UserService, ) {

    @PostMapping
    fun saveOrder(@RequestBody orderDto:OrderDTO,request: HttpServletRequest):ResponseEntity<Any>
    {
        val customer: Customer = userService.getCustomerByUsername(request.remoteUser)
        val order=orderService.save(orderDto,customer)
        return ResponseEntity.ok(order)
    }
    @GetMapping("/ordersCount")
    fun getOrdersCount():ResponseEntity<Any>{
        return ResponseEntity.ok(orderService.getNumberOfOrders())
    }
}