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

    @GetMapping(value = "", params = "sort")
    CollectionModel<EntityModel<DTO>> getSimpleSort(@RequestParam String sort);

    @GetMapping(value = "", params = {"sort", "order"})
    CollectionModel<EntityModel<DTO>> getSimpleSortOrder(@RequestParam String sort, String order);

    @GetMapping(value = "", params = {"sort", "pages", "pageSize"})
    CollectionModel<EntityModel<DTO>> getSimpleSortPaging(@RequestParam String sort, @RequestParam Integer page, @RequestParam Integer pageSize);

    @GetMapping(value = "", params = {"sort", "order", "pages", "pageSize"})
    CollectionModel<EntityModel<DTO>> getSimpleSortPagingOrder(@RequestParam String sort, @RequestParam String order, @RequestParam Integer page, @RequestParam Integer pageSize);

    @PostMapping(value = "")
    EntityModel<DTO> add(@RequestBody DTO dto);

    @PostMapping(value = "/list")
    CollectionModel<EntityModel<DTO>> addMultiple(@RequestBody List<DTO> dtoList);

    @PatchMapping(value = "/{id}")
    EntityModel<DTO> updateEntity(@PathVariable Long id, @RequestBody Map<String, Object> json);

    @DeleteMapping(value = "/{id}")
    Long deleteById(@PathVariable Long id);
}
