package bookers.bookkeeper.book;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByOrderByGlobalscore(Pageable page);
    List<Book> findByOrderByPages(Pageable page);
    List<Book> findByOrderByTitle(Pageable page);
    List<Book> findByLanguageContains(String language);
}
