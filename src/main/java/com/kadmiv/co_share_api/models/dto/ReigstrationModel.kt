package com.kadmiv.co_share_api.models.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


class RegistrationModel {

    var userLogin = ""
    //    var userEmail: String? = null
    var userPassword = ""
    var userConfirmPassword = ""


}