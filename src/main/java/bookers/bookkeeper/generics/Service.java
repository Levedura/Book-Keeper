package bookers.bookkeeper.generics;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface Service<T> {
    List<T> getAllEntities();

    T getEntityById(Long id);

    T addEntity(T t);

    List<T> addListEntities(List<T> entities);

    Long deleteEntityById(Long id);

    List<T> getSimpleSort(String sort);

    List<T> getSimpleSortOrder(String sort, String order);

    Page<T> getSimpleSortPagingOrder(String sort, String order, Integer pages, Integer pageSize);

    Page<T> getSimpleSortPaging(String sort, Integer pages, Integer pageSize);

    T updateEntity(Long entityid, Map<String, Object> jsonMap);

}
