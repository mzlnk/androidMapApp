package pl.mzlnk.emergencyspot.service.network.requests;

import org.json.JSONObject;

public abstract class BaseHttpRequestParams implements HttpRequestParams {


    @Override
    public JSONObject getRequestBody() {
        return null;
    }

}
