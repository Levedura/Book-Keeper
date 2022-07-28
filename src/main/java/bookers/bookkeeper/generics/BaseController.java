package bookers.bookkeeper.generics;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BaseController<T, DTO, C extends Converter<T, DTO>, S extends IService<T>> {

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

    @GetMapping(value = "/{id}")
    public DTO getById(@PathVariable Long id) {
        return converter.toDto(service.getEntityById(id));
    }

    @GetMapping(value = "/{pages}&{pageSize}")
    public List<DTO> getSimpleOrderedBy(@PathVariable Integer pages, @PathVariable Integer pageSize, BiFunction<Integer,Integer, List<T>> orderFunction) {
        return converter.listToDto(orderFunction.apply(pages, pageSize));
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

    @DeleteMapping(value = "/{id}")
    public Long deleteById(@PathVariable Long id) {
        return service.deleteEntityById(id);
    }

    @PostMapping(value = "/group")
    public <A> List<DTO> getEntitiesByList(@RequestBody List<A> list, Function<List<A>, List<T>> getGroupFunction) {
        return converter.listToDto(getGroupFunction.apply(list));
    }

}
