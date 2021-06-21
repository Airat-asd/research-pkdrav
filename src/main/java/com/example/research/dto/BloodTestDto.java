package com.example.research.dto;

import com.example.research.entity.Patient;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class BloodTestDto {
    private String id;
    @ApiModelProperty(value = "СНИЛС", required = true, example = "123-456-789 00")
    @NotNull @NotBlank
    private Patient patient;

    @ApiModelProperty(value = "Дата исследования", required = true, example = "01-01-2021")
    @NotNull @NotBlank
    private Date date;

    @ApiModelProperty(value = "Уровень гемоглобина", required = false, example = "123")
    private short HGB; //Уровень гемоглобина

    @ApiModelProperty(value = "Уровень эритроцитов", required = true, example = "7.21")
    private float RBC; //Эритроциты

    @ApiModelProperty(value = "Средний объем эритроцитов", required = true, example = "11")
    private short MCV; //Средний объем эритроцитов

    @ApiModelProperty(value = "Средняя концентрация гемоглобина в эритроцитах", required = true, example = "9")
    private short MCHC; //Средняя концентрация гемоглобина в эритроцитах
}
