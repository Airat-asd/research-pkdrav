package com.example.research.repository;

import com.example.research.entity.BloodTest;
import com.example.research.entity.Patient;
import com.example.research.service.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Slf4j
public class PatientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Patient findPatient(String id) {
        log.trace("starting method: public Patient findPatient(String id)");
        Patient patient = entityManager.find(Patient.class, id);
        return patient;
    }

    public List<Patient> findAllPatient() {
        log.trace("starting method: public List<Patient> findAllPatient()");
        Query query = entityManager.createNativeQuery("select * from Patient;");
        return query.getResultList();
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
    public String deletePatientFromContext(@NonNull Patient patient) {
        log.trace("starting method: public String deletePatient(@NonNull Patient patient)");
        entityManager.remove(patient);
        return "ok";
    }

    @Transactional
    public String deletePatient(@NonNull Patient patient) {
        log.trace("starting method: public String deletePatient(@NonNull Patient patient)");
        entityManager.remove(patient);
        return "ok";
    }

    @Transactional
    public void saveBloodTest(@NonNull Patient patient, @NonNull BloodTest bloodTest) {
        log.trace("starting method: public void saveBloodTest(Patient patient, BloodTest bloodTest)");
        patient.addBloodTest(bloodTest);
        entityManager.flush();
    }


//    private String buildDeleteQuery(List<Department> departmentList) {
//        log.trace("starting method: private String buildDeleteQuery(List<Department> departmentList)");
//        String idList = departmentList.stream()
//                .map(department -> String.valueOf(department.getId()))
//                .reduce((id1, id2) -> (id1 + "," + id2)).get();
//        return String.format("delete from Department where id in (%s)", idList);
//    }
//
//    private String buildUpdateQuery(List<Department> departmentList) {
//        log.trace("starting method: private String buildUpdateQuery(List<Department> departmentList)");
//        StringBuilder updateQuery = new StringBuilder();
//        departmentList.forEach(department -> updateQuery
//                .append(String.format("update Department set description = '%s' where id = %s; ", department.getDescription(), department.getId())));
//        return updateQuery.toString();
//    }
//
//    private String buildInsertQuery(List<Department> departmentList) {
//        log.trace("starting method: private String buildInsertQuery(List<Department> departmentList)");
//        String values = departmentList.stream()
//                .map(department -> String.format("('%s','%s','%s')", department.getDepCode(), department.getDepJob(), department.getDescription()))
//                .reduce((value1, value2) -> (value1 + "," + value2)).get();
//        return String.format("insert into Department(dep_code, dep_job, description) values %s", values);
//    }
}