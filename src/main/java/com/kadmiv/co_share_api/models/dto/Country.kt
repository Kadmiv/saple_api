package com.kadmiv.co_share_api.models.dto

import java.util.*
import javax.persistence.*

const val COUNTRY_JOIN_NAME = "country_id"

@Entity
@Table(name = "COUNTRY")
class Country {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) //
//    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
////    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
//    var id: Long = 0

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 16, unique = true, nullable = false)
    var id: UUID = UUID.randomUUID()

    @Column(nullable = false)
    var name = ""
}