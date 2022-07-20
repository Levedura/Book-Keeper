package bookers.bookkeeper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseService<T, Repository extends JpaRepository<T, Long>> {

    private final Repository rep;

    public BaseService(Repository rep) {
        this.rep = rep;
    }

    public Repository getRep() {
        return rep;
    }

    public List<T> getAllEntities(){
        return rep.findAll();
    }

    public List<T> saveEntities(List<T> entities){
        return rep.saveAll(entities);
    }

    public T saveEntity(T entity) {
        return rep.save(entity);
    }

    public T findEntityById(Long entityId) {
        return rep.findById(entityId).orElseThrow(() -> new IllegalStateException("Entity not found"));
    }

    public ResponseEntity<Long> deleteEntityById(Long entityId) {
        if (!rep.existsById(entityId)) {
            return new ResponseEntity<>(entityId, HttpStatus.NOT_FOUND);
        }
        rep.deleteById(entityId);
        return new ResponseEntity<>(entityId, HttpStatus.OK);
    }


}
