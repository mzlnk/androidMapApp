package pl.mzlnk.emergencyspot.model.hospital;

import java.io.Serializable;

import lombok.Data;

@Data
public class HospitalDto implements Serializable {

    private Long id;
    private Double longitude;
    private Double latitude;
    private String name;
    private String description;
    private AddressDto address;

}
