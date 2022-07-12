package hw01

interface TestRunner<T> {
    fun runTest(steps: T, test: () -> Unit)
}

class MyTestRunner : TestRunner<Any> {
    override fun runTest(steps: Any, test: () -> Unit) {
        Steps().before()
        test()
        Steps().after()
    }
}

class Steps() {
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
