package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.hospitalward.HospitalWardDto;

@AllArgsConstructor
public class RetrieveHospitalWardsHttpRequestParams extends BaseHttpRequestParams<HospitalWardDto> {

    private Long hospitalId;

    @Override
    public int getRequestMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/wards?hospital=" + hospitalId;
    }

    @Override
    public TypeToken<List<HospitalWardDto>> receivedDataTypeToken() {
        return new TypeToken<List<HospitalWardDto>>() {
        };
    }

}
