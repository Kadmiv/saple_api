package com.kadmiv.co_share_api.models

import javax.persistence.*


@Entity
@Table()
class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    var id: Long = 0

    var name = ""
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