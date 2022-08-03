package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.author.dto.AuthorDTOConverter;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.enums.Genres;
import bookers.bookkeeper.generics.ConverterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDTOConverter extends ConverterImpl<Book, BookDTO> {

    AuthorDTOConverter authorDtoConverter;

    @Autowired
    public BookDTOConverter(AuthorDTOConverter authorDtoConverter) {
        this.authorDtoConverter = authorDtoConverter;
    }

    public BookDTO toDto(Book book) {
        BookDTO bookDto = new BookDTO();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setCover(book.getCover());
        bookDto.setAuthors(authorDtoConverter.listToDto(book.getAuthors()));
        bookDto.setGenres(Genres.listToValue(book.getGenres()));
        bookDto.setLanguage(book.getLanguage());
        bookDto.setPages(book.getPages());
        bookDto.setGlobalScore(book.getGlobalScore());
        bookDto.setSynopsis(book.getSynopsis());
        return bookDto;
    }

    public Book fromDto(BookDTO bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthors(authorDtoConverter.listFromDto(bookDto.getAuthors()));
        book.setCover(bookDto.getCover());
        book.setGenres(Genres.listToGenres(bookDto.getGenres()));
        book.setLanguage(bookDto.getLanguage());
        book.setPages(bookDto.getPages());
        book.setGlobalScore(bookDto.getGlobalScore());
        book.setSynopsis(bookDto.getSynopsis());
        return book;
    }

}
