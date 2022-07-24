package bookers.bookkeeper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Function;

public abstract class BaseService<T, Repository extends JpaRepository<T, Long>> {

    private static final String ERROR_MESSAGE = "Entity not found";
    protected final Repository rep;

    protected BaseService(Repository rep) {
        this.rep = rep;
    }


    public T findEntityById(Long entityId) {
        return rep.findById(entityId).orElseThrow(() -> new IllegalStateException(ERROR_MESSAGE));
    }

    public Long deleteEntityById(Long entityId) {
        if (!rep.existsById(entityId)) {
            throw new IllegalStateException(ERROR_MESSAGE);
        }
        rep.deleteById(entityId);
        return entityId;
    }

    public List<T> getEntitiesOrderedBy(Function<Pageable, List<T>> func, int pages, int size) {
        Pageable page = PageRequest.of(pages, size);
        return func.apply(page);
    }


}
