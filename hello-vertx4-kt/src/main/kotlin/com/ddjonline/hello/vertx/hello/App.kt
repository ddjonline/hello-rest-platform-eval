package com.ddjonline.hello.vertx.hello

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router


import java.util.concurrent.atomic.AtomicLong

class App : AbstractVerticle() {
    private val counter: AtomicLong = AtomicLong()

    companion object {
        suspend fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            try {
                vertx.deployVerticle(App())
                println("Application started")
            } catch (exception: Throwable) {
                println("Could not start application")
                exception.printStackTrace()
            }
        }

        fun pi_digits(digits: Int): String {
            val scale = 10000
            val array_init = 2000
            val pi = StringBuffer()
            val arr = IntArray(digits + 1)
            var carry = 0
            for (i in 0..digits) arr[i] = array_init
            var i = digits
            while (i > 0) {
                var sum = 0
                for (j in i downTo 1) {
                    sum = sum * j + scale * arr[j]
                    arr[j] = sum % (j * 2 - 1)
                    sum /= j * 2 - 1
                }
                pi.append(String.format("%04d", carry + sum / scale))
                carry = sum % scale
                i -= 14
            }
            return pi.toString()
        }
    }

    override fun start() {
        val router: Router = Router.router(vertx)
        router.get("/hello").handler { context ->
            context.response().end("Hello (" + counter.incrementAndGet() + ")")
        }
        router.get("/naptime").blockingHandler { context ->
            context.response().end("your slice of pi is " + pi_digits(20000))
        }
        vertx.createHttpServer().requestHandler(router).listen(8080)
    }
}