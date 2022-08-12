package bookers.bookkeeper.generics;

import bookers.bookkeeper.booklist.BookListController;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.core.GenericTypeResolver;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public abstract class AssemblerConverterImpl<T, DTO,Con
        , C extends Converter<T, DTO>> implements AssemblerConverter<T, DTO> {

    protected final C converter;

    public AssemblerConverterImpl(C converter) {
        this.converter = converter;
    }

    public Converter<T, DTO> getConverter() {
        return converter;
    }
}
