package hw01

import kotlin.reflect.full.functions

interface TestRunner<T> {
    fun runTest(steps: T, test: () -> Unit)
}

class MyTestRunner : TestRunner<Any> {

    override fun runTest(steps: Any, test: () -> Unit) {
        steps::class.functions.filter { it.name.contains("before") }.forEach { it.call(steps) }
        test()
        steps::class.functions.filter { it.name.contains("after") }.forEach { it.call(steps) }
    }
}

class Steps() {
    fun afterAll() = log("afterAll test")

    fun before() {
        log("before test")
    }

    fun after() {
        log("after test")
    }

    private fun log(s: String) {
        println("[LOG] $s")
    }
}

fun testFunction() {
    println("My test is running.")
}

fun main() {
    MyTestRunner().runTest(Steps(), ::testFunction)
}
