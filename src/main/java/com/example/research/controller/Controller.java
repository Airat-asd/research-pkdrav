package com.example.research.controller;

import com.example.research.dto.BloodTestDto;
import com.example.research.entity.Patient;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "Тестируем REST сервис \"Исследования\"")
@ApiResponses({
        @ApiResponse(
                code = 400,
                message = "Invalid request",
                response = Error.class),
        @ApiResponse(
                code = 500,
                message = "Internal Server Error",
                response = Error.class)
})

public interface Controller {

    @ApiOperation(value = "Добавление пациента")
    public String savePatient(@ApiParam(value = "Добавляемый пациент", required = true) @Valid @RequestBody
                                      Patient patient);

    @ApiOperation(value = "Получение списка пациентов")
    public List<Patient> getAllPatient();

    @ApiOperation(value = "Редактирование пациента")
    public String updatePatient(@ApiParam(value = "Редактируемый пациент", required = true) @Valid @RequestBody
                                        Patient patient);

    @ApiOperation(value = "Удаление пациента")
    public String deletePatient(@ApiParam(value = "Id удаляемого пациента", required = true) @Valid @RequestBody
                                        String id);

    @ApiOperation(value = "Добавление исследования")
    public String saveResearch(@ApiParam(value = "Добавляемое исследование", required = true) @Valid @RequestBody
                                       BloodTestDto bloodTestDto);

    @ApiOperation(value = "Получение списка исследований по пациенту")
    public List<BloodTestDto> getAllResearchPatient(@ApiParam(value = "Id пациента", required = true) @Valid @RequestBody
                                                            String id);

    @ApiOperation(value = "Редактирование исследования")
    public String updateResearch(@ApiParam(value = "Редактируемое исследование", required = true) @Valid @RequestBody
                                         BloodTestDto bloodTestDto);

    @ApiOperation(value = "Удаление исследования")
    public String removeResearch(@ApiParam(value = "Id удаляемого исследования", required = true) @Valid @RequestBody
                                         String id);
}