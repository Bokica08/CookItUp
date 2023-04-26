package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.*
import com.sorsix.cookitup.model.dto.CustomerInfoDTO
import com.sorsix.cookitup.model.dto.RegisterDTO
import com.sorsix.cookitup.model.enumeration.Role
import com.sorsix.cookitup.repository.*
import com.sorsix.cookitup.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImplementation(val pendingAdminRepository: PendingAdminRepository,val adminRepository: AdminRepository,val recipeRepository: RecipeRepository,val customerRepository: CustomerRepository, val userRepository: UserRepository,val passwordEncoder: PasswordEncoder): UserService {
    override fun loadUserByUsername(username: String): User {
        return userRepository.findAllByUsername(username).first()
    }

    override fun register(userDTO: RegisterDTO): User {
        if (userDTO.role==Role.ROLE_USER)
            return customerRepository.save(
                Customer(
                    0L, userDTO.firstName,
                    userDTO.lastName,
                    userDTO.username,
                    userDTO.email,
                    passwordEncoder.encode(userDTO.password),
                    Role.ROLE_USER,
                    null,
                    null
                )
            )
        else {
            return pendingAdminRepository.save(
                PendingAdmin(
                    0L, userDTO.firstName,
                    userDTO.lastName,
                    userDTO.username,
                    userDTO.email,
                    passwordEncoder.encode(userDTO.password),
                    Role.ROLE_PENDING_ADMIN
                )
            )
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

    override fun addToFavorites(username: String, id: Long): Any {
        val customer = customerRepository.getByUsername(username)
        val recipe: Recipe = recipeRepository.findById(id).get()
        customer.recipeList.add(recipe)
        recipe.favoriteList.add(customer)
        customerRepository.save(customer)
        recipeRepository.save(recipe)
        return 1


    }

    override fun deleteFromFavorites(username: String, id: Long): Any {
        val customer = customerRepository.getByUsername(username)
        val recipe: Recipe = recipeRepository.findById(id).get()
        customer.recipeList.remove(recipe)
        recipe.favoriteList.remove(customer)
        customerRepository.save(customer)
        recipeRepository.save(recipe)
        return 1
    }

    override fun findAllByRole(role: Role): List<User> {
        return userRepository.findAllByRole(role)
    }

    override fun findAllPendingAdmins(): List<User?>? {
        return userRepository.findAllByRole(Role.ROLE_PENDING_ADMIN)
    }

    override fun authorizePendingAdmin(username: String?): Optional<User> {
        val user: PendingAdmin = pendingAdminRepository.findByUsername(username)
        if (user.role!=Role.ROLE_PENDING_ADMIN) {
            throw TODO()
        }
        user.role=Role.ROLE_ADMIN
        val admin= Admin(user.userId,user.firstname,user.lastname,user.username,user.email,user.password,user.role)
        pendingAdminRepository.delete(user)
        adminRepository.save(admin)
        return Optional.of(admin)
    }
}