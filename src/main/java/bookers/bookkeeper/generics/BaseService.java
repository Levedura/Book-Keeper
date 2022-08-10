package bookers.bookkeeper.generics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T, Repository extends GenericRepository<T>> implements Service<T> {

    private static final String ERROR_MESSAGE = "Entity not found";

    protected final Repository rep;

    protected BaseService(Repository rep) {
        this.rep = rep;
    }

    public T addEntity(T entity) {
        return rep.save(entity);
    }

    public List<T> addListEntities(List<T> entities) {
        return rep.saveAll(entities);
    }

    public List<T> getAllEntities() {
        return rep.findAll();
    }

    public T getEntityById(Long entityId) {
        return rep.findById(entityId).orElseThrow(() -> new IllegalStateException(ERROR_MESSAGE));
    }

    public Long deleteEntityById(Long entityId) {
        if (!rep.existsById(entityId)) {
            throw new IllegalStateException(ERROR_MESSAGE);
        }
        rep.deleteById(entityId);
        return entityId;
    }

    public List<T> getSimpleSort(String sort) {
        return bySort(Sort.by(sort));
    }

    public List<T> getSimpleSortOrder(String sort, String order) {
        return bySort(Sort.by(new Sort.Order(Sort.Direction.fromString(order), sort)));
    }

    private List<T> bySort(Sort sort) {
        return rep.findAll(sort);
    }

    public Page<T> getSimpleSortPaging(String sort, Integer pages, Integer pageSize) {
        Pageable page = PageRequest.of(pages, pageSize, Sort.by(sort));
        return rep.findAll(page);
    }

    public Page<T> getSimpleSortPagingOrder(String sort, String order, Integer pages, Integer pageSize) {
        return bySortPaged(Sort.by(new Sort.Order(Sort.Direction.fromString(order), sort)), pages, pageSize);
    }

    private Page<T> bySortPaged(Sort sort, Integer pages, Integer pageSize) {
        Pageable page = PageRequest.of(pages, pageSize, sort);
        return rep.findAll(page);
    }

    public T updateEntity(Long entryId, Map<String, Object> jsonMap) {
        T entity = getEntityById(entryId);
        jsonMap.forEach((getterName, updateValue) -> {
            Field field = ReflectionUtils.findField(entity.getClass(), getterName);
            field.setAccessible(true);
            ReflectionUtils.setField(field, entity, updateValue);
        });
        return rep.save(entity);
    }

}
