package ml.wonwoo.kotlinwebfn

import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.ok

@Component
class UserHandler {

    val users = mapOf("1" to User(1, "wonwoo", 20),
            "2" to User(2, "admin", 28),
            "3" to User(3, "foo", 22),
            "4" to User(3, "bar", 32)
    )

    fun findAll(serverRequest: ServerRequest): ServerResponse {
        return ok().body(users.values.map { it.toDto() })
    }

    fun findOne(serverRequest: ServerRequest): ServerResponse {
        return ok().body(users[serverRequest.pathVariable("id")]!!.toDto())
    }

}

data class User(val id: Long, val name: String, val age: Int)

class UserDto(val name: String, val age: Int)

fun User.toDto() = UserDto(name, age)

