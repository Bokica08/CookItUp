package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Admin
import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.CustomerInfoDTO
import com.sorsix.cookitup.model.dto.RegisterDTO
import com.sorsix.cookitup.model.dto.UserInfoDTO
import com.sorsix.cookitup.model.enumeration.Role
import com.sorsix.cookitup.repository.CustomerRepository
import com.sorsix.cookitup.repository.UserRepository
import com.sorsix.cookitup.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImplementation(val customerRepository: CustomerRepository, val userRepository: UserRepository,val passwordEncoder: PasswordEncoder): UserService {
    override fun loadUserByUsername(username: String): User {
        return userRepository.findAllByUsername(username).first()
    }

    override fun register(userDTO: RegisterDTO): User {
        if (userDTO.address!=null && userDTO.phoneNumber!=null)
            return customerRepository.save(Customer(
                0L,userDTO.firstName,
                userDTO.lastName,
                userDTO.username,
                userDTO.email,
                passwordEncoder.encode(userDTO.password),
                Role.ROLE_USER,
                userDTO.phoneNumber!!,
                userDTO.address!!
            ))
        else{
            return userRepository.save(Admin(
                0L,userDTO.firstName,
                userDTO.lastName,
                userDTO.username,
                userDTO.email,
                passwordEncoder.encode(userDTO.password),
                Role.ROLE_ADMIN
            ))
        }
    }

    override fun getUser(username: String, password: String): User {
        TODO("Not yet implemented")
    }

    override fun getCustomerByUsername(username: String): Customer {
        return customerRepository.getByUsername(username)
    }

    override fun findByUsername(username: String): CustomerInfoDTO {
        return customerRepository.findByUsername(username)
    }

}