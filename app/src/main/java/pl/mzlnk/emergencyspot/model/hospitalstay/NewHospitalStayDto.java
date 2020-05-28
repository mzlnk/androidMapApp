package pl.mzlnk.emergencyspot.model.hospitalstay;

import java.util.Calendar;

import lombok.Data;

@Data
public class NewHospitalStayDto {

    private Long hospitalWardId;
    private Long patientId;


    private Calendar dateFrom;
    private Calendar dateTo;

}
