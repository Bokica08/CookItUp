package com.sorsix.cookitup.model

import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import com.sorsix.cookitup.model.enumeration.Role
import java.util.*


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
abstract class User : UserDetails {
        constructor(userId: Long, firstname: String, lastname: String, username: String, email: String, password: String, role: Role)

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val userId: Long? = null
        val firstname: String? = null
        val lastname: String? = null
        var username: String = ""
        val email: String? = null
        var password: String = ""
        @Enumerated(value = EnumType.STRING)
        val role: Role? = null
        override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                val authorities = mutableListOf<GrantedAuthority>()
                if (role != null) {
                        authorities.add(role)
                }
                return authorities
        }

        override fun isAccountNonExpired(): Boolean {
                return true
        }

        override fun isAccountNonLocked(): Boolean {
                return true
        }

        override fun isCredentialsNonExpired(): Boolean {
                return true
        }

        override fun isEnabled(): Boolean {
                return true
        }
}