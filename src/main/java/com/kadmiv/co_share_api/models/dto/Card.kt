package com.kadmiv.co_share_api.models.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet


@Entity
@Table(name = "CARD")
class Card {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) //
//    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
////    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
//    var id: Long = 0

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 16, unique = true, nullable = false)
    var id: UUID = UUID.randomUUID()

    //    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CATEGORY_CARD_JOIN_NAME)
    var category: Category? = null

    //    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COMPANY_CARD_JOIN_NAME)
    var company: Company? = null

    //    @Column(nullable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_JOIN_NAME)
    var owner: User? = null

//    @OneToMany(mappedBy = "card",cascade= [CascadeType.ALL])
//    var users: Set<User>? = null

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE],
            mappedBy = "cards")
    private val users: Set<User> = HashSet()

//    @ManyToMany
//    var users: Set<User>? = null


    @Column(nullable = false)
    var name = ""

    @Column(nullable = false)
    var code = ""

    @JsonIgnore
    var bonusPercent = 0.0

    @JsonIgnore
    var rating = 0.0
    @JsonIgnore
    var followersCount = 0

    @JsonIgnore
    var isActive = true

    @JsonIgnore
    var mayUseForShare = false


//    @ManyToOne(fetch = FetchType.LAZY)
//    var categories: Category? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    var сompany: Сompany? = null

//    @ManyToOne
//    @JoinColumn(name = "company_id")
//    var company: Сompany? = null

//    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
//    val categories: List<Comments>? = null

}