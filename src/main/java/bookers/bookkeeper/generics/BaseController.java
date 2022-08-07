package bookers.bookkeeper.generics;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class BaseController<T, DTO extends RepresentationModel<DTO> & DTOId, C extends Converter<T, DTO>, S extends Service<T>> {


    protected final S service;
    protected final C converter;

    public BaseController(S service, C converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping(value = "")
    public CollectionModel<DTO> getAll() {
        return getLinksAndDtos(service.getAllEntities());
    }

    @GetMapping(value = "/id={id}")
    public EntityModel<DTO> getById(@PathVariable Long id) {
        return getLinkAndDto(service.getEntityById(id));
    }

    @GetMapping(value = "sortBy/{sort}")
    public CollectionModel<DTO> getSimpleSort(@PathVariable String sort) {
        return getLinksAndDtos(service.getSimpleSort(sort));
    }

    @GetMapping(value = "/{sort}/{pages}/{pageSize}")
    public CollectionModel<DTO> getSimpleSortPaging(@PathVariable String sort, @PathVariable Integer pages, @PathVariable Integer pageSize) {
        return getLinksAndDtos(service.getSimpleSortPaging(sort, pages, pageSize).getContent());
    }

    @PostMapping(value = "")
    public EntityModel<DTO> add(@RequestBody DTO dto) {
        return getLinkAndDto(service.addEntity(converter.fromDto(dto)));
    }

    @PostMapping(value = "/list")
    public CollectionModel<DTO> addMultiple(@RequestBody List<DTO> dtoList) {
        List<T> result = new ArrayList<>();
        for (DTO dto : dtoList) {
            result.add(service.addEntity(converter.fromDto(dto)));
        }
        return getLinksAndDtos(result);
    }

    @PatchMapping(value = "/{id}")
    public EntityModel<DTO> updateEntity(@PathVariable Long id, @RequestBody Map<String, Object> json) {
        return getLinkAndDto(service.updateEntity(id, json));
    }


    @DeleteMapping(value = "/{id}")
    public Long deleteById(@PathVariable Long id) {
        return service.deleteEntityById(id);
    }

    public DTO addLinkToType(DTO dto) {
        return dto.add(linkTo(methodOn(this.getClass()).getById(dto.getId())).withSelfRel());
    }


    public CollectionModel<DTO> getLinksAndDtos(List<T> list) {
        List<DTO> dtos = converter.listToDto(list);
        dtos.forEach(this::addLinkToType);
        return CollectionModel.of(dtos);
    }

    public EntityModel<DTO> getLinkAndDto(T value) {
        DTO dto = converter.toDto(value);
        return EntityModel.of(addLinkToType(dto));
    }
}
