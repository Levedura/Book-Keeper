package bookers.bookkeeper.generics;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface IController<T, DTO, S extends Service<T>, M extends AssemblerConverter<T, DTO>> {
    @GetMapping(value = "")
    CollectionModel<EntityModel<DTO>> getAll();

    @GetMapping(value = "/{id}")
    EntityModel<DTO> getById(@PathVariable Long id);

    @GetMapping(value = "sortBy/{sort}")
    CollectionModel<EntityModel<DTO>> getSimpleSort(@PathVariable String sort);

    @GetMapping(value = "sortBy/{sort}/{order}")
    CollectionModel<EntityModel<DTO>> getSimpleSortOrder(@PathVariable String sort, @PathVariable String order);

    @GetMapping(value = "/{sort}/{pages}/{pageSize}")
    CollectionModel<EntityModel<DTO>> getSimpleSortPaging(@PathVariable String sort, @PathVariable Integer pages, @PathVariable Integer pageSize);

    @GetMapping(value = "/{sort}/{order}/{pages}/{pageSize}")
    CollectionModel<EntityModel<DTO>> getSimpleSortPagingOrder(@PathVariable String sort, @PathVariable String order, @PathVariable Integer pages, @PathVariable Integer pageSize);

    @PostMapping(value = "")
    EntityModel<DTO> add(@RequestBody DTO dto);

    @PostMapping(value = "/list")
    CollectionModel<EntityModel<DTO>> addMultiple(@RequestBody List<DTO> dtoList);

    @PatchMapping(value = "/{id}")
    EntityModel<DTO> updateEntity(@PathVariable Long id, @RequestBody Map<String, Object> json);

    @DeleteMapping(value = "/{id}")
    Long deleteById(@PathVariable Long id);
}
