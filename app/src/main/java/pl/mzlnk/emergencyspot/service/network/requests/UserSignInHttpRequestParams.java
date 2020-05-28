package pl.mzlnk.emergencyspot.service.network.requests;

import com.android.volley.Request;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import pl.mzlnk.emergencyspot.model.user.AuthUserDto;

@AllArgsConstructor
public class UserSignInHttpRequestParams extends BaseHttpRequestParams<AuthUserDto> {

    private final String login;
    private final String password;

    @Override
    public int getRequestMethod() {
        return Request.Method.POST;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + "/token/generate";
    }

    @Override
    public String getRequestBody() {
        Map<String, String> map = new HashMap<>();
        map.put("username", this.login);
        map.put("password", this.password);

        return new JSONObject(map).toString();
    }

    @Override
    public Class<AuthUserDto> receivedDataClass() {
        return AuthUserDto.class;
    }
}
