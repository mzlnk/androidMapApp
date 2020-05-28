package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.hospitalstay.HospitalStayDetailsDto;

@AllArgsConstructor
public class RetrieveHospitalStayDetailsHttpRequestParams extends BaseHttpRequestParams<HospitalStayDetailsDto> {

    private Long hospitalStayId;

    @Override
    public int getRequestMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/stays/" + this.hospitalStayId;
    }

    @Override
    public Class<HospitalStayDetailsDto> receivedDataClass() {
        return HospitalStayDetailsDto.class;
    }

}
