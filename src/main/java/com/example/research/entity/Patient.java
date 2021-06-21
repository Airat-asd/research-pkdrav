package com.example.research.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ApiModel(description = "Пациент")
public class Patient {
    @Id
    @ApiModelProperty (value = "UUID, генерируемый при создании пациента", example = "97343f6b-4e5d-11ea-ad24-186024e83486")
    @JsonIgnore
    private String id;

    @Column(unique = true)
    @ApiModelProperty(value = "СНИЛС", example = "123-456-789 00")
    private String snils;

    @ApiModelProperty(value = "Фамилия", required = true, example = "бен Ишай")
    private String lastName;

    @ApiModelProperty(value = "Имя", required = true, example = "Давид")
    @NotNull @NotBlank
    private String firstName;

    @ApiModelProperty(value = "Отчество", example = "Йеуды")
    private String middleName;

    @ApiModelProperty(value = "Дата рождения",required = true, example = "2000-11-01")
    @NotNull @NotBlank
    private Date dateOfBirth;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<BloodTest> bloodTests = new ArrayList<>();

    public void addBloodTest(BloodTest bloodTest) {
        bloodTests.add(bloodTest);
        bloodTest.addPatient(this);
    }

    public void removeBloodTest(BloodTest bloodTest) {
        bloodTests.remove(bloodTest);
        bloodTest.addPatient(null);
    }
}