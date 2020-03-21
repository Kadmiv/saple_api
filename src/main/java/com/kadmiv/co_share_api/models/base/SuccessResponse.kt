package com.kadmiv.co_share_api.models.base

import org.apache.commons.lang3.exception.ExceptionUtils


class SuccessBuilder() {

    private var message: String = ""
    private var path: String = "/"
    private var status: Int = 0

    //    private val timeStamp = Timestamp(System.currentTimeMillis());
//    private var timestamp: String = timeStamp.toInstant().toString()
    private var timestamp: String = System.currentTimeMillis().toString()


    fun setMessage(message: String): SuccessBuilder {
        this.message = message
        return this
    }

    fun setMessage(message: Throwable): SuccessBuilder {
        this.message = ExceptionUtils.getStackTrace(message)
        return this
    }

    fun setPath(path: String): SuccessBuilder {
        this.path = path
        return this
    }

    fun setStatus(status: Int): SuccessBuilder {
        this.status = status
        return this
    }

    fun build(): SuccessAnswer {
        val answer = SuccessAnswer().apply {
            this.message = this@SuccessBuilder.message
            this.path = this@SuccessBuilder.path
            this.status = this@SuccessBuilder.status
            this.timestamp = this@SuccessBuilder.timestamp
        }
        return answer

    }

    class SuccessAnswer {
        var message: String = ""
        var path: String = ""
        var status: Int = 0
        var timestamp: String = ""
    }

}