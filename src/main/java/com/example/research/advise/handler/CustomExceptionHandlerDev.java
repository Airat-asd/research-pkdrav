package com.example.research.advise.handler;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.example.research.dto.Error;

/**
 * Обработка прочих исключений в контроллере.
 * Для DEV и LOCAL профилей выводится stacktrace.
 *
 */
@Component
@Profile("local | dev | staging")
public class CustomExceptionHandlerDev implements CustomExceptionHandler {

    @Override
    public ResponseEntity<Error> handleOtherExceptions(Exception e, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = prehandleOtherExceptions(e);
        ByteArrayOutputStream ba = (ByteArrayOutputStream)map.get("ba");
        List<String> detail = (List<String>) map.get("detail");
        detail.add(ba.toString());
        return handleOtherExceptions(e, detail);
    }
}