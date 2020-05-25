package pl.mzlnk.emergencyspot.service.network.requests;

import org.json.JSONObject;

public interface HttpRequestParams {

    int getRequestMethod();

    String getUrl();

    JSONObject getRequestBody();

}
