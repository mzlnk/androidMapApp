package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.hospitalreview.HospitalReviewDetailsDto;

@AllArgsConstructor
public class UpdateHospitalStayReviewHttpRequestParams extends BaseHttpRequestParams<HospitalReviewDetailsDto> {

    private Long hospitalReviewId;
    private Double newRating;

    @Override
    public int getRequestMethod() {
        return Request.Method.PUT;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/reviews/";
    }

    @Override
    public Class<HospitalReviewDetailsDto> receivedDataClass() {
        return HospitalReviewDetailsDto.class;
    }

    @Override
    public String getRequestBody() {
        Map<String, Object> map = new HashMap<>();
        map.put("hospitalReviewId", this.hospitalReviewId);
        map.put("newRating", this.newRating);

        return new JSONObject(map).toString();
    }

}
