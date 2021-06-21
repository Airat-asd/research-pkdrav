package com.example.research.service;

import com.example.research.dto.BloodTestDto;
import com.example.research.entity.BloodTest;

public class DtoUtil {
    public static BloodTest bloodTestDto2BloodTest(BloodTestDto bloodTestDto) {
        BloodTest bloodTest = new BloodTest();
        bloodTest.setId(bloodTestDto.getId());
        bloodTest.setPatient(bloodTestDto.getPatient());
        bloodTest.setDate(bloodTestDto.getDate());
        bloodTest.setHGB(bloodTestDto.getHGB());
        bloodTest.setRBC((short)(bloodTestDto.getRBC()*100));
        bloodTest.setMCV(bloodTestDto.getMCV());
        bloodTest.setMCHC(bloodTestDto.getMCHC());
        return bloodTest;
    }
}
