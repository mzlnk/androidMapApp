package pl.mzlnk.emergencyspot.model.user;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthUserDto {

    private Long patientId;
    private String username;
    private String token;

}
