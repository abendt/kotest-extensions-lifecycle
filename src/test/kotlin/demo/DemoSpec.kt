package demo

import io.kotest.core.extensions.MountableExtension
import io.kotest.core.extensions.install
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult

class DemoSpec : StringSpec({

    install(MyExtension()) {}
    install(OtherExtension()) {}

    "test" {

    }

})

class MyExtension : MountableExtension<MyExtension, MyExtension>, TestListener {
    override fun mount(configure: MyExtension.() -> Unit): MyExtension {
        return this
    }

    override suspend fun beforeTest(testCase: TestCase) {
        println("before test $this")
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        println("after test $this")
    }
}

class OtherExtension : MountableExtension<OtherExtension, OtherExtension>, TestListener {
    override fun mount(configure: OtherExtension.() -> Unit): OtherExtension {
        return this
    }

    override suspend fun beforeTest(testCase: TestCase) {
        println("before test $this")

        throw RuntimeException("extension failed")
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        println("after test $this")
    }
}
