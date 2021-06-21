package com.example.research.entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class BloodTest {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;
    private Date date;
    private short HGB; //Уровень гемоглобина
    private int RBC; //Эритроциты RBC = (значение x 100) x 10^12
    private short MCV; //Средний объем эритроцитов
    private short MCHC; //Средняя концентрация гемоглобина в эритроцитах

    public void addPatient(Patient patient) {
        this.patient=patient;
    }
}
