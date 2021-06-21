package com.example.research.controller;

import com.example.research.dto.BloodTestDto;
import com.example.research.entity.BloodTest;
import com.example.research.entity.Patient;
import com.example.research.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
public class ControllerImpl implements Controller {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    @PostMapping("/patient/save")
    public String savePatient(Patient patient) {
        log.trace("starting method: public String savePatient(Patient patient)");
        String id = patientRepository.savePatient(patient);
        return id;
    }

    @Override
    @GetMapping("/patient/getAll")
    public List<Patient> getAllPatient() {
        log.trace("starting method: public List<Patient> getAllPatient()");
        return patientRepository.findAllPatient();
    }

    @Override
    @PutMapping("/patient/update")
    public String updatePatient(Patient patient) {
        //log.trace("starting method: public String updatePatient(Patient patient)");
        return null;
    }

    @Override
    @DeleteMapping("/patient/delete/{id}")
    public String deletePatient(String id) {
        log.trace("starting method: public String deletePatient(String id)");
        log.trace("id={}", id);
        Patient patient = patientRepository.findPatient(id);
        if (patient == null) {

        }
        log.trace("patient={}", patient);
        patientRepository.deletePatient(patient);
        return "ok";
    }

    @Override
    @PostMapping("/research/save")
    public String saveResearch(BloodTestDto bloodTestDto) {
        return null;
    }

    @Override
    @GetMapping("/research/getAllPatient/{id}")
    public List<BloodTest> getAllResearchPatient(String id) {
        return null;
    }

    @Override
    @PutMapping("/research/update")
    public String updateResearch(BloodTestDto bloodTestDto) {
        return null;
    }

    @Override
    @DeleteMapping("/research/remove/{id}")
    public String removeResearch(String id) {
        return null;
    }
}