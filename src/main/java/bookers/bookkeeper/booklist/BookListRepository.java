package bookers.bookkeeper.booklist;

import bookers.bookkeeper.bookentry.BookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookListRepository extends JpaRepository<BookEntry,Long>{
}
