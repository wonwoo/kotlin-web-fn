package ml.wonwoo.kotlinwebfn

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class InitializeData {

    @Bean
    fun init(repository: AccountRepository) = CommandLineRunner {
        repository.save(Account(1, "wonwoo", 22))
        repository.save(Account(2, "foo", 32))
        repository.save(Account(3, "bar", 28))

    }

}