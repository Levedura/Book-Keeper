package bookers.bookkeeper.user;

import bookers.bookkeeper.booklist.BookListController;
import bookers.bookkeeper.generics.AssemblerConverter;
import bookers.bookkeeper.generics.Converter;
import bookers.bookkeeper.user.dto.UserDTO;
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
public class UserModelAssembler implements AssemblerConverter<User, UserDTO> {

    private final Converter<User, UserDTO> converter;

    @Autowired
    public UserModelAssembler(Converter<User, UserDTO> converter) {
        this.converter = converter;
    }

    @Override
    @NonNull
    public EntityModel<UserDTO> toModel(@NonNull User entity) {
        UserDTO user = converter.toDto(entity);
        user.add(linkTo(methodOn(UserController.class).getById(entity.getId())).withSelfRel());
        user.add(linkTo(methodOn(BookListController.class).getUserList(entity.getUsername())).withSelfRel());
        return EntityModel.of(user);
    }

    @Override
    @NonNull
    public CollectionModel<EntityModel<UserDTO>> toCollectionModel(@NonNull Iterable<? extends User> entities) {
        List<EntityModel<UserDTO>> result = new ArrayList<>();
        entities.forEach(e -> result.add(toModel(e)));
        return CollectionModel.of(result, linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }

    @Override
    public Converter<User, UserDTO> getConverter() {
        return converter;
    }
}
