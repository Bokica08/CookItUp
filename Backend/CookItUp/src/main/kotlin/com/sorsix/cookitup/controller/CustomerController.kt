package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.dto.CustomerInfoDTO
import com.sorsix.cookitup.service.OrderService
import com.sorsix.cookitup.service.ReviewService
import com.sorsix.cookitup.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/customer")
class CustomerController(val userService: UserService, val reviewService: ReviewService
,val orderService:OrderService) {
    @GetMapping("/info")
    fun getCustomerInfo(request:HttpServletRequest): ResponseEntity<CustomerInfoDTO> {
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
}