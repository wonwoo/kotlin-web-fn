package ml.wonwoo.kotlinwebfn;

import org.springframework.data.jpa.repository.JpaRepository;

interface PostRepository extends JpaRepository<Post, Long> {

}
