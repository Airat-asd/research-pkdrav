package com.example.research.service;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UUID {

    /**
     * Метод генерирует UUID на основе MAC-адреса и текущего времени
     *
     * @return String вида: 1750f483-1684-4a06-b72e-485a559a2359
     */
    public static String generateId() {
        log.trace("starting method:  public static String generateId()");

        EthernetAddress address = EthernetAddress.fromInterface();
        TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator(address);
        return  uuidGenerator.generate().toString();
    }
}