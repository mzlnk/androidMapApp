package pl.mzlnk.emergencyspot.service.network.requests;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import pl.mzlnk.emergencyspot.model.hospitalstay.HospitalStayDto;

public class RetrieveHospitalStaysHttpRequestParams extends BaseHttpRequestParams<HospitalStayDto> {

    @Override
    public int getRequestMethod() {
        return 0;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/stays/me";
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
