package com.example.research.repository;

import com.example.research.entity.Patient;
import com.example.research.service.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Slf4j
public class PatientRepository {
    @Value("${com.example.research.errorNotFindPatient}")
    private String errorNotFindPatient;

    @PersistenceContext
    private EntityManager entityManager;

    public Patient findPatient(String id) {
        log.trace("starting method: public Patient findPatient(String id)");
        Patient patient = entityManager.find(Patient.class, id);
        if (patient == null) {
            throw new IllegalArgumentException(errorNotFindPatient);
        }
        return patient;
    }

    public List<Patient> findAllPatient() {
        log.trace("starting method: public List<Patient> findAllPatient()");
        return entityManager.createQuery("from Patient").getResultList();
    }

    @Transactional
    public String savePatient(@NonNull Patient patient) {
        log.trace("starting method: public void savePatient(Patient patient)");
        String id = UUID.generateId();
        patient.setId(id);
        entityManager.persist(patient);
        return id;
    }

    @Transactional
    public String deletePatient(@NonNull Patient patient) {
        log.trace("starting method: public String deletePatient(@NonNull String id)");
        entityManager.remove(patient);
        return "ok";
    }

    @Transactional
    public String updatePatient(@NonNull Patient patient) {
        log.trace("starting method: public String updatePatient(@NonNull Patient patient)");
        Patient merge = entityManager.merge(patient);
        log.trace("patient={}", patient);
        return "ok";
    }
}