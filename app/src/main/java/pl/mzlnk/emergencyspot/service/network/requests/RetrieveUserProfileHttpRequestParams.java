package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;

import pl.mzlnk.emergencyspot.model.user.UserDto;

public class RetrieveUserProfileHttpRequestParams extends BaseHttpRequestParams<UserDto> {

    @Override
    public int getRequestMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getUrl() {
        return "http://192.168.0.21:5000/users/me";
    }

    @Override
    public boolean isAuthorized() {
        return true;
    }

    @Override
    public Class<UserDto> receivedDataClass() {
        return UserDto.class;
    }
}
