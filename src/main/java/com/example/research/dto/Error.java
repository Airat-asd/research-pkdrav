/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.research.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Стандартный ответ об ошибке")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    @ApiModelProperty(value = "Обобщенный текст ошибки", example = "Invalid seek request")
    String message;

    @ApiModelProperty(value = "HTTP code", example = "400")
    Integer code;

    @ApiModelProperty(value = "Время возникновения ошибки в timestamp", example = "1548848018854")
    Long timestamp;

    @ApiModelProperty(value = "Детали ошибки", example = "[\"Значение уровня гемоглобины выше допустимого значения\"]")
    List<String> detail;

    public Error(String message, int code, long timestamp) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
    }
}