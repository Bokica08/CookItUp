package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.repository.CustomerRepository
import com.sorsix.cookitup.service.CustomerService
import org.springframework.stereotype.Service

@Service
class CustomerServiceImplementation(val customerRepository: CustomerRepository) :CustomerService{
    override fun count(): Long {
        return customerRepository.count()
    }

}