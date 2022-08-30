package bookers.bookkeeper.author;

import bookers.bookkeeper.generics.GenericRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends GenericRepository<Author> {

    @Query(value = "select * from book_authors inner join author a  on a.id = authors_id  where books_id  in ?1", nativeQuery = true)
    List<Author> findAuthorsByBooks(List<Long> bookIds);
}
