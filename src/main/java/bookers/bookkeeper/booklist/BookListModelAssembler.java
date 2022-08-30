package bookers.bookkeeper.booklist;

import bookers.bookkeeper.book.BookController;
import bookers.bookkeeper.booklist.dto.BookEntryDTO;
import bookers.bookkeeper.generics.AssemblerConverter;
import bookers.bookkeeper.generics.Converter;
import bookers.bookkeeper.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookListModelAssembler implements AssemblerConverter<BookEntry, BookEntryDTO> {

    private final Converter<BookEntry, BookEntryDTO> converter;

    @Autowired
    public BookListModelAssembler(Converter<BookEntry, BookEntryDTO> converter) {
        this.converter = converter;
    }

    @Override
    @NonNull
    public EntityModel<BookEntryDTO> toModel(@NonNull BookEntry entity) {
        BookEntryDTO dto = converter.toDto(entity);
        //dto.add(linkTo(methodOn(BookListController.class).getById(entity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(BookController.class).getById(entity.getBook().getId())).withRel("books"));
        dto.add(linkTo(methodOn(UserController.class).getUserProfile(entity.getUser().getUsername())).withRel("user"));
        return EntityModel.of(dto);
    }

    @Override
    public Converter<BookEntry, BookEntryDTO> getConverter() {
        return converter;
    }
}
