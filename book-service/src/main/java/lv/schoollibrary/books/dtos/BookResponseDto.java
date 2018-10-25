package lv.schoollibrary.books.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;
import java.util.Objects;

@JsonDeserialize(builder = BookResponseDto.Builder.class)
public class BookResponseDto implements Serializable {

    private String id;

    private String name;

    private String isbn;

    public BookResponseDto() {
    }

    private BookResponseDto(Builder builder) {
        id = builder.id;
        name = builder.name;
        isbn = builder.isbn;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookResponseDto that = (BookResponseDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isbn);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {

        private String id;

        private String name;

        private String isbn;

        public Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookResponseDto build() {
            return new BookResponseDto(this);
        }
    }
}
