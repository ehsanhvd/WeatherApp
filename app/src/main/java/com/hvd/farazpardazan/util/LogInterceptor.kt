package com.hvd.farazpardazan.util

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException

class LogInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val reqBuilder = chain.request().newBuilder()
        onRequest(reqBuilder)
        val request = reqBuilder.build()
        logRequest(request)
        return chain.proceed(request)
    }

    private fun onRequest(reqBuilder: Request.Builder) {

    }

    private fun logRequest(request: Request) {
        println("WebService Request url: " + request.url())
        println(
            "WebService Request body: " + bodyToString(
                request
            )
        )
    }

    private fun bodyToString(request: Request): String? {
        return try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            if (copy.body() != null) {
                copy.body()!!.writeTo(buffer)
            }
            buffer.readUtf8()
        } catch (e: IOException) {
            "did not work"
        }
    }


}