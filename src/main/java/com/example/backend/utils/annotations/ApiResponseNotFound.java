package com.example.backend.utils.annotations;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.example.backend.security.models.response.ErrorResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "404", description = "Requested entity not found", content = {
        @Content(mediaType = "application/json", schema =
        @Schema(implementation = ErrorResponse.class))
})
public @interface ApiResponseNotFound { }