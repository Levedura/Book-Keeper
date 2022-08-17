package bookers.bookkeeper.userprofile.dto;

import bookers.bookkeeper.author.dto.AuthorDTOConverter;
import bookers.bookkeeper.book.dto.BookDTOConverter;
import bookers.bookkeeper.generics.ConverterImpl;
import bookers.bookkeeper.userprofile.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileDTOConverter extends ConverterImpl<UserProfile, UserProfileDTO> {

    BookDTOConverter bookDTOConverter;
    AuthorDTOConverter authorDTOConverter;

    public UserProfileDTOConverter(BookDTOConverter bookDTOConverter, AuthorDTOConverter authorDTOConverter) {
        this.bookDTOConverter = bookDTOConverter;
        this.authorDTOConverter = authorDTOConverter;
    }

    @Override
    public UserProfileDTO toDto(UserProfile userProfile) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setProfileImage(userProfile.getUser().getImage());
        userProfileDTO.setFavoriteBooks(bookDTOConverter.listToDto(userProfile.getFavoriteBooks()));
        userProfileDTO.setFavoriteAuthors(authorDTOConverter.listToDto(userProfile.getFavoriteAuthors()));

        if (userProfile.getUser() != null) {
            userProfileDTO.setUserName(userProfile.getUser().getUsername());
        }

        userProfileDTO.setBooksRead(userProfile.getBooksRead());
        userProfileDTO.setMeanScore(userProfile.getMeanScore());
        userProfileDTO.setPagesRead(userProfile.getPagesRead());
        return userProfileDTO;
    }

    @Override
    public UserProfile fromDto(UserProfileDTO dto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setMeanScore(dto.getMeanScore());
        userProfile.setPagesRead(dto.getPagesRead());
        userProfile.setBooksRead(dto.getBooksRead());
        userProfile.setFavoriteBooks(bookDTOConverter.listFromDto(dto.getFavoriteBooks()));
        return userProfile;
    }

}
