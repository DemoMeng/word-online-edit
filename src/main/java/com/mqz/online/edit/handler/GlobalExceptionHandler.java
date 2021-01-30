package com.mqz.online.edit.handler;

import com.mqz.online.edit.result.WpsResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/23
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return WpsResponseBean.ERROR(e.getMessage());
    }


}
