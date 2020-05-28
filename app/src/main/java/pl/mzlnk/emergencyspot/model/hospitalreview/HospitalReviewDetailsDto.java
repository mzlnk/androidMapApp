package pl.mzlnk.emergencyspot.model.hospitalreview;

import lombok.Data;
import pl.mzlnk.emergencyspot.model.hospitalward.HospitalWardDto;

@Data
public class HospitalReviewDetailsDto {

    private Long id;
    private HospitalWardDto ward;
    private Double rating;

}
