package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.dto.OrderDTO
import com.sorsix.cookitup.model.enumeration.OrderStatus
import com.sorsix.cookitup.model.enumeration.Role
import com.sorsix.cookitup.service.OrderService
import com.sorsix.cookitup.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = ["http://localhost:4200"], allowCredentials = "true", maxAge = 3600)
class OrderController(val orderService: OrderService, val userService: UserService) {

    @PostMapping
    fun saveOrder(@RequestBody orderDto:OrderDTO,request: HttpServletRequest):ResponseEntity<Any>
    {
        return if(request.remoteUser!=null) {
            if(userService.getUserByUsername(request.remoteUser).role==Role.ROLE_USER) {
                val customer: Customer = userService.getCustomerByUsername(request.remoteUser)
                ResponseEntity.ok(orderService.save(orderDto, customer))
            }
            else{
                ResponseEntity.ok(orderService.save(orderDto))
            }
        } else{
            ResponseEntity.ok(orderService.save(orderDto))
        }
    }
    @GetMapping("/ordersCount")
    fun getOrdersCount():ResponseEntity<Any>{
        return ResponseEntity.ok(orderService.getNumberOfOrders())
    }
    @GetMapping("/searchByStatus")
    fun searchByStatus(@RequestParam(required = false) status: OrderStatus, request: HttpServletRequest):ResponseEntity<Any>{
        return ResponseEntity.ok(orderService.getOrdersByStatusAndUser(status, userService.getCustomerByUsername(request.remoteUser)))
    }
}