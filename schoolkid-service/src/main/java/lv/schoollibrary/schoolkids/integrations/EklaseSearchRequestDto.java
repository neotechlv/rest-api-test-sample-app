package lv.schoollibrary.schoolkids.integrations;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class EklaseSearchRequestDto {

    private final String name;

    private final String surname;

    public EklaseSearchRequestDto(String name, String surname) {
        checkArgument(isNotBlank(name), "Name should not be empty");
        checkArgument(isNotBlank(surname), "Surname should not be empty");

        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EklaseSearchRequestDto that = (EklaseSearchRequestDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "EklaseSearchRequestDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }


}
