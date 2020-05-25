package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.hospital.HospitalParamsDto;

@AllArgsConstructor
public class RetrieveHospitalsHttpRequestParams extends BaseHttpRequestParams {

    private HospitalParamsDto hospitalParams;


    @Override
    public int getRequestMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getUrl() {
        return null;
    }

}
