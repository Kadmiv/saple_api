package com.kadmiv.co_share_api.config.secure

import com.kadmiv.co_share_api.controllers.REGISTRATION_PATH
import com.kadmiv.co_share_api.repo.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val encoderWrapper: EncoderWrapper? = null

    @Autowired
    private val authEntryPoint: AuthenticationEntryPointImpl? = null


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/$REGISTRATION_PATH").permitAll()
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


    @Autowired
    @Throws(Exception::class)
    protected open fun configureGlobal(auth: AuthenticationManagerBuilder) {

        auth
                .userDetailsService(userService)
                .passwordEncoder(encoderWrapper?.getEncoder())

//        auth.inMemoryAuthentication()
//                .withUser("spring")
//                .password(encoder.encode("secret"))
//                .roles("USER")
    }


//    @Autowired
//    @Throws(Exception::class)
//    open fun configureGlobal(auth: AuthenticationManagerBuilder) {
//
//        val users = userService?.getAll()
//        if (users != null) {
//            for (user in users) {
//                auth.inMemoryAuthentication()
//                        .withUser(user?.userLogin)
//                        .password("{noop}${user?.userPassword}")
//                        .roles(user?.roles?.first()?.name);
//
//                if (user != users.last()) {
//                    auth.inMemoryAuthentication().and()
//                }
//            }
//        }
//    }

}