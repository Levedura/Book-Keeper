package bookers.bookkeeper.booklist.dto;

import bookers.bookkeeper.book.dto.BookDTO;
import bookers.bookkeeper.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Data
public class BookEntryDTO {

    private Float userScore;

    private BookDTO book;

    private String userName;

    private Integer pagesRead;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateAdded;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateFinished;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String notes;


}


