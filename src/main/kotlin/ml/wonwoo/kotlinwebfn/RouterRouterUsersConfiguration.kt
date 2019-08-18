package ml.wonwoo.kotlinwebfn

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.web.servlet.function.router

@Configuration(proxyBeanMethods = false)
class RouterRouterUsersConfiguration {

    @Bean
    fun usersRouter(userHandler: UserHandler) = router {
        "/api".nest {
            accept(APPLICATION_JSON).nest {
                GET("/users", userHandler::findAll)
                POST("/users", userHandler::save)
            }
            accept(APPLICATION_JSON).nest {
                GET("/users/{id}", userHandler::findOne)
            }
        }
        accept(TEXT_HTML).nest {
            GET("/") {
                ok().render("index")
            }
            GET("/users", userHandler::findAllView)
        }
    }
}