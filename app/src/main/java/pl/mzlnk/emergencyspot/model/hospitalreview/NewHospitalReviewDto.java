package pl.mzlnk.emergencyspot.model.hospitalreview;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewHospitalReviewDto {

    private Long hospitalStayId;
    private Double rating;

}
