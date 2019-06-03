package ml.wonwoo.kotlinwebfn;

import static org.springframework.web.servlet.function.ServerResponse.ok;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
class PostHandler {

    private final PostRepository postRepository;

    public PostHandler(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    ServerResponse getPosts(@SuppressWarnings("unused") ServerRequest serverRequest) {
        return ok().body(postRepository.findAll());
    }
}
