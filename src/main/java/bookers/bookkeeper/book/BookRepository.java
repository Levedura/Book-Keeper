package bookers.bookkeeper.book;

import bookers.bookkeeper.book.dto.BookSimpleView;
import bookers.bookkeeper.generics.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends GenericRepository<Book> {

    List<BookSimpleView> getBooksBy();

    @Query(value = "select * from book_authors inner join book on book.id = books_id where authors_id in ?1", nativeQuery = true)
    List<Book> findByAuthors(List<Long> authors);


    @Query(value = "select book.id as booid,cover,score,language,pages,title,a.id,a.favorites,a.name from book inner join ( select book_id as bid from bookgenres b inner join book on book.id = b.book_id where genres in ?1 group by bid having count(*) = ?2) as b on b.bid = book.id inner join book_authors ba on ba.books_id = bid inner join author a on ba.authors_id = a.id", nativeQuery = true)
    List<Long> findByGenres(List<String> genres, Integer size);

    Page<Book> findBooksByTitleLike(Pageable page, String words);
}
