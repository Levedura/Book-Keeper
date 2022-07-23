package bookers.bookkeeper.book.dto;

import bookers.bookkeeper.Converter;
import bookers.bookkeeper.book.Book;
import bookers.bookkeeper.enums.Genres;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    String title;
    String cover;
    List<Long> authors;
    List<Genres> genres;
    String language;
    Integer pages;
    Float score;

    @JsonIgnore
    public Converter<BookDto, Book> getConverter(){
        return new BookDtoConverter();
    }
}
