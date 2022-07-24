package bookers.bookkeeper.book;

import bookers.bookkeeper.enums.Genres;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByOrderByGlobalscore(Pageable page);

    List<Book> findByOrderByPages(Pageable page);

    List<Book> findByOrderByTitle(Pageable page);

    @Query(value = "select * from book_authors inner join book on book.id = books_id where authors_id in ?1", nativeQuery = true)
    List<Book> findByAuthors(List<Long> authors);

    @Query(value = "select book_id from bookgenres b inner join book on book.id = b.book_id where genres in ?1 group by book_id having count(*) = ?2", nativeQuery = true)
    List<Long> findByGenres(List<String> genres, Integer size);

}
