package bookers.bookkeeper.author.dto;

import bookers.bookkeeper.author.Author;
import bookers.bookkeeper.generics.ConverterImpl;
import org.springframework.stereotype.Component;

@Component
public class AuthorDTOConverter extends ConverterImpl<Author, AuthorDTO> {

    @Override
    public Author fromDto(AuthorDTO authorDto) {
        Author author = new Author();
        if (authorDto.getId() != null) {
            author.setId(authorDto.getId());
        }
        author.setName(authorDto.getName());
        author.setFavorites(authorDto.getFavorites());
        return author;
    }

    @Override
    public AuthorDTO toDto(Author author) {
        AuthorDTO authorDto = new AuthorDTO();
        authorDto.setName(author.getName());
        authorDto.setId(author.getId());
        authorDto.setFavorites(author.getFavorites());
        return authorDto;
    }
}
