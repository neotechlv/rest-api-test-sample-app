package lv.schoollibrary.schoolkids.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lv.schoollibrary.schoolkids.dtos.ErrorDto;
import lv.schoollibrary.schoolkids.dtos.SchoolKidInfoDto;
import lv.schoollibrary.schoolkids.dtos.SchoolKidSearchDto;
import lv.schoollibrary.schoolkids.services.KidNotFoundException;
import lv.schoollibrary.schoolkids.services.SchoolKidsService;

@RestController
@RequestMapping(path = "/api/v1/schoolkid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SchoolKidsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolKidsController.class);

    private final SchoolKidsService schoolKidsService;

    @Autowired
    public SchoolKidsController(SchoolKidsService schoolKidsService) {
        this.schoolKidsService = schoolKidsService;
    }

    @ApiOperation(value = "Find a school kid by name and surname")
    @ApiResponses({
            @ApiResponse(code = 200,
                    message = "Returned when the school kid is found",
                    response = SchoolKidInfoDto.class
            ),
            @ApiResponse(code = 400,
                    message = "Returned when data is malformed or person not found (distinguished by error code)",
                    response = ErrorDto.class)
    })
    @PostMapping("/find")
    public SchoolKidInfoDto find(@RequestBody SchoolKidSearchDto dto) {
        LOGGER.debug("Finding school kid by name {} and surname {}", dto.getName(), dto.getSurname());
        return schoolKidsService.find(dto.getName(), dto.getSurname());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Request processing failed")
    public void handleGeneralError(Exception ex) {
        LOGGER.error("Request processing failed", ex);
    }

    @ExceptionHandler(KidNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Kid not found")
    private ErrorDto handleKidNotFoundException(KidNotFoundException ex) {
        return new ErrorDto.Builder()
                .code(ErrorDto.NOT_FOUND_ERROR_CODE)
                .message(ex.getMessage())
                .build();
    }

}