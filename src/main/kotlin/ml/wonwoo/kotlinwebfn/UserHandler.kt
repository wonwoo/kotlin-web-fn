package ml.wonwoo.kotlinwebfn

import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.ok
import org.springframework.web.servlet.function.body

@Component
class UserHandler(private val accountRepository: AccountRepository,
                  private val accountConverter: AccountConverter) {

    fun findAll(serverRequest: ServerRequest): ServerResponse =
            ok().body(accountRepository.findAll().map { accountConverter(it) })

    fun findOne(serverRequest: ServerRequest) =
            ok().body(accountRepository.findById(serverRequest.pathVariable("id").toLong())
                    .orElseThrow { IllegalArgumentException() })

    fun findAllView(serverRequest: ServerRequest) =
            ok().render("users", mapOf("users" to accountRepository.findAll().map { it.toDto() }))

    fun save(serverRequest: ServerRequest) =
            ok().body(accountRepository.save(serverRequest.body<AccountDto>().toAccount()))
}

class AccountDto(val name: String, val age: Int)

fun Account.toDto() = AccountDto(name, age)

fun AccountDto.toAccount() = Account(name = name, age = age)

@Component
class AccountConverter : (Account) -> AccountDto {
    override fun invoke(p1: Account): AccountDto {
        return AccountDto(p1.name, p1.component3())
    }
}