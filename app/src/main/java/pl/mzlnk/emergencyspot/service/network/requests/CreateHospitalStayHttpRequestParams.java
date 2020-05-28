package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import pl.mzlnk.emergencyspot.model.hospitalstay.HospitalStayDetailsDto;
import pl.mzlnk.emergencyspot.model.hospitalstay.NewHospitalStayDto;

@AllArgsConstructor
public class CreateHospitalStayHttpRequestParams extends BaseHttpRequestParams<HospitalStayDetailsDto> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private NewHospitalStayDto newHospitalStay;

    @Override
    public int getRequestMethod() {
        return Request.Method.POST;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/stays";
    }

    @SneakyThrows
    @Override
    public String getRequestBody() {
        JSONObject obj = new JSONObject();

        obj.put("hospitalWardId", this.newHospitalStay.getHospitalWardId());
        obj.put("patientId", this.newHospitalStay.getPatientId());
        obj.put("dateFrom", formatter.format(this.newHospitalStay.getDateFrom().getTime()));
        obj.put("dateTo", formatter.format(this.newHospitalStay.getDateTo().getTime()));

        return obj.toString();
    }

    @Override
    public Class<HospitalStayDetailsDto> receivedDataClass() {
        return HospitalStayDetailsDto.class;
    }

}
