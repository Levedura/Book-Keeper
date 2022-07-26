package bookers.bookkeeper.author.dto;

import bookers.bookkeeper.Converter;
import bookers.bookkeeper.author.Author;
import bookers.bookkeeper.book.Book;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorDtoConverter extends Converter<AuthorDto, Author> {


    @Override
    public Author fromDto(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setFavorites(authorDto.getFavorites());
        return author;
    }

    @Override
    public AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(author.getName());
        authorDto.setBooks(author.getBooks().stream().map(Book::getId).collect(Collectors.toList()));
        authorDto.setId(author.getId());
        authorDto.setFavorites(author.getFavorites());
        return authorDto;
    }
}
