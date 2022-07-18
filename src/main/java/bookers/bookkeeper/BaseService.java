package bookers.bookkeeper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


public class BaseService<T, TRep extends JpaRepository<T, Long>> {

    private final TRep trep;

    public BaseService(TRep trep) {
        this.trep = trep;
    }
    public T save(T entity) {
        return trep.save(entity);
    }

    public List<T> saveAll(List<T> entities) {
        return trep.saveAll(entities);
    }

    public List<T> findAll() {
        return trep.findAll();
    }

    public T findById(Long entityId) {
        return trep.findById(entityId).orElseThrow(() -> new IllegalStateException("Entity not found"));
    }

    public void delete(T entity) {
        trep.delete(entity);
    }

    public ResponseEntity<Long> deleteById(Long entityId) {
        if (!trep.existsById(entityId)) {
            return new ResponseEntity<>(entityId, HttpStatus.NOT_FOUND);
        }
        trep.deleteById(entityId);
        return new ResponseEntity<>(entityId, HttpStatus.OK);
    }

    public TRep getTrep() {
        return trep;
    }
}
