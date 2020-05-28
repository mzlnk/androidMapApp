package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.hospital.HospitalDetailsDto;

@AllArgsConstructor
public class RetrieveHospitalDetailsHttpRequestParams extends BaseHttpRequestParams<HospitalDetailsDto> {

    private Long hospitalId;

    @Override
    public int getRequestMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/hospitals/" + hospitalId;
    }

    @Override
    public Class<HospitalDetailsDto> receivedDataClass() {
        return HospitalDetailsDto.class;
    }

}
