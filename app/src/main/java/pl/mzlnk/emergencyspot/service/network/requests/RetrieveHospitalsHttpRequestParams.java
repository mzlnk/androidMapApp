package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.hospital.HospitalDto;
import pl.mzlnk.emergencyspot.model.hospital.HospitalParamsDto;
import pl.mzlnk.emergencyspot.model.hospitalward.HospitalWardEnum;

@AllArgsConstructor
public class RetrieveHospitalsHttpRequestParams extends BaseHttpRequestParams<HospitalDto> {

    private HospitalParamsDto hospitalParams;

    @Override
    public int getRequestMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getUrl() {
        StringBuilder sb = new StringBuilder();

        sb.append("/hospitals?");

        if (hospitalParams.getName() != null) {
            sb.append("name=")
                    .append(hospitalParams.getName())
                    .append("&");
        }

        if (hospitalParams.getLongitude() != null) {
            sb.append("longitude=")
                    .append(hospitalParams.getLongitude())
                    .append("&");
        }

        if (hospitalParams.getLatitude() != null) {
            sb.append("latitude=")
                    .append(hospitalParams.getLatitude())
                    .append("&");
        }

        if (hospitalParams.getCity() != null) {
            sb.append("city=")
                    .append(hospitalParams.getCity())
                    .append("&");
        }

        if (hospitalParams.getCountry() != null) {
            sb.append("country=")
                    .append(hospitalParams.getCountry())
                    .append("&");
        }

        if (hospitalParams.getWards() != null && !hospitalParams.getWards().isEmpty()) {
            sb.append("wards=")
                    .append(hospitalParams
                            .getWards()
                            .stream()
                            .map(HospitalWardEnum::name)
                            .collect(Collectors.joining(","))
                    );
        }

        return super.getUrl() + sb.toString();
    }

    @Override
    public TypeToken<List<HospitalDto>> receivedDataTypeToken() {
        return new TypeToken<List<HospitalDto>>() {
        };
    }

}
