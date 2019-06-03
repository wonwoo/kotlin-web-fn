package ml.wonwoo.kotlinwebfn;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.web.servlet.function.RequestPredicates.*;
import static org.springframework.web.servlet.function.RouterFunctions.nest;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;

@Configuration(proxyBeanMethods = false)
class RouterRouterPostsConfiguration {

    @Bean
    RouterFunction<?> postRouter(PostHandler postHandler) {
        return nest(path("/api"),
            route(accept(APPLICATION_JSON)
                .and(GET("/posts")), postHandler::getPosts))
            .and(route(accept(TEXT_HTML).and(GET("/posts")),
                request -> ok().render("posts")));
    }

}
