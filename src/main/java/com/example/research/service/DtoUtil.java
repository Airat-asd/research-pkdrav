package com.example.research.service;

import com.example.research.dto.BloodTestDto;
import com.example.research.entity.BloodTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DtoUtil {
    public static BloodTest bloodTestDto2BloodTest(@NonNull BloodTestDto bloodTestDto) {
        BloodTest bloodTest = new BloodTest();
        bloodTest.setId(bloodTestDto.getId());
        bloodTest.setDate(bloodTestDto.getDate());
        bloodTest.setHGB(bloodTestDto.getHGB());
        bloodTest.setRBC((short) (bloodTestDto.getRBC() * 100));
        bloodTest.setMCV(bloodTestDto.getMCV());
        bloodTest.setMCHC(bloodTestDto.getMCHC());
        return bloodTest;
    }

    public static BloodTestDto bloodTest2BloodTestDto(@NonNull BloodTest bloodTest) {
        BloodTestDto bloodTestDto = new BloodTestDto();
        bloodTestDto.setId(bloodTest.getId());
        bloodTestDto.setPatientId(bloodTest.getPatient().getId());
        bloodTestDto.setDate(bloodTest.getDate());
        bloodTestDto.setHGB(bloodTest.getHGB());
        float rbc = bloodTest.getRBC();
        bloodTestDto.setRBC(rbc/100);
        bloodTestDto.setMCV(bloodTest.getMCV());
        bloodTestDto.setMCHC(bloodTest.getMCHC());
        return bloodTestDto;
    }

    public static List<BloodTestDto> bloodTestList2BloodTestDtoList(@NonNull List<BloodTest> bloodTestList) {
        return bloodTestList.stream()
                .map(bloodTest -> bloodTest2BloodTestDto(bloodTest))
                .collect(Collectors.toList());
    }
}