package bookers.bookkeeper.booklist.dto;

import bookers.bookkeeper.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class AddEntryDTO {
    private Long bookId;

    private Integer pagesRead;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateAdded;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateFinished;

    private Status status;

    private String notes;

    private float userScore;

}
