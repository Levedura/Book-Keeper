package bookers.bookkeeper.booklist.dto;

import bookers.bookkeeper.book.dto.BookDTO;
import bookers.bookkeeper.enums.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Data
public class BookEntryDTO {

    private float userscore;

    private BookDTO book;

    private String username;

    private Integer pagesRead;

    private Date dateAdded;

    private Date dateFinished;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String notes;


}


