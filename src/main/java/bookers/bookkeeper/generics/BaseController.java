package bookers.bookkeeper.generics;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<T, DTO,C extends Converter<T,DTO>, S extends IService<T>> {

    protected final S service;
    protected final C converter;

    public BaseController(S service, C converter) {
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
