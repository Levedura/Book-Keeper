package bookers.bookkeeper.generics;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseController<T, DTO, C extends Converter<T, DTO>, S extends Service<T>> {

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

    @GetMapping(value = "/id={id}")
    public DTO getById(@PathVariable Long id) {
        return converter.toDto(service.getEntityById(id));
    }

    @GetMapping(value = "sortBy/{sort}")
    public List<DTO> getSimpleSort(@PathVariable String sort) {
        return converter.listToDto(service.getSimpleSort(sort));
    }

    @GetMapping(value = "/{sort}/{pages}/{pageSize}")
    public List<DTO> getSimpleSortPaging(@PathVariable String sort, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return converter.listToDto(service.getSimpleSortPaging(sort, pages, pageSize).getContent());
    }

    @PostMapping(value = "")
    public DTO add(@RequestBody DTO dto) {
        return converter.toDto(service.addEntity(converter.fromDto(dto)));
    }

    @PostMapping(value = "/list")
    public List<DTO> addMultiple(@RequestBody List<DTO> dtoList) {
        List<DTO> result = new ArrayList<>();
        dtoList.forEach(dto -> result.add(add(dto)));
        return result;
    }

    @PatchMapping(value = "/{id}")
    public DTO updateEntity(@PathVariable Long id, @RequestBody Map<String, Object> json) {
        return converter.toDto(service.updateEntity(id, json));
    }


    @DeleteMapping(value = "/{id}")
    public Long deleteById(@PathVariable Long id) {
        return service.deleteEntityById(id);
    }

}
