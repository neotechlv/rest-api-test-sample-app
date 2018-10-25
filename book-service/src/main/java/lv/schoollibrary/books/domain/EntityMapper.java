package lv.schoollibrary.books.domain;

import lv.schoollibrary.books.dtos.BookRequestDto;
import lv.schoollibrary.books.dtos.BookResponseDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = EntityFactory.class)
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    BookResponseDto toResponseDto(Book source);

    Book fromRequestDto(BookRequestDto source);
}
