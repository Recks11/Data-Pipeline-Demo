package dev.rexijie.pipeline.realtime.ingestion.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class ApiResponse {
    private final String status;
    private final String message;
    private String key;

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getKey() {
        return key;
    }

    private ApiResponse(HttpStatus httpStatus) {
        this.status = httpStatus.getReasonPhrase();
        this.message = "SUCCESS";
    }

    public static ApiResponse ok() {
        return new ApiResponse(HttpStatus.OK);
    }
}
