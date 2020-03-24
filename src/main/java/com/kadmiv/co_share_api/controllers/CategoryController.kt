package com.kadmiv.co_share_api.controllers

//import org.apache.log4j.Logger
import com.kadmiv.co_share_api.models.base.ServerAnswerBuilder
import com.kadmiv.co_share_api.models.dto.Category
import com.kadmiv.co_share_api.repo.category.CategoryService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.async.DeferredResult
import java.util.concurrent.ForkJoinPool


const val CATEGORY_PATH = "category"

@RestController()
@RequestMapping(value = ["/$CATEGORY_PATH"])
//@Transactional
class CategoryController {

    private val controllerPath = CATEGORY_PATH

    private val LOG = LoggerFactory.getLogger(CategoryController::class.java)

    @Autowired
    internal var dataRepository: CategoryService? = null

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    open fun getAllCategories(): DeferredResult<ResponseEntity<*>> {
        LOG.info("")
        var msg = ""

        val output = DeferredResult<ResponseEntity<*>>()

        prepareOutput(output)

        ForkJoinPool.commonPool().submit {
            try {
                val data = dataRepository?.getAllItems()
                output.setResult(ResponseEntity.status(HttpStatus.OK).body(data))
            } catch (ex: java.lang.Exception) {
                msg = "Problem with data loading!!!"
                output.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        ServerAnswerBuilder()
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
            @RequestBody dataSet: ArrayList<Category>
    ): DeferredResult<ResponseEntity<*>> {
        LOG.info("")
//        val requestDir = "$RESOURCE_PATH/$method"

        var msg = ""

        val output = DeferredResult<ResponseEntity<*>>()

        prepareOutput(output)

        ForkJoinPool.commonPool().submit {
            try {
//                clearDataItemsDate(dataSet)
                dataRepository?.insertItems(dataSet)
                msg = "Data was add"
                output.setResult(ResponseEntity.status(HttpStatus.OK).body(
                        ServerAnswerBuilder()
                                .setStatus(HttpStatus.OK.value())
                                .setPath("/$controllerPath")
                                .setMessage(msg)
                                .build()
                ))
            } catch (ex: java.lang.Exception) {
//                logger.error(method, ex)
                msg = "Data wasn't add!!!"
                output.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        ServerAnswerBuilder()
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
}