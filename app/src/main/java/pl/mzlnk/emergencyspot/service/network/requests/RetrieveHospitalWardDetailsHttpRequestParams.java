package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.hospitalward.HospitalWardDetailsDto;

@AllArgsConstructor
public class RetrieveHospitalWardDetailsHttpRequestParams extends BaseHttpRequestParams<HospitalWardDetailsDto> {

    private Long hospitalWardId;

    @Override
    public int getRequestMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/wards/" + hospitalWardId;
    }

    @Override
    public Class<HospitalWardDetailsDto> receivedDataClass() {
        return HospitalWardDetailsDto.class;
    }

}
