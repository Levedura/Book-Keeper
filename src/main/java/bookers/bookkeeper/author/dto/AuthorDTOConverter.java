package bookers.bookkeeper.author.dto;

import bookers.bookkeeper.generics.ConverterImpl;
import bookers.bookkeeper.author.Author;
import bookers.bookkeeper.book.Book;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorDTOConverter extends ConverterImpl<Author, AuthorDTO> {


    @Override
    public Author fromDto(AuthorDTO authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setFavorites(authorDto.getFavorites());
        return author;
    }

    @Override
    public AuthorDTO toDto(Author author) {
        AuthorDTO authorDto = new AuthorDTO();
        authorDto.setName(author.getName());
        authorDto.setBooks(author.getBooks().stream().map(Book::getId).collect(Collectors.toList()));
        authorDto.setId(author.getId());
        authorDto.setFavorites(author.getFavorites());
        return authorDto;
    }
}
