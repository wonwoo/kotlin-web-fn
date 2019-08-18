package ml.wonwoo.kotlinwebfn

import org.springframework.stereotype.Component

@Component
class InitializerAccount(private val repository: AccountRepository) : InitializerData {

    override fun initializer() {

        this.repository.save(Account(1, "wonwoo", 22))
        this.repository.save(Account(2, "foo", 32))
        this.repository.save(Account(3, "bar", 28))

    }
}