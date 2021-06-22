package com.example.research.controller;

import com.example.research.dto.BloodTestDto;
import com.example.research.entity.BloodTest;
import com.example.research.entity.Patient;
import com.example.research.repository.BloodTestRepository;
import com.example.research.repository.PatientRepository;
import com.example.research.service.DtoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ControllerImpl implements Controller {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BloodTestRepository bloodTestRepository;

    @Override
    @PostMapping("/patient/save")
    public String savePatient(Patient patient) {
        log.trace("starting method: public String savePatient(Patient patient)");
        return patientRepository.savePatient(patient);
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
        log.trace("starting method: public String updatePatient(Patient patient)");
        log.trace("patient={}", patient);
        patientRepository.updatePatient(patient);
        return null;
    }

    @Override
    @DeleteMapping("/patient/delete/{id}")
    public String deletePatient(String id) {
        log.trace("starting method: public String deletePatient(String id)");
        log.trace("Delete patient with id={}", id);
        Patient patient = patientRepository.findPatient(id);
        patientRepository.deletePatient(patient);
        return "ok";
    }

    @Override
    @PostMapping("/research/save")
    public String saveResearch(BloodTestDto bloodTestDto) {
        log.trace("starting method: public String saveResearch(BloodTestDto bloodTestDto)");
        BloodTest bloodTest = DtoUtil.bloodTestDto2BloodTest(bloodTestDto);
        Patient patient = patientRepository.findPatient(bloodTestDto.getPatientId());
        bloodTest.addPatient(patient);
        bloodTestRepository.saveBloodTest(bloodTest);
        return "ok";
    }

    @Override
    @PostMapping("/research/getAllForPatient/{id}")
    public List<BloodTestDto> getAllResearchPatient(String id) {
        log.trace("starting method: public List<BloodTest> getAllResearchPatient(String id)");
        List<BloodTest> bloodTestList = bloodTestRepository.findResearchFromPatient(id);
        return DtoUtil.bloodTestList2BloodTestDtoList(bloodTestList);
    }

    @Override
    @PutMapping("/research/update")
    public String updateResearch(BloodTestDto bloodTestDto) {
        log.trace("starting method: public String updateResearch(BloodTestDto bloodTestDto)");
        BloodTest bloodTest = DtoUtil.bloodTestDto2BloodTest(bloodTestDto);
        bloodTestRepository.updateResearch(bloodTest);
        return "ok";
    }

    @Override
    @DeleteMapping("/research/remove/{id}")
    public String removeResearch(String id) {
        log.trace("starting method: public String removeResearch(String id)");
        return bloodTestRepository.deleteResearch(id);
    }
}