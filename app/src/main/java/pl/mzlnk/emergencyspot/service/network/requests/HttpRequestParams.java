package pl.mzlnk.emergencyspot.service.network.requests;

import com.google.gson.reflect.TypeToken;

import java.util.List;


public interface HttpRequestParams<T> {

    int getRequestMethod();

    String getUrl();

    String getRequestBody();

    boolean isAuthorized();

    Class<T> receivedDataClass();

    TypeToken<List<T>> receivedDataTypeToken();

}
