package com.example.research.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@NoArgsConstructor
@Setter
@EqualsAndHashCode
@ApiModel(description = "Исследование пациента")
public class BloodTestDto {
    private String id;
    private String patientId;
    private Date date;
    private short HGB; //Уровень гемоглобина
    private float RBC; //Эритроциты
    private short MCV; //Средний объем эритроцитов
    private short MCHC; //Средняя концентрация гемоглобина в эритроцитах

    @ApiModelProperty(position = 1, value = "Id исследования, генерируемый при создании исследования",
            example = "97343f6b-4e5d-11ea-ad24-186024e83486")
    public String getId() {
        return this.id;
    }

    @ApiModelProperty(position = 2, value = "Id пациента", example = "97343f6b-4e5d-11ea-ad24-186024e83486")
    public @NotNull @NotBlank String getPatientId() {
        return this.patientId;
    }

    @ApiModelProperty(position = 3, value = "Дата исследования", required = true, example = "2000-11-01")
    public @NotNull @NotBlank Date getDate() {
        return this.date;
    }

    @ApiModelProperty(position = 4, value = "Уровень гемоглобина", example = "123")
    public short getHGB() {
        return this.HGB;
    }

    @ApiModelProperty(position = 5, value = "Уровень эритроцитов", example = "7.21")
    public float getRBC() {
        return this.RBC;
    }

    @ApiModelProperty(position = 6, value = "Средний объем эритроцитов", example = "11")
    public short getMCV() {
        return this.MCV;
    }

    @ApiModelProperty(position = 7, value = "Средняя концентрация гемоглобина в эритроцитах", example = "9")
    public short getMCHC() {
        return this.MCHC;
    }
}