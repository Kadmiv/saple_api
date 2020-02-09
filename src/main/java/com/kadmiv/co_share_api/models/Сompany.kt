package com.kadmiv.co_share_api.models

import javax.persistence.*


@Entity
@Table(name = "company")
class Сompany {
    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.AUTO) //
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    var id: Long = 0
    var name: String? = null

//    @ManyToOne(fetch = FetchType.LAZY)
//    var categories: Category? = null

//    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL])
//    var cards: Set<Card>? = null
}