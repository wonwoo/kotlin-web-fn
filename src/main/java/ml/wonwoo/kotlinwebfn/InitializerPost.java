package ml.wonwoo.kotlinwebfn;

import org.springframework.stereotype.Component;

@Component
class InitializerPost implements InitializerData {

    private final PostRepository postRepository;

    public InitializerPost(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void initializer() {
        postRepository.save(new Post("test post", "test content"));
    }
}
