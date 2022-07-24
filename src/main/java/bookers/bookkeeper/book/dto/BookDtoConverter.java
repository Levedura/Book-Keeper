package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.Converter;
import bookers.bookkeeper.author.dto.AuthorDtoConverter;
import bookers.bookkeeper.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter extends Converter<BookDto, Book> {

    AuthorDtoConverter authorDtoConverter;

    @Autowired
    public BookDtoConverter(AuthorDtoConverter authorDtoConverter) {
        this.authorDtoConverter = authorDtoConverter;
    }

    public BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setCover(book.getCover());
        bookDto.setAuthors(authorDtoConverter.listToDto(book.getAuthors()));
        bookDto.setGenres(book.getGenres());
        bookDto.setLanguage(book.getLanguage());
        bookDto.setPages(book.getPages());
        bookDto.setGlobalscore(book.getGlobalscore());
        return bookDto;
    }

    public Book fromDto(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthors(authorDtoConverter.listFromDto(bookDto.getAuthors()));
        book.setCover(bookDto.getCover());
        book.setGenres(bookDto.getGenres());
        book.setLanguage(bookDto.getLanguage());
        book.setPages(bookDto.getPages());
        book.setGlobalscore(bookDto.getGlobalscore());
        return book;
    }
}
