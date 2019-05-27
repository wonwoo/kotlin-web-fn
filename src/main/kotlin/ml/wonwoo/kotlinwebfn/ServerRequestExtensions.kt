package ml.wonwoo.kotlinwebfn

import org.springframework.core.ParameterizedTypeReference
import org.springframework.web.servlet.function.ServerRequest

inline fun <reified T : Any> ServerRequest.body() =
        this.body(T::class.java)

inline fun <reified T : Any> ServerRequest.bodyToTokens() =
        this.body(object : ParameterizedTypeReference<T>() {})
