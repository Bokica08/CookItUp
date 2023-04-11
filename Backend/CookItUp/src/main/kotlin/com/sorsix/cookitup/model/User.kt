package com.sorsix.cookitup.model

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
abstract class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val userid: Long? = null,
        val firstname: String? = null,
        val lastname: String? = null,
        val username: String? = null,
        val email: String? = null,
        val password: String? = null


) {
}