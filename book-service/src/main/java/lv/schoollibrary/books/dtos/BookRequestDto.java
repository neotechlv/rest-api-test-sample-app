package lv.schoollibrary.books.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;

@JsonDeserialize(builder = BookRequestDto.Builder.class)
public class BookRequestDto implements Serializable {

    private String name;

    private String isbn;

    public BookRequestDto() {
    }

    private BookRequestDto(Builder builder) {
        name = builder.name;
        isbn = builder.isbn;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {

        private String name;

        private String isbn;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookRequestDto build() {
            return new BookRequestDto(this);
        }
    }
}
