package com.sorsix.cookitup.model

import com.sorsix.cookitup.model.enumeration.Role
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
abstract class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) open var userId: Long? = null,
    open val firstname: String? = null,
    open val lastname: String? = null,
    private var username: String = "",
    open val email: String? = null,
    private var password: String = "",
    @Enumerated(value = EnumType.STRING) open val role: Role? = null
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return Collections.singletonList(role)
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

    override fun getUsername() = username
    override fun getPassword() = password


}