package pl.mzlnk.emergencyspot.model.hospitalstay;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

public class HospitalStayDtoTest {

    @Test
    public void calendarSerializeTest() {
        HospitalStayDto dto = new HospitalStayDto();
        dto.setDateFrom(Calendar.getInstance());

        System.out.println(new Gson().toJson(dto, HospitalStayDto.class));
    }

}