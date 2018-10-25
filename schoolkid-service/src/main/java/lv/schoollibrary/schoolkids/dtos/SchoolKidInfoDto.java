package lv.schoollibrary.schoolkids.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;
import java.util.Objects;

@JsonDeserialize(builder = SchoolKidInfoDto.Builder.class)
public class SchoolKidInfoDto implements Serializable {

    private String id;

    private String name;

    private String surname;

    public SchoolKidInfoDto() {
    }

    private SchoolKidInfoDto(Builder builder) {
        id = builder.id;
        name = builder.name;
        surname = builder.surname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getsurname() {
        return surname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolKidInfoDto that = (SchoolKidInfoDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
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

        public SchoolKidInfoDto build() {
            return new SchoolKidInfoDto(this);
        }
    }
}
