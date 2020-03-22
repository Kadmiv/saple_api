package com.kadmiv.co_share_api.controllers


import com.kadmiv.co_share_api.config.secure.EncoderWrapper
import com.kadmiv.co_share_api.models.base.ErrorBuilder
import com.kadmiv.co_share_api.models.base.SuccessBuilder
import com.kadmiv.co_share_api.models.dto.RegistrationModel
import com.kadmiv.co_share_api.models.dto.Role
import com.kadmiv.co_share_api.models.dto.User
import com.kadmiv.co_share_api.repo.user.UserRepo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import java.lang.Exception
import java.util.*
import java.util.concurrent.ForkJoinPool


const val REGISTRATION_PATH = "registration"

//@Controller
@RestController()
@RequestMapping(value = ["/$REGISTRATION_PATH"])
class RegistrationController {

    private val LOG = LoggerFactory.getLogger(RegistrationController::class.java)

    private val controllerPath = REGISTRATION_PATH

    @Autowired
    private val userRepo: UserRepo? = null

    @Autowired
    private val encoderWrapper: EncoderWrapper? = null

//    @GetMapping()
//    fun registration(): String {
//        return "registration"
//    }

    @PostMapping()
    fun addUser(@RequestBody user: RegistrationModel): DeferredResult<ResponseEntity<*>> {
        var msg = ""
        LOG.info(msg)

        val output = DeferredResult<ResponseEntity<*>>()

        prepareOutput(output)

        ForkJoinPool.commonPool().submit {
            try {
                var validation = checkNewUser(user)

                if (validation.isValid) {

                    val newUser = User()
                            .apply {
                                isActive = true
                                userLogin = user.userLogin
                                roles = Collections.singleton(Role.USER)
                                userPassword = encoderWrapper?.getEncoder()?.encode(user.userPassword)
                            }

                    try {
                        val dbUser = userRepo?.save(newUser)

                        if (dbUser != null) {
                            msg = "User was created"
                            output.setResult(ResponseEntity.status(HttpStatus.CREATED).body(
                                    SuccessBuilder()
                                            .setStatus(HttpStatus.CREATED.value())
                                            .setPath("/$controllerPath")
                                            .setMessage(msg)
                                            .build()
                            ))
                        } else {
                            output.setErrorResult(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                                    ErrorBuilder()
                                            .setMessage(validation.message)
                                            .setError("Writing with db error")
                                            .setStatus(HttpStatus.NOT_ACCEPTABLE.value())
                                            .setPath("/$controllerPath")
                                            .build()
                            ))
                        }

                    } catch (ex: Exception) {
                        msg = "Problem working with db"
                        output.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                                ErrorBuilder()
                                        .setMessage(msg)
                                        .setError(ex)
                                        .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                        .setPath("/$controllerPath")
                                        .build()
                        ))
                    }


                } else {
                    output.setErrorResult(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                            ErrorBuilder()
                                    .setMessage(msg)
                                    .setError("Validation error")
                                    .setStatus(HttpStatus.NOT_ACCEPTABLE.value())
                                    .setPath("/$controllerPath")
                                    .setMessage(validation.message)
                                    .build()
                    ))
                }

            } catch (ex: java.lang.Exception) {
                msg = "Problem working with db"
                output.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        ErrorBuilder()
                                .setMessage(msg)
                                .setError(ex)
                                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .setPath("/$controllerPath")
                                .build()
                ))
            }
        }

        return output

    }

    private fun checkNewUser(user: RegistrationModel): EntityValidation {
        val validation = EntityValidation()
        var msg = "User exists"

        val userFromDb: User? = userRepo?.findByUserLogin(user.userLogin)

        if (userFromDb != null) {
            return validation.apply { message = msg }
        }

        //Check login
        if (user.userLogin.isEmpty()) {
            msg = "Login is empty"
            return validation.apply { message = msg }
        }

        if (user.userPassword.isEmpty()) {
            msg = "Password is empty"
            return validation.apply { message = msg }
        }

        //Check password
        if (user.userPassword != user.userConfirmPassword) {
            msg = "Password is not the same"
            return validation.apply { message = msg }
        }

        return validation.apply { isValid = true }
    }

    protected fun prepareOutput(
            output: DeferredResult<ResponseEntity<*>>
    ) {
        output.onError { throwable ->
            //            logger.error("onError|Request $method")
//            logger.error(method, throwable)
            output.setErrorResult(
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error")
            )
        }
        output.onTimeout {
            //            logger.error("onTimeout|Request $method")
            output.setErrorResult(
                    ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                            .body("Time out")
            )
        }

        output.onCompletion {
            //            logger.info("onCompletion|Request $method")
        }
    }
}

class EntityValidation {
    var isValid = false
    var message = "New user was created"
}