package lv.schoollibrary.schoolkids.integrations;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;

@JsonDeserialize(builder = EklaseSearchResponseDto.Builder.class)
public class EklaseSearchResponseDto implements Serializable {

    private String id;

    private String name;

    private String surname;

    public EklaseSearchResponseDto() {
    }

    private EklaseSearchResponseDto(Builder builder) {
        id = builder.id;
        name = builder.name;
        surname = builder.surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {

        private String id;

        private String name;

        private String surname;

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

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public EklaseSearchResponseDto build() {
            return new EklaseSearchResponseDto(this);
        }
    }
}
