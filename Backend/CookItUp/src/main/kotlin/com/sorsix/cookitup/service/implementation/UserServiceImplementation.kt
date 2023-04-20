package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Admin
import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.CustomerInfoDTO
import com.sorsix.cookitup.model.dto.RegisterDTO
import com.sorsix.cookitup.model.enumeration.Role
import com.sorsix.cookitup.repository.AdminRepository
import com.sorsix.cookitup.repository.CustomerRepository
import com.sorsix.cookitup.repository.RecipeRepository
import com.sorsix.cookitup.repository.UserRepository
import com.sorsix.cookitup.service.UserService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional


@Service
class UserServiceImplementation(val adminRepository: AdminRepository,val recipeRepository: RecipeRepository,val customerRepository: CustomerRepository, val userRepository: UserRepository,val passwordEncoder: PasswordEncoder): UserService {
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
            return userRepository.save(
                Customer(
                    0L, userDTO.firstName,
                    userDTO.lastName,
                    userDTO.username,
                    userDTO.email,
                    passwordEncoder.encode(userDTO.password),
                    Role.ROLE_PENDING_ADMIN,
                    null,
                    null
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
        return 1;


    }

    override fun findAllByRole(role: Role): List<User> {
        return userRepository.findAllByRole(role)
    }

    override fun findAllPendingAdmins(): List<User?>? {
        return userRepository.findAllByRole(Role.ROLE_PENDING_ADMIN)
    }

    override fun authorizePendingAdmin(username: String?): Optional<User> {
        val user: User = userRepository.findByUsername(username)
        if (user.role!=Role.ROLE_PENDING_ADMIN) {
            throw TODO()
        }
        user.role=Role.ROLE_ADMIN
        userRepository.save(user)
        //tuka frla error
        //pending_admin e zapisan kako customer i treba da se trgne od tabelata customer i da se
        // stai u admin tabelata ali ima constrains i frla greski
        return Optional.of(adminRepository.save(user))
    }
}