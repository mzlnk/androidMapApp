package pl.mzlnk.emergencyspot.ui.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.model.user.UserDto;
import pl.mzlnk.emergencyspot.service.network.requests.HttpRequestParams;
import pl.mzlnk.emergencyspot.service.network.requests.RetrieveUserProfileHttpRequestParams;
import pl.mzlnk.emergencyspot.ui.view.button.NoIconButton;
import pl.mzlnk.emergencyspot.utils.StringUtils;

import static pl.mzlnk.emergencyspot.EmergencySpotApplication.app;


public class ProfileOverviewFragment extends BaseNetworkDataReceiverFragment<UserDto> {

    private TextView username, name, pesel;
    private NoIconButton signOut;

    private String peselValue;

    @Override
    protected int getInflatedLayoutResId() {
        return R.layout.f_profile_overview;
    }

    @Override
    protected void loadViewsFromXml(View rootView) {
        this.username = rootView.findViewById(R.id.f_profile_overview_text_username);
        this.name = rootView.findViewById(R.id.f_profile_overview_text_name);
        this.pesel = rootView.findViewById(R.id.f_profile_overview_text_pesel);
        this.signOut = rootView.findViewById(R.id.f_profile_overview_sign_out);
    }

    @Override
    protected void prepareListeners() {
        this.pesel.setOnClickListener(view -> this.pesel.setText(this.peselValue));

        this.signOut.setOnClickListener(view -> {
            app.userService.signOut(() -> {
                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.a_main_fragment_container, new MapOverviewFragment())
                        .commit();
            });
        });
    }


    @Override
    public HttpRequestParams<UserDto> httpRequestParams() {
        return new RetrieveUserProfileHttpRequestParams(app.userService.getAuthUser().getUsername());
    }

    @Override
    public void onDataReceivedFromNetwork(UserDto data) {
        this.username.setText(data.getUsername());
        this.name.setText(String.valueOf(data.getFirstName() + " " + data.getLastName()));
        this.peselValue = data.getPesel();
        this.pesel.setText(StringUtils.repeat("\u2022", data.getPesel().length()));
    }

}
