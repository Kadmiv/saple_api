package com.kadmiv.co_share_api.controllers

//import org.apache.log4j.Logger
import com.kadmiv.co_share_api.repo.RepoService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.async.DeferredResult


const val CATEGORY_PATH = "category"

@RestController()
@RequestMapping(value = ["/$CATEGORY_PATH"])
//@Transactional
class CategoryController {

    private val controllerPath = CATEGORY_PATH

    private val LOG = LoggerFactory.getLogger(CategoryController::class.java)

    @Autowired
    internal var dataRepository: RepoService? = null

//    @GetMapping(value = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    open fun getAllCards(): DeferredResult<ResponseEntity<*>> {
//        val method = object {}.javaClass.enclosingMethod?.name
//        var msg = ""
//
//        val output = DeferredResult<ResponseEntity<*>>()
//
//        prepareOutput(output, method)
//
//        ForkJoinPool.commonPool().submit {
//            try {
//                val data = dataRepository?.getAllItems()
//                output.setResult(ResponseEntity.status(HttpStatus.OK).body(data))
//            } catch (ex: java.lang.Exception) {
//                msg = "Problem with data loading!!!"
//                output.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(InfoResponse().apply { this.msg = msg }))
//            }
//        }
//
//        return output
//    }
//
//    @PostMapping(value = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    open fun addNewCard(
//            @RequestBody dataSet: ArrayList<Card>
//    ): DeferredResult<ResponseEntity<*>> {
//        val method = object {}.javaClass.enclosingMethod?.name
////        val requestDir = "$RESOURCE_PATH/$method"
//
//        var msg = ""
//
//        val output = DeferredResult<ResponseEntity<*>>()
//
//        prepareOutput(output, method)
//
//        ForkJoinPool.commonPool().submit {
//            try {
////                clearDataItemsDate(dataSet)
//                dataRepository?.insertItems(dataSet)
//                msg = "Data was add"
//                output.setResult(ResponseEntity.status(HttpStatus.OK).body(InfoResponse().apply { this.msg = msg }))
//            } catch (ex: java.lang.Exception) {
////                logger.error(method, ex)
//                msg = "Data wasn't add!!!"
//                output.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(InfoResponse().apply { this.msg = msg }))
//            }
//        }
//
//        return output
//    }
//
//    @GetMapping(value = ["/mostPopular"], produces = [MediaType.APPLICATION_JSON_VALUE])
//    open fun getMostPopular(): DeferredResult<ResponseEntity<*>> {
//        val method = object {}.javaClass.enclosingMethod?.name
//        var msg = ""
//
//        val output = DeferredResult<ResponseEntity<*>>()
//
//        prepareOutput(output, method)
//
//        ForkJoinPool.commonPool().submit {
//            try {
//                val data = dataRepository?.getMostPopularItems()
//                output.setResult(ResponseEntity.status(HttpStatus.OK).body(data))
//            } catch (ex: java.lang.Exception) {
//                msg = "Problem with data loading!!!"
//                output.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(InfoResponse().apply { this.msg = msg }))
//            }
//        }
//
//        return output
//    }


    protected fun prepareOutput(
            output: DeferredResult<ResponseEntity<*>>,
            method: String?
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