package lv.schoollibrary.schoolkids.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lv.schoollibrary.schoolkids.integrations.EklaseSearchResponseDto;

@Mapper(uses = EntityFactory.class)
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    SchoolKidInfoDto fromResponseDto(EklaseSearchResponseDto source);
}
