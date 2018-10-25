package lv.schoollibrary.orders.domain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lv.schoollibrary.orders.dtos.OrderInfoDto;
import lv.schoollibrary.orders.dtos.OrderRequestDto;

@Mapper(uses = EntityFactory.class)
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    OrderInfoDto toResponseDto(Order source);

    Order fromRequestDto(OrderRequestDto source);
}
