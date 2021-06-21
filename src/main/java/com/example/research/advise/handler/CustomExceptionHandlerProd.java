/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.research.advise.handler;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.example.research.dto.Error;

/**
 * Обработка прочих исключений в контроллере.
 * Для остальных профилей не выводится stacktrace.
 * 
 */
@Component
@Profile("!(local | dev | staging)")
@Slf4j
public class CustomExceptionHandlerProd implements CustomExceptionHandler {

    @Override
    public ResponseEntity<Error> handleOtherExceptions(Exception e,
            HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = prehandleOtherExceptions(e);
        ByteArrayOutputStream ba = (ByteArrayOutputStream)map.get("ba");
        List<String> detail = (List<String>) map.get("detail");
        
        log.error("handle custom exception, stacktrace={}", ba);

        return handleOtherExceptions(e, detail);
    }
}