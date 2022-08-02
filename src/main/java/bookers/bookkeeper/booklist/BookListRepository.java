package bookers.bookkeeper.booklist;

import bookers.bookkeeper.enums.Status;
import bookers.bookkeeper.generics.GenericRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookListRepository extends GenericRepository<BookEntry> {
    List<BookEntry> findBookEntriesByUser_Username(String username, Pageable page);

    List<BookEntry> findBookEntriesByUser_UsernameAndStatus(String username, Status status);

    @Query(value = "select avg (b.userScore) from BookEntry b where b.user.username like ?1")
    Float averageUserScore(String username);


    @Query(value = "select sum(b.pagesRead) from BookEntry b where b.user.username like ?1")
    Integer sumPagesRead(String username);


    @Query(value = "select count(b.book) from BookEntry b where b.user.username like ?1")
    Integer booksRead(String username);

}
