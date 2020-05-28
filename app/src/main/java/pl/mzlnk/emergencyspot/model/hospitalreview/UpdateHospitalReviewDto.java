package pl.mzlnk.emergencyspot.model.hospitalreview;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateHospitalReviewDto {

    private Long hospitalReviewId;
    private Double newRating;

}
