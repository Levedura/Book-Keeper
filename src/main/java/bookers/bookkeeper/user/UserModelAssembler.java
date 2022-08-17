package bookers.bookkeeper.user;

import bookers.bookkeeper.booklist.BookListController;
import bookers.bookkeeper.generics.AssemblerConverter;
import bookers.bookkeeper.generics.Converter;
import bookers.bookkeeper.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

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
        user.add(linkTo(methodOn(UserController.class).getUserProfile(entity.getUsername())).withSelfRel());
        user.add(linkTo(methodOn(BookListController.class).getUserList(entity.getUsername())).withSelfRel());
        return EntityModel.of(user);
    }


    @Override
    public Converter<User, UserDTO> getConverter() {
        return converter;
    }
}
