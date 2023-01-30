package com.zerobase.member.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zerobase.member.member.dto.InvalidArgumentsDto;
import com.zerobase.member.model.WebResponse;
import com.zerobase.member.model.WebResponseData;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public WebResponse customExceptionHandler(CustomException ex) {
        return WebResponse.error(ex.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponseData<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> fieldNames = new ArrayList<>();
        BindingResult result = ex.getBindingResult();
        for(FieldError fe : result.getFieldErrors()) {
            if(!fieldNames.contains(fe.getField())) {
                fieldNames.add(fe.getField());
            }            
        }

        return WebResponseData.error(ErrorCode.INVALID_ARGUMENT, InvalidArgumentsDto.builder().fieldNames(fieldNames).build());
    }
}