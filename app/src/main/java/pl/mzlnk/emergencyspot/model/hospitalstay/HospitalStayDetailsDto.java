package pl.mzlnk.emergencyspot.model.hospitalstay;

import java.util.Calendar;

import lombok.Data;
import pl.mzlnk.emergencyspot.model.hospitalreview.HospitalReviewDetailsDto;
import pl.mzlnk.emergencyspot.model.hospitalward.HospitalWardDto;

@Data
public class HospitalStayDetailsDto {

    private Long id;
    private HospitalWardDto ward;

    private Calendar dateFrom;
    private Calendar dateTo;

    private HospitalReviewDetailsDto review;

    public boolean reviewExists() {
        return this.review != null;
    }

}
