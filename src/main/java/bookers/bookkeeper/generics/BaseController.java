package bookers.bookkeeper.generics;

import bookers.bookkeeper.Converter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<T, DTO ,Repository extends GenericRepository<T>> {

    protected final BaseService<T,Repository> service;
    protected final Converter<DTO, T> converter;

    public BaseController(BaseService<T,Repository> service, Converter<DTO, T> converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping(value = "")
    public List<DTO> getAll() {
        return converter.listToDto(service.getAllEntities());
    }

    @GetMapping(value ="/{id}")
    public DTO getById(@PathVariable Long id){
        return converter.toDto(service.getEntityById(id));
    }

    @DeleteMapping(value = "/{id}")
    public Long deleteById(@PathVariable Long id){
        return service.deleteEntityById(id);
    }


}
