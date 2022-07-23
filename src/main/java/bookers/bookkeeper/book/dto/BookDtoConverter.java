package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.DtoConverter;
import bookers.bookkeeper.book.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter extends DtoConverter<BookDto,Book> {

    @Override
    public BookDto convertToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setCover(book.getCover());
        bookDto.setGenres(book.getGenres());
        bookDto.setLanguage(book.getLanguage());
        bookDto.setPages(book.getPages());
        bookDto.setScore(book.getGlobalscore());
        return bookDto;
    }

    @Override
    public Book convertFromDto(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setCover(bookDto.getCover());
        book.setGenres(bookDto.getGenres());
        book.setLanguage(bookDto.getLanguage());
        book.setPages(bookDto.getPages());
        book.setGlobalscore(bookDto.getScore());
        return book;
    }
}
