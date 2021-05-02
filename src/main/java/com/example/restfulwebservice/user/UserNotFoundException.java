package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// HTTP Status code
// 2xx -> OK
// 4xx -> Client 클라이언트 잘못
// 5xx -> Server 서버측 오류
@ResponseStatus(HttpStatus.NOT_FOUND) //NOT_FOUND 로 설정하면 400번대 에러가뜸
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
