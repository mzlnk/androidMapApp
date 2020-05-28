package pl.mzlnk.emergencyspot.model.hospital;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mzlnk.emergencyspot.model.hospitalward.HospitalWardEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalParamsDto {

    private String name;
    private Double longitude;
    private Double latitude;
    private String country;
    private String city;
    private List<HospitalWardEnum> wards;

}
