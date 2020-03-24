package com.kadmiv.co_share_api.config.secure

import com.google.gson.Gson
import com.kadmiv.co_share_api.models.base.ServerAnswerBuilder
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class AuthenticationEntryPointImpl : BasicAuthenticationEntryPoint() {
    @Throws(IOException::class, ServletException::class)
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authEx: AuthenticationException) {
        response.addHeader("WWW-Authenticate", "Basic realm=$realmName")
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val writer = response.writer
        writer.println(Gson().toJson(
                ServerAnswerBuilder()
                        .setMessage(HttpStatus.UNAUTHORIZED.reasonPhrase)
                        .setError(HttpStatus.UNAUTHORIZED.reasonPhrase)
                        .setStatus(HttpStatus.UNAUTHORIZED.value())
                        .build()
        ))
    }

    @Throws(Exception::class)
    override fun afterPropertiesSet() { // RealmName appears in the login window (Firefox).
        realmName = "coshare"
        super.afterPropertiesSet()
    }
}