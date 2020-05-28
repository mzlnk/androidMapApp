package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.hospitalreview.HospitalReviewDetailsDto;
import pl.mzlnk.emergencyspot.model.hospitalstay.HospitalStayDetailsDto;

@AllArgsConstructor
public class CreateHospitalStayReviewHttpRequestParams extends BaseHttpRequestParams<HospitalReviewDetailsDto> {

    private Long hospitalStayId;
    private Double rating;

    @Override
    public int getRequestMethod() {
        return Request.Method.POST;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/reviews/";
    }

    @Override
    public String getRequestBody() {
        Map<String, Object> map = new HashMap<>();
        map.put("hospitalStayId", this.hospitalStayId);
        map.put("rating", this.rating);

        return new JSONObject(map).toString();
    }

    @Override
    public Class<HospitalReviewDetailsDto> receivedDataClass() {
        return HospitalReviewDetailsDto.class;
    }

}
