package lv.schoollibrary.orders.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String schoolKidId;

    private String bookId;

    private LocalDate takenUntil;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolKidId() {
        return schoolKidId;
    }

    public void setSchoolKidId(String schoolKidId) {
        this.schoolKidId = schoolKidId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getTakenUntil() {
        return takenUntil;
    }

    public void setTakenUntil(LocalDate takenUntil) {
        this.takenUntil = takenUntil;
    }
}
