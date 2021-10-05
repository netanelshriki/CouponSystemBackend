package com.net.couponSystem.advice;

import com.net.couponSystem.exceptions.CouponsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class Advice {
    @ExceptionHandler(value = {CouponsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handler1(Exception e){
        return e.getMessage();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleCatSystemException(Exception e){
        return e.getMessage();
    }

}
