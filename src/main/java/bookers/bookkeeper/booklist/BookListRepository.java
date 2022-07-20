package bookers.bookkeeper.booklist;

import bookers.bookkeeper.bookentry.BookEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookListRepository extends JpaRepository<BookEntry, Long> {
    List<BookEntry> findByOrderBy(Pageable page);
}
