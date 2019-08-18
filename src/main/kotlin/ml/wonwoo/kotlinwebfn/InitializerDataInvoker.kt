package ml.wonwoo.kotlinwebfn

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InitializerDataInvoker(private val initializerData: List<InitializerData>) :
        CommandLineRunner {

    override fun run(vararg args: String?) {

        initializerData.forEach { it.initializer() }

    }
}