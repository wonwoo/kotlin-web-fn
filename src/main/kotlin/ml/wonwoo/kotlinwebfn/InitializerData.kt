package ml.wonwoo.kotlinwebfn

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

interface InitializerData {

    @Transactional
    fun initializer()

    @Component
    class NoopInitializerData : InitializerData {
        override fun initializer() {
            //nothing
        }
    }
}

