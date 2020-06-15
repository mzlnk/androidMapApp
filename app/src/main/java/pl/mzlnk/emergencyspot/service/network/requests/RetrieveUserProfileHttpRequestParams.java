package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.user.UserDto;

@AllArgsConstructor
public class RetrieveUserProfileHttpRequestParams extends BaseHttpRequestParams<UserDto> {

    private final String username;

    @Override
    public int getRequestMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/user/" + username;
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
