package com.kadmiv.co_share_api.models.dto

import javax.persistence.*


const val COMPANY_CARD_JOIN_NAME = "company_card_id"

@Entity
@Table(name = "COMPANY")
class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO) //
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    var id: Long = 0

    @Column(nullable = false)
    var name: String? = null

    //    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COUNTRY_JOIN_NAME)
    var country: Country? = null

//    @OneToMany
//    @JoinColumn(name = COMPANY_CARD_JOIN_NAME) // we need to duplicate the physical information
//    private val cards: Collection<Card>? = null


}