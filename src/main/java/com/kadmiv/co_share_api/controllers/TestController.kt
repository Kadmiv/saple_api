package com.kadmiv.co_share_api.controllers

//import org.apache.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.servlet.mvc.AbstractController
import java.util.concurrent.ForkJoinPool

@RestController()
@RequestMapping(value = ["/test"])
//@Transactional
class TestController {

    @GetMapping(value = ["/"])
    open fun solverTest(): DeferredResult<ResponseEntity<*>> {
        val method = object {}.javaClass.enclosingMethod?.name

        val output = DeferredResult<ResponseEntity<*>>()
        prepareOutput(output, method)

        ForkJoinPool.commonPool().submit {
            val msg = "Server is work"
            output.setResult(ResponseEntity.status(HttpStatus.OK).body(msg))
        }

        return output
    }

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