/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.research.advise.handler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.research.advise.ControllerAdvice;
import com.example.research.dto.Error;

public interface CustomExceptionHandler {

    ResponseEntity<Error> handleOtherExceptions(Exception e,
        HttpServletRequest request, HttpServletResponse response);

    default Map<String, Object> prehandleOtherExceptions(Exception e) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(ba);
        List<String> causeMessage = new ArrayList<>();
        if (e.getCause() != null) {
            causeMessage.add(e.getCause().getMessage());
            if (e.getCause().getCause() != null) {
                e.getCause().getCause().printStackTrace(pr);
                causeMessage.add(e.getCause().getCause().getMessage());
            } else {
                e.getCause().printStackTrace(pr);
            }
        } else {
            e.printStackTrace(pr);
        }

        List<String> detail = new ArrayList<>();
        detail.add(e.getMessage());
        detail.addAll(causeMessage);

        Map<String, Object> map = new HashMap<>();
        map.put("ba", ba);
        map.put("detail", detail);

        return map;
    }

    default ResponseEntity<Error> handleOtherExceptions(Exception e, List<String> detail) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(ControllerAdvice.X_STATUS, "Error");

        return new ResponseEntity<>(
                new Error(
                        "Internal Server Error",
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        System.currentTimeMillis(),
                        detail
                ),
                headers,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
