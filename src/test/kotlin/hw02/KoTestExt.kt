package hw02

import io.kotest.core.annotation.AutoScan
import io.kotest.core.extensions.TestCaseExtension
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.random.Random

class SimpleExpression {
    companion object {
        fun getSum(a: Int, b: Int) = a + b
    }
}

class KoTestExt : FunSpec({
    val a = Random.nextInt(10, 15)
    val b = Random.nextInt(1, 9)

    test("positive test") {
        a + b shouldBe SimpleExpression.getSum(a, b)
    }

    test("negative test") {
        a * b shouldNotBe 0
    }

    test("second negative test") {
        a * b shouldNotBe null
    }
})

@AutoScan
class RepeatOnFailureExtension : TestCaseExtension {
    private val maxRunCount = 3
    override suspend fun intercept(testCase: TestCase, execute: suspend (TestCase) -> TestResult): TestResult {
        val result = execute(testCase)

        if (result.isErrorOrFailure) {
            for (i in 1 until maxRunCount) {
                execute(testCase)
                println("Re-running failed test $i, ${testCase.name.testName}")
            }
        }

        return result
    }

}