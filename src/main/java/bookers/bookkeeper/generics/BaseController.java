package bookers.bookkeeper.generics;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public class BaseController<T, DTO, S extends Service<T>, M extends AssemblerConverter<T, DTO>> implements IController<T, DTO, S, M> {


    protected final S service;
    protected final M modelAssembler;

    public BaseController(S service, M modelAssembler) {
        this.service = service;
        this.modelAssembler = modelAssembler;
    }

    @Override
    @GetMapping(value = "")
    public CollectionModel<EntityModel<DTO>> getAll() {
        return modelAssembler.toCollectionModel(service.getAllEntities());
    }

    @Override
    @GetMapping(value = "/{id}")
    public EntityModel<DTO> getById(@PathVariable Long id) {
        return modelAssembler.toModel(service.getEntityById(id));
    }

    @Override
    @GetMapping(value = "sortBy/{sort}")
    public CollectionModel<EntityModel<DTO>> getSimpleSort(@PathVariable String sort) {
        return modelAssembler.toCollectionModel(service.getSimpleSort(sort));
    }

    @Override
    @GetMapping(value = "sortBy/{sort}/{order}")
    public CollectionModel<EntityModel<DTO>> getSimpleSortOrder(@PathVariable String sort, @PathVariable String order) {
        return modelAssembler.toCollectionModel(service.getSimpleSortOrder(sort, order));
    }

    @Override
    @GetMapping(value = "/{sort}/{pages}/{pageSize}")
    public CollectionModel<EntityModel<DTO>> getSimpleSortPaging(@PathVariable String sort, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return modelAssembler.toCollectionModel(service.getSimpleSortPaging(sort, pages, pageSize).getContent());
    }

    @Override
    @GetMapping(value = "/{sort}/{order}/{pages}/{pageSize}")
    public CollectionModel<EntityModel<DTO>> getSimpleSortPagingOrder(@PathVariable String sort, @PathVariable String order, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return modelAssembler.toCollectionModel(service.getSimpleSortPagingOrder(sort, order, pages, pageSize).getContent());
    }

    @Override
    @PostMapping(value = "")
    public EntityModel<DTO> add(@RequestBody DTO dto) {
        return modelAssembler.toModel(service.addEntity(modelAssembler.getConverter().fromDto(dto)));
    }

    @Override
    @PostMapping(value = "/list")
    public CollectionModel<EntityModel<DTO>> addMultiple(@RequestBody List<DTO> dtoList) {
        return modelAssembler.toCollectionModel(service.addListEntities(modelAssembler.getConverter().listFromDto(dtoList)));

    }

    @Override
    @PatchMapping(value = "/{id}")
    public EntityModel<DTO> updateEntity(@PathVariable Long id, @RequestBody Map<String, Object> json) {
        return modelAssembler.toModel(service.updateEntity(id, json));
    }


    @Override
    @DeleteMapping(value = "/{id}")
    public Long deleteById(@PathVariable Long id) {
        return service.deleteEntityById(id);
    }

}
