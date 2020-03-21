package com.kadmiv.co_share_api.models.dto

import javax.persistence.*

const val COUNTRY_JOIN_NAME = "country_id"

@Entity
@Table(name = "COUNTRY")
class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    var id: Long = 0

    @Column(nullable = false)
    var name = ""
}