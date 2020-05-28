package pl.mzlnk.emergencyspot.service.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Map;

import pl.mzlnk.emergencyspot.service.network.requests.HttpRequestParams;
import pl.mzlnk.emergencyspot.utils.gson.CalendarTypeAdapter;

public class HttpObjectRequest<T> extends Request<T> {

    private final Class<T> clazz;
    private final Response.Listener<T> onSuccessListener;

    private final Map<String, String> headers;
    private final String requestBody;

    public HttpObjectRequest(HttpRequestParams<T> requestParams,
                             Response.Listener<T> onSuccessListener,
                             Response.ErrorListener onErrorListener) {
        this(requestParams, onSuccessListener, onErrorListener, null);
    }

    public HttpObjectRequest(HttpRequestParams<T> requestParams,
                             Response.Listener<T> onSuccessListener,
                             Response.ErrorListener onErrorListener,
                             Map<String, String> headers) {

        super(requestParams.getRequestMethod(), requestParams.getUrl(), onErrorListener);

        this.clazz = requestParams.receivedDataClass();
        this.onSuccessListener = onSuccessListener;
        this.headers = headers;
        this.requestBody = requestParams.getRequestBody();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

    @Override
    public byte[] getBody() {
        return this.requestBody.getBytes();
    }

    @Override
    protected void deliverResponse(T response) {
        this.onSuccessListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Calendar.class, new CalendarTypeAdapter())
                .create();

        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            Log.d("network", "json response:\n" + json);

            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

}