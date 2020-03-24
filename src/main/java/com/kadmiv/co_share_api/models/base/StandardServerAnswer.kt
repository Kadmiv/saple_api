package com.kadmiv.co_share_api.models.base

import org.apache.commons.lang3.exception.ExceptionUtils


class ServerAnswerBuilder() {

    private var error: String = ""
    private var message: String = ""
    private var path: String = "/"
    private var status: Int = 0

    //    private val timeStamp = Timestamp(System.currentTimeMillis());
//    private var timestamp: String = timeStamp.toInstant().toString()
//    private var timestamp: String = System.currentTimeMillis().toString()
    private var timestamp: Long = System.currentTimeMillis()

    fun setError(error: String): ServerAnswerBuilder {
        this.error = error
        return this
    }


    fun setError(error: Throwable): ServerAnswerBuilder {
        this.error = ExceptionUtils.getStackTrace(error)
        return this
    }

    fun setMessage(message: String): ServerAnswerBuilder {
        this.message = message
        return this
    }


    fun setPath(path: String): ServerAnswerBuilder {
        this.path = path
        return this
    }

    fun setStatus(status: Int): ServerAnswerBuilder {
        this.status = status
        return this
    }

    fun build(): StandardServerAnswer {
        val answer = StandardServerAnswer().apply {
            this.error = this@ServerAnswerBuilder.error
            this.message = this@ServerAnswerBuilder.message
            this.path = this@ServerAnswerBuilder.path
            this.status = this@ServerAnswerBuilder.status
            this.timestamp = this@ServerAnswerBuilder.timestamp
        }
        return answer

    }

    class StandardServerAnswer {
        var error: String = ""
        var message: String = ""
        var path: String = ""
        var status: Int = 0
        var timestamp: Long = 0L
    }

}