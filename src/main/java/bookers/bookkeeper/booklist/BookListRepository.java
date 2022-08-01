package bookers.bookkeeper.booklist;

import bookers.bookkeeper.enums.Status;
import bookers.bookkeeper.generics.GenericRepository;
import bookers.bookkeeper.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookListRepository extends GenericRepository<BookEntry> {
    List<BookEntry> findBookEntriesByUser_Username(String username, Pageable page);
    List<BookEntry> findBookEntriesByUser_UsernameAndStatus(String username, Status status);
}
