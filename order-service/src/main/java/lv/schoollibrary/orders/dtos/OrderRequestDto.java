package lv.schoollibrary.orders.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@JsonDeserialize(builder = OrderRequestDto.Builder.class)
public class OrderRequestDto implements Serializable {

    private final String schoolKidId;

    private final String bookId;

    private LocalDate takenUntil;

    private OrderRequestDto(Builder builder) {
        schoolKidId = builder.schoolKidId;
        bookId = builder.bookId;
        takenUntil = builder.takenUntil;
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


    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {

        private String schoolKidId;

        private String bookId;

        private LocalDate takenUntil;

        public Builder() {
        }

        public Builder schoolKidId(String schoolKidId) {
            this.schoolKidId = schoolKidId;
            return this;
        }

        public Builder bookId(String bookId) {
            this.bookId = bookId;
            return this;
        }

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
        public Builder takenUntil(LocalDate takenUntil) {
            this.takenUntil = takenUntil;
            return this;
        }

        public OrderRequestDto build() {
            return new OrderRequestDto(this);
        }
    }
}
