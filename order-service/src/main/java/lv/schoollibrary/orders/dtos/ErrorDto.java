package lv.schoollibrary.orders.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = ErrorDto.Builder.class)
public class ErrorDto {

    private String code;

    private String message;

    private ErrorDto(Builder builder) {
        code = builder.code;
        message = builder.message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {

        private String code;

        private String message;

        public Builder() {
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorDto build() {
            return new ErrorDto(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDto errorDto = (ErrorDto) o;
        return Objects.equals(code, errorDto.code) &&
                Objects.equals(message, errorDto.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }
}
