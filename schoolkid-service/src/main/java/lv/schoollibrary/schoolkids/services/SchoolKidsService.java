package lv.schoollibrary.schoolkids.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.schoollibrary.schoolkids.dtos.EntityMapper;
import lv.schoollibrary.schoolkids.dtos.SchoolKidInfoDto;
import lv.schoollibrary.schoolkids.integrations.EklaseService;

@Service
public class SchoolKidsService {

    private final EklaseService eklaseService;

    private final EntityMapper entityMapper;

    @Autowired
    public SchoolKidsService(EklaseService eklaseService, EntityMapper entityMapper) {
        this.eklaseService = eklaseService;
        this.entityMapper = entityMapper;
    }

    public SchoolKidInfoDto find(String name, String surname) {
        return eklaseService.find(name, surname)
                .map(entityMapper::fromResponseDto)
                .orElseThrow(() -> new KidNotFoundException(name, surname));
    }
}
