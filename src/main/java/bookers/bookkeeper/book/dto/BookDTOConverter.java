package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.author.dto.AuthorDTOConverter;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.enums.Genres;
import bookers.bookkeeper.generics.ConverterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookDTOConverter extends ConverterImpl<Book, BookDTO> {

    AuthorDTOConverter authorDtoConverter;

    @Autowired
    public BookDTOConverter(AuthorDTOConverter authorDtoConverter) {
        this.authorDtoConverter = authorDtoConverter;
    }

    public BookDTO toDto(Book book) {
        BookDTO bookDto = new BookDTO();
        bookDto.setTitle(book.getTitle());
        bookDto.setCover(book.getCover());
        bookDto.setAuthors(authorDtoConverter.listToDto(book.getAuthors()));
        bookDto.setGenres(book.getGenres().stream().map(Genres::getName).collect(Collectors.toList()));
        bookDto.setLanguage(book.getLanguage());
        bookDto.setPages(book.getPages());
        bookDto.setGlobalscore(book.getGlobalscore());
        return bookDto;
    }

    public Book fromDto(BookDTO bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthors(authorDtoConverter.listFromDto(bookDto.getAuthors()));
        book.setCover(bookDto.getCover());
        book.setGenres(bookDto.getGenres().stream().map(Genres::valueOf).collect(Collectors.toList()));
        book.setLanguage(bookDto.getLanguage());
        book.setPages(bookDto.getPages());
        book.setGlobalscore(bookDto.getGlobalscore());
        return book;
    }
}
