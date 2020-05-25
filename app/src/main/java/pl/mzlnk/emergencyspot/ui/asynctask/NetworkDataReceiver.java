package pl.mzlnk.emergencyspot.ui.asynctask;

import pl.mzlnk.emergencyspot.service.network.requests.HttpRequestParams;

public interface NetworkDataReceiver<T> {

    HttpRequestParams httpRequestParams();
    Class<T> receivedDataClass();

}
