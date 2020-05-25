package pl.mzlnk.emergencyspot.service.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
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

    public <T> void makeRequest(HttpRequestParams requestParams,
                                Class<T> clazz,
                                Response.Listener<T> onSuccessListener,
                                Response.ErrorListener errorListener) {

        requestQueue.add(new HttpRequest<>(requestParams, clazz, onSuccessListener, errorListener));
    }

    public <T> void makeAuthorizedRequest(HttpRequestParams requestParams,
                                          Class<T> clazz,
                                          Response.Listener<T> onSuccessListener,
                                          Response.ErrorListener errorListener,
                                          AuthUserDto authUser) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer: " + authUser.getToken());

        requestQueue.add(new HttpRequest<>(requestParams, clazz, onSuccessListener, errorListener, headers));
    }

}
