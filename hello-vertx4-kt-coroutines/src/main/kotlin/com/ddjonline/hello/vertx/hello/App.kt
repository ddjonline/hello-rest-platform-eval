package com.ddjonline.hello.vertx.hello

import io.vertx.core.Vertx
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong


class App : CoroutineVerticle() {

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

    }

    private val counter = AtomicLong()

    override suspend fun start(): Unit {

        val router = Router.router(vertx)
        router.get("/hello").coroutineHandler { ctx -> getCounter(ctx) }
        router.get("/naptime").coroutineHandler { ctx -> pi_digits(ctx, 20000) }
        // Start the server
        vertx.createHttpServer()
            .requestHandler(router)
            .listen(config.getInteger("http.port", 8080))
            .await()
    }

    suspend fun getCounter(context: RoutingContext) {
        context.response().end("Hello (" + counter.incrementAndGet() + ")")
    }

    suspend fun pi_digits(context: RoutingContext, digits: Int): Unit {
        val scale = 10000
        val array_init = 2000
        var pi = StringBuffer()
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
        context.response().end("your slice of pi is $pi")
    }

    fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) {
        handler { ctx ->
            launch(ctx.vertx().dispatcher()) {
                try {
                    fn(ctx)
                } catch (e: Exception) {
                    ctx.fail(e)
                }
            }
        }
    }
}