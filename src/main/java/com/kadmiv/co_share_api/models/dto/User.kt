package com.kadmiv.co_share_api.models.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet


const val USER_JOIN_NAME = "owner_card_id"

@Entity
@Table(name = "usr")
class User : UserDetails {

    @Id
    @Column(name = "id", length = 16, unique = true, nullable = false)
    var id: UUID? = UUID.randomUUID()

    var userLogin: String? = null
    //    var userEmail: String? = null
    var userPassword: String? = null

    var isActive = false

    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
    @Enumerated(EnumType.STRING)
    var roles: Set<Role>? = null

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "user_cards",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "card_id")]
    )
    val cards: Set<Card> = HashSet()


    override fun getAuthorities(): Collection<GrantedAuthority> {
        return roles as Collection<GrantedAuthority>
    }

    override fun isEnabled(): Boolean {
        return isActive
    }

    override fun getUsername(): String {
        return userLogin!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return userPassword!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

}