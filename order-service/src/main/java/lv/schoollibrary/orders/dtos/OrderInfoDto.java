package lv.schoollibrary.orders.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@JsonDeserialize(builder = OrderInfoDto.Builder.class)
public class OrderInfoDto implements Serializable {

    private String id;

    private String schoolKidId;

    private String bookId;

    private LocalDate takenUntil;

    public OrderInfoDto() {
    }

    private OrderInfoDto(OrderInfoDto.Builder builder) {
        id = builder.id;
        schoolKidId = builder.schoolKidId;
        bookId = builder.bookId;
        takenUntil = builder.takenUntil;
    }

    public String getId() {
        return id;
    }

    public String getSchoolKidId() {
        return schoolKidId;
    }

    public String getBookId() {
        return bookId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    public LocalDate getTakenUntil() {
        return takenUntil;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSchoolKidId(String schoolKidId) {
        this.schoolKidId = schoolKidId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setTakenUntil(LocalDate takenUntil) {
        this.takenUntil = takenUntil;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {

        private String id;

        private String schoolKidId;

        private String bookId;

        private LocalDate takenUntil;

        public Builder() {
        }

        public OrderInfoDto.Builder id(String id) {
            this.id = id;
            return this;
        }

        public OrderInfoDto.Builder schoolKidId(String schoolKidId) {
            this.schoolKidId = schoolKidId;
            return this;
        }

        public OrderInfoDto.Builder bookId(String bookId) {
            this.bookId = bookId;
            return this;
        }

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
        public OrderInfoDto.Builder takenUntil(LocalDate takenUntil) {
            this.takenUntil = takenUntil;
            return this;
        }

        public OrderInfoDto build() {
            return new OrderInfoDto(this);
        }
    }
}
