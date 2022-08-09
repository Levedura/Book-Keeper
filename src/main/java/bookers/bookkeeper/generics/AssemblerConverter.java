package bookers.bookkeeper.generics;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface AssemblerConverter<T,DTO> extends RepresentationModelAssembler<T, EntityModel<DTO>> {
    Converter<T,DTO> getConverter();
}
