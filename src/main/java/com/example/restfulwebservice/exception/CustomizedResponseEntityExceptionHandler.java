package com.example.restfulwebservice.exception;

import com.example.restfulwebservice.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@RestController
@ControllerAdvice //모든 컨트롤러가 실행이될때 사전에 실행된다.
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                //언제 발생했는지, 메시지, 리퀘스트가 어떤내용을 가지고있을지에대함
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        // 500번대 에러.
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                //언제 발생했는지, 메시지, 리퀘스트가 어떤내용을 가지고있을지에대함
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        // 500번대 에러.
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
