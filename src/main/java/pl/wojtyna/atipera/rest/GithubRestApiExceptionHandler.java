package pl.wojtyna.atipera.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.wojtyna.atipera.repository.UserDoesNotExistException;

@ControllerAdvice
public class GithubRestApiExceptionHandler {

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<String> handleUserDoesntExistException(UserDoesNotExistException exception) {
        return ResponseEntity.status(404).header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                             .body("""
                                   {
                                       "status": 404,
                                       "message": "User %s doesn't exist"
                                   }
                                   """.formatted(exception.getUser()));
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<String> handleMediaTypeException(HttpMediaTypeNotAcceptableException exception) {
        return ResponseEntity.status(406).header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                             .body("""
                                   {
                                       "status": 406,
                                       "message": "%s"
                                   }
                                   """.formatted(exception.getMessage()));
    }
}
