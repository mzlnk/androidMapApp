package pl.mzlnk.emergencyspot.service.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.mzlnk.emergencyspot.model.user.AuthUserDto;
import pl.mzlnk.emergencyspot.service.network.requests.HttpRequestParams;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NetworkService {

    private RequestQueue requestQueue;

    public static NetworkService init(Context context) {
        NetworkService networkService = new NetworkService();

        networkService.requestQueue = Volley.newRequestQueue(context);

        return networkService;
    }

    public <T> void makeRequestForObject(HttpRequestParams<T> requestParams,
                                         Response.Listener<T> onSuccessListener,
                                         Response.ErrorListener errorListener) {

        HttpObjectRequest<T> objectRequest = new HttpObjectRequest<T>(requestParams, onSuccessListener, errorListener);
        Log.d("network", "content-type: " + objectRequest.getBodyContentType());
        Log.d("network", "request-body: " + new String(objectRequest.getBody()));

        requestQueue.add(objectRequest);
    }

    public <T> void makeAuthorizedRequestForObject(HttpRequestParams<T> requestParams,
                                                   Response.Listener<T> onSuccessListener,
                                                   Response.ErrorListener errorListener,
                                                   AuthUserDto authUser) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer: " + authUser.getToken());

        HttpObjectRequest<T> objectRequest = new HttpObjectRequest<>(requestParams, onSuccessListener, errorListener, headers);
        Log.d("network", "content-type: " + objectRequest.getBodyContentType());

        requestQueue.add(objectRequest);
    }

    public <T> void makeRequestForList(HttpRequestParams<T> requestParams,
                                       Response.Listener<List<T>> onSuccessListener,
                                       Response.ErrorListener errorListener) {

        HttpListRequest<T> listRequest = new HttpListRequest<>(requestParams, onSuccessListener, errorListener);
        Log.d("network", "content-type: " + listRequest.getBodyContentType());

        requestQueue.add(listRequest);
    }

    public <T> void makeAuthorizedRequestForList(HttpRequestParams<T> requestParams,
                                                 Response.Listener<List<T>> onSuccessListener,
                                                 Response.ErrorListener errorListener,
                                                 AuthUserDto authUser) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer: " + authUser.getToken());

        HttpListRequest<T> listRequest = new HttpListRequest<>(requestParams, onSuccessListener, errorListener, headers);
        Log.d("network", "content-type: " + listRequest.getBodyContentType());

        requestQueue.add(listRequest);
    }

}
