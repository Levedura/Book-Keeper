package bookers.bookkeeper.author.dto;

import bookers.bookkeeper.Converter;
import bookers.bookkeeper.author.Author;
import bookers.bookkeeper.book.dto.BookDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoConverter extends Converter<AuthorDto, Author> {
    BookDtoConverter bookDtoConverter;

    @Autowired
    public AuthorDtoConverter(BookDtoConverter bookDtoConverter) {
        this.bookDtoConverter = bookDtoConverter;
    }

    @Override
    public Author fromDto(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setBooks(bookDtoConverter.listFromDto(authorDto.books));
        author.setFavorites(authorDto.favorites);
        return author;
    }

    @Override
    public AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(author.getName());
        authorDto.setBooks(bookDtoConverter.listToDto(author.getBooks()));
        authorDto.setFavorites(author.getFavorites());
        return authorDto;
    }
}
