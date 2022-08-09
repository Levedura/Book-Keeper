package bookers.bookkeeper.book;

import bookers.bookkeeper.author.AuthorController;
import bookers.bookkeeper.book.dto.BookDTO;
import bookers.bookkeeper.generics.AssemblerConverter;
import bookers.bookkeeper.generics.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookModelAssembler implements AssemblerConverter<Book, BookDTO> {

    private final Converter<Book, BookDTO> converter;

    @Autowired
    public BookModelAssembler(Converter<Book, BookDTO> converter) {
        this.converter = converter;
    }

    @Override
    @NonNull
    public EntityModel<BookDTO> toModel(@NonNull Book entity) {
        BookDTO dto = converter.toDto(entity);
        dto.add(linkTo(methodOn(BookController.class).getById(entity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(AuthorController.class).getAuthorByBook(entity.getId())).withRel("author"));
        return EntityModel.of(dto);
    }

    @Override
    @NonNull
    public CollectionModel<EntityModel<BookDTO>> toCollectionModel(@NonNull Iterable<? extends Book> entities) {
        List<EntityModel<BookDTO>> result = new ArrayList<>();
        entities.forEach(e -> result.add(toModel(e)));
        return CollectionModel.of(result, linkTo(methodOn(BookController.class).getAll()).withSelfRel());
    }

    @Override
    public Converter<Book, BookDTO> getConverter() {
        return converter;
    }
}
