package ml.wonwoo.kotlinwebfn

import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.ok

@Component
class UserHandler(private val accountRepository: AccountRepository) {

    fun findAll(serverRequest: ServerRequest): ServerResponse {
        return ok().body(accountRepository.findAll().map { it.toDto() })
    }

    fun findOne(serverRequest: ServerRequest): ServerResponse {
        return ok().body(accountRepository.findById(serverRequest.pathVariable("id").toLong())
                .orElseThrow { IllegalArgumentException() })
    }

    fun findAllView(serverRequest: ServerRequest): ServerResponse {
        return ok().render("users", mapOf("users" to accountRepository.findAll().map { it.toDto() }))
    }

}


class AccountDto(val name: String, val age: Int)

fun Account.toDto() = AccountDto(name, age)

