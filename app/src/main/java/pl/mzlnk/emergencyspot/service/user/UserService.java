package pl.mzlnk.emergencyspot.service.user;

import android.util.Log;

import com.android.volley.VolleyError;

import java.util.function.Consumer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.mzlnk.emergencyspot.model.user.AuthUserDto;
import pl.mzlnk.emergencyspot.service.network.requests.UserSignInHttpRequestParams;

import static pl.mzlnk.emergencyspot.EmergencySpotApplication.app;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private AuthUserDto authUser;

    public static UserService init() {
        return new UserService();
    }

    public AuthUserDto getAuthUser() {
        return authUser;
    }

    public boolean isUserSignedIn() {
        return authUser != null;
    }

    public void signIn(String username, String password, Consumer<AuthUserDto> onSuccessListener, Consumer<VolleyError> onErrorListener) {
        app.networkService.makeRequestForObject(
                new UserSignInHttpRequestParams(username, password),
                authUser -> {
                    this.authUser = authUser;
                    Log.d("user-service", "auth user: " + authUser);
                    onSuccessListener.accept(authUser);
                },
                onErrorListener::accept
        );
    }

    public void signOut(Runnable onSignOutListener) {
        this.authUser = null;
        onSignOutListener.run();
    }

}
