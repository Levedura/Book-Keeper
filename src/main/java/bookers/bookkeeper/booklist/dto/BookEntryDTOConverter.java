package bookers.bookkeeper.booklist.dto;

import bookers.bookkeeper.generics.ConverterImpl;
import bookers.bookkeeper.book.dto.BookDTOConverter;
import bookers.bookkeeper.booklist.BookEntry;
import bookers.bookkeeper.booklist.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookEntryDTOConverter extends ConverterImpl<BookEntry, BookEntryDTO> {

    @Autowired
    public BookEntryDTOConverter(BookDTOConverter bookDTOConverter, BookListService bookService) {
        this.bookDTOConverter = bookDTOConverter;
        this.bookListService = bookService;
    }


    BookDTOConverter bookDTOConverter;
    BookListService bookListService;

    @Override
    public BookEntryDTO toDto(BookEntry bookEntry) {
        BookEntryDTO bookEntryDTO = new BookEntryDTO();
        bookEntryDTO.setUserscore(bookEntry.getUserscore());
        bookEntryDTO.setBook(bookDTOConverter.toDto(bookEntry.getBook()));
        bookEntryDTO.setDateAdded(bookEntry.getDateAdded());
        bookEntryDTO.setDateFinished(bookEntry.getDateFinished());
        bookEntryDTO.setPagesRead(bookEntry.getPagesRead());
        bookEntryDTO.setStatus(bookEntry.getStatus());
        return bookEntryDTO;
    }

    @Override
    public BookEntry fromDto(BookEntryDTO bookEntryDto) {
       BookEntry bookEntry = new BookEntry();
       bookEntry.setDateAdded(bookEntryDto.getDateAdded());
       bookEntry.setDateFinished(bookEntryDto.getDateFinished());
       bookEntry.setUserscore(bookEntryDto.getUserscore());
       bookEntry.setPagesRead(bookEntryDto.getPagesRead());
       bookEntry.setStatus(bookEntryDto.getStatus());
       return bookEntry;

    }
}
