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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) open var userId: Long,
    open val firstname: String ,
    open val lastname: String ,
    private var username: String = "",
    open val email: String,
    private var password: String = "",
    @Enumerated(value = EnumType.STRING) open var role: Role
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