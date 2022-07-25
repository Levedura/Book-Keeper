package bookers.bookkeeper.author;

import bookers.bookkeeper.book.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByOrderByNameAsc(Pageable pageable);

    List<Author> findByOrderByFavorites(Pageable pageable);

}
