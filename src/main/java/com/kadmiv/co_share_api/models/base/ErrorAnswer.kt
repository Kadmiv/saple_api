package com.kadmiv.co_share_api.models.base

import org.apache.commons.lang3.exception.ExceptionUtils


class ErrorBuilder() {

    private var error: String = ""
    private var message: String = ""
    private var path: String = "/"
    private var status: Int = 0

    //    private val timeStamp = Timestamp(System.currentTimeMillis());
//    private var timestamp: String = timeStamp.toInstant().toString()
    private var timestamp: String = System.currentTimeMillis().toString()

    fun setError(error: String): ErrorBuilder {
        this.error = error
        return this
    }

    fun setMessage(message: String): ErrorBuilder {
        this.message = message
        return this
    }

    fun setMessage(message: Throwable): ErrorBuilder {
        this.message = ExceptionUtils.getStackTrace(message)
        return this
    }

    fun setPath(path: String): ErrorBuilder {
        this.path = path
        return this
    }

    fun setStatus(status: Int): ErrorBuilder {
        this.status = status
        return this
    }

    fun build(): ErrorAnswer {
        val answer = ErrorAnswer().apply {
            error = this@ErrorBuilder.error
            this.message = this@ErrorBuilder.message
            this.path = this@ErrorBuilder.path
            this.status = this@ErrorBuilder.status
            this.timestamp = this@ErrorBuilder.timestamp
        }
        return answer

    }

    class ErrorAnswer {
        var error: String = ""
        var message: String = ""
        var path: String = ""
        var status: Int = 0
        var timestamp: String = ""
    }

}