package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.dto.OrderDTO
import com.sorsix.cookitup.service.OrderService
import com.sorsix.cookitup.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/orders")
class OrderController(val orderService: OrderService,val userService: UserService) {

    @PostMapping
    fun saveOrder(@RequestBody orderDto:OrderDTO,request: HttpServletRequest):ResponseEntity<Any>
    {
        val order=orderService.save(orderDto);
        return ResponseEntity.ok(order)
    }
}