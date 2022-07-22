package bookers.bookkeeper.author.dto;

import bookers.bookkeeper.author.Author;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.book.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorToDtoConverter {

    public static AuthorDto convertToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        List<Long> bookIds = author.getBooks().stream().map(Book::getId).collect(Collectors.toList());
        authorDto.setName(author.getName());
        authorDto.setBooks(bookIds);
        authorDto.setFavorites(author.getFavorites());
        return authorDto;
    }

    public static List<AuthorDto> convertListToDto(List<Author> authors) {
        return authors.stream()
                .map(AuthorToDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }
    public static Author convertFromDto(AuthorDto authorDto,List<Book> books){
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setBooks(books);
        author.setFavorites(authorDto.favorites);
        return author;
    }


}
