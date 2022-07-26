package bookers.bookkeeper.author;

import bookers.bookkeeper.generics.GenericRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorRepository extends GenericRepository<Author> {
    List<Author> findByOrderByNameAsc(Pageable pageable);

    List<Author> findByOrderByFavorites(Pageable pageable);

}
