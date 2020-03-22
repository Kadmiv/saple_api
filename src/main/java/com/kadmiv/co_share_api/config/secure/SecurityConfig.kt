package com.kadmiv.co_share_api.config.secure

import com.kadmiv.co_share_api.repo.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val authEntryPoint: AuthenticationEntryPointImpl? = null


//    @Bean
//    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder? {
//        return BCryptPasswordEncoder()
//    }


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .logout()
                .permitAll()
                .and()
                .httpBasic().authenticationEntryPoint(authEntryPoint)

//        http
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                //Доступ только для НЕ зарегистрированных пользователей
//                .antMatchers("/$REGISTRATION_PATH").permitAll() //.not().fullyAuthenticated()
////                //Доступ только для пользователей с ролью Администратор
////                .antMatchers("/admin/**").hasRole("ADMIN")
////                .antMatchers("/news").hasRole("USER")
////                //Доступ разрешен всем пользователей
////                .antMatchers("/", "/resources/**").permitAll()
//                //Все остальные страницы требуют аутентификации
//                .anyRequest().authenticated()
////                .and()
//                //Настройка для входа в систему
////                .formLogin()
////                .loginPage("/login")
//                //Перенарпавление на главную страницу после успешного входа
////                .defaultSuccessUrl("/")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll()
////                .logoutSuccessUrl("/")
//
//        http.httpBasic().authenticationEntryPoint(authEntryPoint);
    }

//
//    @Autowired
//    @Throws(Exception::class)
//    protected open fun configureGlobal(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService<UserDetailsService>(userService).passwordEncoder(bCryptPasswordEncoder())
//    }


    @Autowired
    @Throws(Exception::class)
    open fun configureGlobal(auth: AuthenticationManagerBuilder) {

        val u1: UserDetails = User.withUsername("admin1234").password("{noop}admin1234").roles("USER").build()

        auth.inMemoryAuthentication()
                .withUser(u1)
    }

}