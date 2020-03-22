package com.kadmiv.co_share_api.models.dto

import java.util.*
import javax.persistence.*


const val CATEGORY_CARD_JOIN_NAME = "category_card_id"

@Entity
@Table(name = "CATEGORY")
class Category {

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO) //
//    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
////    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
//    var id: Long = 0

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 16, unique = true, nullable = false)
    var id: UUID = UUID.randomUUID()

    var name = ""
    var description = ""

//    @OneToMany
//    @JoinColumn(name = CATEGORY_CARD_JOIN_NAME) // we need to duplicate the physical information
//    private val cards: Collection<Card>? = null

}