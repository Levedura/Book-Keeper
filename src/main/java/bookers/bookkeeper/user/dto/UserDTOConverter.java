package bookers.bookkeeper.user.dto;

import bookers.bookkeeper.booklist.dto.BookEntryDTOConverter;
import bookers.bookkeeper.generics.ConverterImpl;
import bookers.bookkeeper.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter extends ConverterImpl<User, UserDTO> {

    BookEntryDTOConverter bookEntryDTOConverter;

    @Autowired
    public UserDTOConverter(BookEntryDTOConverter bookEntryDTOConverter) {
        this.bookEntryDTOConverter = bookEntryDTOConverter;
    }

    @Override
    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        if (user.getUserlist() != null) {
            userDTO.setUserlist(bookEntryDTOConverter.listToDto(user.getUserlist()));
        }
        return userDTO;
    }

    @Override
    public User fromDto(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setUserlist(bookEntryDTOConverter.listFromDto(userDto.getUserlist()));
        return user;
    }
}
