package lv.schoollibrary.schoolkids.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

import lv.schoollibrary.schoolkids.config.EklaseSettings;

import static java.lang.System.lineSeparator;

@Repository
public class EklaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EklaseService.class);

    private final ObjectMapper objectMapper;

    private final RestTemplate restTemplate;

    private final EklaseSettings settings;

    @Autowired
    public EklaseService(ObjectMapper objectMapper, RestTemplate eklaseRestTemplate, EklaseSettings settings) {
        this.objectMapper = objectMapper;
        this.restTemplate = eklaseRestTemplate;
        this.settings = settings;
    }

    public Optional<EklaseSearchResponseDto> find(String name, String surname) {
        EklaseSearchRequestDto requestDto = new EklaseSearchRequestDto(name, surname);

        LOGGER.debug("Sending request to {}: {}, token {}", settings.getSearchUri(), requestDto);
        ResponseEntity<String> response =
                restTemplate.exchange(settings.getSearchUri(), HttpMethod.POST, new HttpEntity<>(requestDto), String.class);

        String responseDescription = response.toString();
        LOGGER.debug("Received response from {}: {}", settings.getSearchUri(), responseDescription);

        HttpStatus statusCode = response.getStatusCode();
        String responseBody = response.getBody();
        if (HttpStatus.OK.equals(statusCode)) {
            EklaseSearchResponseDto result = jsonToObject(responseBody, EklaseSearchResponseDto.class);
            return Optional.of(result);
        } else if (HttpStatus.NOT_FOUND.equals(statusCode)) {
            LOGGER.info("Request was reported as being invalid, full response was{} {}", lineSeparator(), responseDescription);
            return Optional.empty();
        } else {
            LOGGER.warn("Request to Eklase {} with payload {} returned an unspecified status {}", settings.getSearchUri(), requestDto, statusCode);
            throw new UnsupportedOperationException();
        }

    }

    private <T> T jsonToObject(String json, Class<T> objectClass) {
        try {
            return objectMapper.readValue(json, objectClass);
        } catch (IOException e) {
            String message = "Failed to parse the response from Eklase";
            LOGGER.error(message, e);
            throw new EklaseIntegrationException(message, e);
        }
    }

}
