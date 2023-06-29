package com.example.threadhandlerasynctask

import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


fun main(args: Array<String>) {
    println(Thread.currentThread().id)

    val t1 = Thread {
            println("t1 - ${Thread.currentThread().id}")
    }

    t1.start()
    // t1.start()

    val r1 = Runnable {
        println("r1 - ${Thread.currentThread().id}")
    }

    val t2 = Thread(r1)
    t2.start()

    val t3 = Thread(r1)
    t3.start()

    val cores = Runtime.getRuntime().availableProcessors()
    println("cores - $cores")
    val mRunnable = MyRunnable(1)
    mRunnable.run()

    val exService = Executors.newFixedThreadPool(2)

    exService.submit(MyRunnable(400))
    exService.submit(MyRunnable(2000))
    exService.submit(MyRunnable(50))
    exService.submit(MyRunnable(500))

    val result = exService.submit(MyCallable())

    if (result.isDone) {
        try {
            val res = result.get()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }

    exService.shutdown()
//    exService.shutdownNow()
}

class MyRunnable(private val pause: Long): Runnable {
    override fun run() {
        try {
            Thread.sleep(pause)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        println("MyRunnable - ${Thread.currentThread().id}")
    }
}

class MyCallable: Callable<String> {
    override fun call(): String {
        Thread.sleep(2500)
        return "Hello"
    }
}
