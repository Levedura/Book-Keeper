package bookers.bookkeeper.generics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;

public interface Service<T> {
    List<T> getAllEntities();

    T getEntityById(Long id);

    T addEntity(T t);

    Long deleteEntityById(Long id);

    List<T> getEntitiesOrderedBy(Function<Pageable, List<T>> func, int pages, int size);

    List<T> getSimpleSort(String sort);

    Page<T> getSimpleSortPaging(String sort, Integer pages, Integer pageSize);
}
