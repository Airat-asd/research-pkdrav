package com.example.research.repository;

import com.example.research.entity.BloodTest;
import com.example.research.entity.Patient;
import com.example.research.service.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Slf4j
public class BloodTestRepository {
    @Value("${com.example.research.errorNotFindPatient}")
    private String errorNotFindPatient;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public void saveBloodTest(@NonNull BloodTest bloodTest) {
        log.trace("starting method: public void saveBloodTest(@NonNull BloodTest bloodTest)");
        String id = UUID.generateId();
        bloodTest.setId(id);
        entityManager.persist(bloodTest);
    }

    @Transactional
    public List<BloodTest> findResearchFromPatient(String id) {
        log.trace("starting method: public List<BloodTest> findResearchFromPatient(String id)");
        Patient patient = patientRepository.findPatient(id);
        return patient.getBloodTestList();
    }

    @Transactional
    public String deleteResearch(@NonNull String id) {
        log.trace("starting method: public String deleteResearch(@NonNull String id)");
        BloodTest bloodTest = entityManager.find(BloodTest.class, id);
        entityManager.remove(bloodTest);
        return "ok";
    }

    @Transactional
    public String updateResearch(@NonNull BloodTest bloodTest) {
        log.trace("starting method: public String updateResearch(@NonNull BloodTest bloodTest)");
        BloodTest bloodTestUpdate = entityManager.find(BloodTest.class, bloodTest.getId());
        fillingBloodTest(bloodTestUpdate, bloodTest);
        entityManager.flush();
        return "ok";
    }

    private void fillingBloodTest(BloodTest bloodTestUpdate, BloodTest bloodTest) {
        bloodTestUpdate.setDate(bloodTest.getDate());
        bloodTestUpdate.setHGB(bloodTest.getHGB());
        bloodTestUpdate.setRBC(bloodTest.getRBC());
        bloodTestUpdate.setMCV(bloodTest.getMCV());
        bloodTestUpdate.setMCHC(bloodTest.getMCHC());
    }
}
