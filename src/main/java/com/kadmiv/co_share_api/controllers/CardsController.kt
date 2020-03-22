package com.kadmiv.co_share_api.controllers

//import org.apache.log4j.Logger
import com.kadmiv.co_share_api.models.base.ErrorBuilder
import com.kadmiv.co_share_api.models.base.SuccessBuilder
import com.kadmiv.co_share_api.models.dto.Card
import com.kadmiv.co_share_api.models.dto.User
import com.kadmiv.co_share_api.repo.card.CardService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.async.DeferredResult
import java.util.concurrent.ForkJoinPool

const val CARD_PATH = "card"

@RestController()
@RequestMapping(value = ["/$CARD_PATH"])
//@Transactional
class CardsController {

    private val LOG = LoggerFactory.getLogger(CardsController::class.java)

    private val controllerPath = CARD_PATH

    @Autowired
    internal var dataRepository: CardService? = null

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    open fun getAllUserCards(): DeferredResult<ResponseEntity<*>> {
        LOG.info("")
        var msg = ""

        val authentication = SecurityContextHolder.getContext().authentication;

        val output = DeferredResult<ResponseEntity<*>>()

        prepareOutput(output)

        ForkJoinPool.commonPool().submit {
            try {

                if (authentication != null) {
                    val user: User = authentication.principal as User
                    val data = dataRepository?.getAllUserCards(user)
                    output.setResult(ResponseEntity.status(HttpStatus.OK).body(data))
                }

                output.setResult(ResponseEntity.status(HttpStatus.OK).body(arrayListOf<Card>()))
            } catch (ex: java.lang.Exception) {
                msg = "Problem with data loading!!!"
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

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    open fun addNewCard(
//            @AuthenticationPrincipal user: User,
            @RequestBody card: Card
    ): DeferredResult<ResponseEntity<*>> {
        LOG.info("")
//        val requestDir = "$RESOURCE_PATH/$method"

        var msg = ""

        val authentication = SecurityContextHolder.getContext().authentication;

        val output = DeferredResult<ResponseEntity<*>>()

        prepareOutput(output)

        ForkJoinPool.commonPool().submit {
            try {
//                clearDataItemsDate(dataSet)
                if (authentication != null) {
                    val user: User = authentication.principal as User

//                    for (card in dataSet) {
                    card.owner = user
                    LOG.info("")
//                    }
                }

                val validation = checkNewCard(card)

                if (validation.isValid) {
                    dataRepository?.insertItem(card)
                    msg = "Data was add"
                    output.setResult(ResponseEntity.status(HttpStatus.OK).body(
                            SuccessBuilder()
                                    .setStatus(HttpStatus.OK.value())
                                    .setPath("/$controllerPath")
                                    .setMessage(msg)
                                    .build()
                    ))
                } else {
                    msg = "Data wasn't add!!!"
                    output.setErrorResult(ResponseEntity.status(HttpStatus.DESTINATION_LOCKED).body(
                            ErrorBuilder()
                                    .setMessage(msg)
                                    .setError("Have same card error")
                                    .setStatus(HttpStatus.DESTINATION_LOCKED.value())
                                    .setPath("/$controllerPath")
                                    .build()
                    ))
                }
            } catch (ex: java.lang.Exception) {
//                logger.error(method, ex)
                msg = "Data wasn't add!!!"
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

    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    open fun updateCard(
            @RequestBody card: Card
    ): DeferredResult<ResponseEntity<*>> {
        LOG.info("")

        var msg = ""

        val authentication = SecurityContextHolder.getContext().authentication;

        val output = DeferredResult<ResponseEntity<*>>()

        prepareOutput(output)

        ForkJoinPool.commonPool().submit {
            try {

                val opitonal = dataRepository?.findByID(card.id)
                var oldCard = opitonal?.get()

                if (oldCard == null)
                    oldCard = card
                else {
                    val user: User = authentication.principal as User
                    if (oldCard.owner!!.id != user.id) {
                        msg = "Not have permission "
                        output.setErrorResult(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                                ErrorBuilder()
                                        .setMessage(msg)
                                        .setError("Not card owner error")
                                        .setStatus(HttpStatus.NOT_ACCEPTABLE.value())
                                        .setPath("/$controllerPath")
                                        .build()
                        ))
                        return@submit
                    }
                }

                val validation = checkNewCard(oldCard)

                if (validation.isValid) {
                    dataRepository?.insertItem(oldCard)
                    msg = "Data was add. Is may share: ${oldCard.mayUseForShare}"
                    output.setResult(ResponseEntity.status(HttpStatus.OK).body(
                            SuccessBuilder()
                                    .setStatus(HttpStatus.OK.value())
                                    .setPath("/$controllerPath")
                                    .setMessage(msg)
                                    .build()
                    ))
                } else {
                    msg = "Data wasn't add!!!"
                    output.setErrorResult(ResponseEntity.status(HttpStatus.DESTINATION_LOCKED).body(
                            ErrorBuilder()
                                    .setMessage(msg)
                                    .setError("Have same card error")
                                    .setStatus(HttpStatus.DESTINATION_LOCKED.value())
                                    .setPath("/$controllerPath")
                                    .build()
                    ))
                }
            } catch (ex: java.lang.Exception) {
//                logger.error(method, ex)
                msg = "Data wasn't add!!!"
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

    @GetMapping(value = ["/mostPopular"], produces = [MediaType.APPLICATION_JSON_VALUE])
    open fun getMostPopular(): DeferredResult<ResponseEntity<*>> {
        LOG.info("")
        var msg = ""

        val output = DeferredResult<ResponseEntity<*>>()

        prepareOutput(output)

        ForkJoinPool.commonPool().submit {
            try {
                val data = dataRepository?.getMostPopularItems()

                val items = arrayListOf<Card>()
                if (data != null) {
                    for (card in data) {
                        if (card.isActive && card.mayUseForShare) {
                            items.add(card)
                        }
                    }
                }

                output.setResult(ResponseEntity.status(HttpStatus.OK).body(items))
            } catch (ex: java.lang.Exception) {
                msg = "Problem with data loading!!!"
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

    //FIxme - need fix this fucntion
    private fun checkNewCard(user: Card): EntityValidation {
        val validation = EntityValidation()
        var msg = "Card is exists"

//        val userFromDb: User? = userRepo?.findByUserLogin(user.userLogin)
//
//        if (userFromDb != null) {
//            return validation.apply { message = msg }
//        }
//
//        //Check login
//        if (user.userLogin.isEmpty()) {
//            msg = "Login is empty"
//            return validation.apply { message = msg }
//        }
//
//        if (user.userPassword.isEmpty()) {
//            msg = "Password is empty"
//            return validation.apply { message = msg }
//        }
//
//        //Check password
//        if (user.userPassword != user.userConfirmPassword) {
//            msg = "Password is not the same"
//            return validation.apply { message = msg }
//        }

        return validation.apply { isValid = true }
    }
}