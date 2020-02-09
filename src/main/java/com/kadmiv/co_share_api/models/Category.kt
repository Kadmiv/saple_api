package com.kadmiv.co_share_api.models

import javax.persistence.*

@Entity
@Table(name = "categories")
class Category {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
//    @Column(name = "id")
    var name = ""
    var description = ""
}