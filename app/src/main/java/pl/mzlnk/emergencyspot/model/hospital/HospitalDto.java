package pl.mzlnk.emergencyspot.model.hospital;

import lombok.Data;

@Data
public class HospitalDto {

    private String name;
    private String description;
    private AddressDto address;

}
