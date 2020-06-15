package pl.mzlnk.emergencyspot.service.network.requests;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.hospitalstay.HospitalStayDto;
import pl.mzlnk.emergencyspot.model.user.AuthUserDto;

@AllArgsConstructor
public class RetrieveHospitalStaysHttpRequestParams extends BaseHttpRequestParams<HospitalStayDto> {

    private final AuthUserDto user;

    @Override
    public int getRequestMethod() {
        return 0;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/stays?patient=" + user.getPatientId();
    }

    @Override
    public boolean isAuthorized() {
        return true;
    }

    @Override
    public TypeToken<List<HospitalStayDto>> receivedDataTypeToken() {
        return new TypeToken<List<HospitalStayDto>>() {
        };
    }

}
