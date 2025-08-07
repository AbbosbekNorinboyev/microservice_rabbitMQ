package uz.brb.card.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.brb.card.dto.Response;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandle {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public Response resourceNotFoundException(ResourceNotFoundException e) {
        return Response.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
    }
}