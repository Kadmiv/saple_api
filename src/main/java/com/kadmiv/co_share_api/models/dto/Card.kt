package com.kadmiv.co_share_api.models.dto

import javax.persistence.*


@Entity
@Table(name = "CARD")
class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    var id: Long = 0

    //    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CATEGORY_CARD_JOIN_NAME)
    var category: Category? = null

    //    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COMPANY_CARD_JOIN_NAME)
    var company: CardСompany? = null

    @Column(nullable = false)
    var name = ""

    @Column(nullable = false)
    var code = ""

    var bonusPercent = 0.0
    var rating = 0.0
    var followersCount = 0

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