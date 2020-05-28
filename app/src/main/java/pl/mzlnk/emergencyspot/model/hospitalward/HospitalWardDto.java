package pl.mzlnk.emergencyspot.model.hospitalward;

import lombok.Data;
import pl.mzlnk.emergencyspot.model.hospital.HospitalDto;

@Data
public class HospitalWardDto {

    private Long id;
    private HospitalDto hospital;
    private HospitalWardEnum wardType;

}
