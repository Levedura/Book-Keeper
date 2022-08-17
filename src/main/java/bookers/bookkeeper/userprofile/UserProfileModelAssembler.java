package bookers.bookkeeper.userprofile;

import bookers.bookkeeper.generics.Converter;
import bookers.bookkeeper.user.UserController;
import bookers.bookkeeper.user.UserService;
import bookers.bookkeeper.userprofile.dto.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserProfileModelAssembler implements RepresentationModelAssembler<UserProfile, EntityModel<UserProfileDTO>> {

    Converter<UserProfile, UserProfileDTO> converter;

    @Autowired
    public UserProfileModelAssembler(Converter<UserProfile, UserProfileDTO> converter) {
        this.converter = converter;
    }

    @Override
    @NonNull
    public EntityModel<UserProfileDTO> toModel(@NonNull UserProfile entity) {
        UserProfileDTO userProfile = converter.toDto(entity);
        userProfile.add(linkTo(methodOn(UserController.class).getUserProfile(entity.getUser().getUsername())).withRel("user"));
        userProfile.add(linkTo(methodOn(UserService.class).getUserByNameWithCheck(entity.getUser().getUsername())).withRel("user"));
        return EntityModel.of(userProfile);
    }


}
