package bookers.bookkeeper.author;

import bookers.bookkeeper.author.dto.AuthorDTO;
import bookers.bookkeeper.book.BookController;
import bookers.bookkeeper.generics.AssemblerConverter;
import bookers.bookkeeper.generics.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AuthorModelAssembler implements AssemblerConverter<Author, AuthorDTO> {

    private final Converter<Author, AuthorDTO> converter;

    @Autowired
    public AuthorModelAssembler(Converter<Author, AuthorDTO> converter) {
        this.converter = converter;
    }

    @Override
    @NonNull
    public EntityModel<AuthorDTO> toModel(@NonNull Author entity) {
        AuthorDTO author = converter.toDto(entity);
        author.add(linkTo(methodOn(AuthorController.class).getById(entity.getId())).withSelfRel());
        author.add(linkTo(methodOn(BookController.class).getBookByAuthor(entity.getId())).withRel("books"));
        return EntityModel.of(author);
    }

    @Override
    @NonNull
    public CollectionModel<EntityModel<AuthorDTO>> toCollectionModel(@NonNull Iterable<? extends Author> entities) {
        List<EntityModel<AuthorDTO>> result = new ArrayList<>();
        entities.forEach(e -> result.add(toModel(e)));
        return CollectionModel.of(result, linkTo(methodOn(AuthorController.class).getAll()).withSelfRel());
    }

    @Override
    public Converter<Author, AuthorDTO> getConverter() {
        return converter;
    }
}
