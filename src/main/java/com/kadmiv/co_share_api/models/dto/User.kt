package com.kadmiv.co_share_api.models.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


const val USER_JOIN_NAME = "author_card_id"

@Entity
@Table(name = "usr")
class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var userLogin: String? = null
    //    var userEmail: String? = null
    var userPassword: String? = null

    var isActive = false

    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
    @Enumerated(EnumType.STRING)
    var roles: Set<Role>? = null

//    @ManyToOne
//    @JoinColumn(name = "card_id", nullable = true)
//    var card: Card? = Card()

//    @OneToMany(mappedBy = "author",cascade= [CascadeType.ALL])
//    var cards: Set<Card>? = null

//    @ManyToMany(cascade= [CascadeType.ALL])
//    var cards: Set<Card>? = null


//    @ManyToMany
//    @JoinTable(
//            name = "user_card",
//            joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
//            inverseJoinColumns = [JoinColumn(name = "card_id", referencedColumnName = "id")]
//    )
//  private val cards: List<Card>? = null

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "user_cards",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "card_id")]
    )
    private val cards: Set<Card> = HashSet()


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