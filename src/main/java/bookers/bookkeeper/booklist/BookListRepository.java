package bookers.bookkeeper.booklist;

import bookers.bookkeeper.generics.GenericRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookListRepository extends GenericRepository<BookEntry> {
    List<BookEntry> findBookEntriesByUser_Username(String username);

    List<BookEntry> findBookEntriesByUser_Username(String username, Pageable page);
}
