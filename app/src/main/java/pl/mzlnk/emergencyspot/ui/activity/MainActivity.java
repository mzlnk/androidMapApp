package pl.mzlnk.emergencyspot.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.function.Consumer;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.model.user.AuthUserDto;
import pl.mzlnk.emergencyspot.ui.dialog.UserSignInDialog;
import pl.mzlnk.emergencyspot.ui.fragment.AddHospitalPatientFragment;
import pl.mzlnk.emergencyspot.ui.fragment.HospitalStaysOverviewFragment;
import pl.mzlnk.emergencyspot.ui.fragment.HospitalsOverviewFragment;
import pl.mzlnk.emergencyspot.ui.fragment.MapOverviewFragment;
import pl.mzlnk.emergencyspot.ui.fragment.ProfileOverviewFragment;
import pl.mzlnk.emergencyspot.ui.view.menu.MenuBar;

import static pl.mzlnk.emergencyspot.EmergencySpotApplication.app;

public class MainActivity extends AppCompatActivity {

    private MenuBar menuBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_main);

        this.menuBar = findViewById(R.id.a_main_menu_bar_menu);

        showFragment(new MapOverviewFragment());
        addMenuItemListeners();
    }

    private void addMenuItemListeners() {
        this.menuBar.setHospitalsItemOnClickListener(view -> showFragment(new HospitalsOverviewFragment()));
        this.menuBar.setMapItemOnClickListener(view -> showFragment(new MapOverviewFragment()));
        this.menuBar.setAddPatientItemOnClickListener(view -> showFragment(new AddHospitalPatientFragment()));

        this.menuBar.setHospitalStaysItemOnClickListener(view -> {
            if(app.userService.isUserSignedIn()) {
                showFragment(new HospitalStaysOverviewFragment());
                return;
            }

            showUserSignInDialog(user -> showFragment(new HospitalStaysOverviewFragment()));
        });

        this.menuBar.setProfileItemOnClickListener(view -> {
            if(app.userService.isUserSignedIn()) {
                showFragment(new ProfileOverviewFragment());
                return;
            }

            showUserSignInDialog(user -> showFragment(new ProfileOverviewFragment()));
        });
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.a_main_fragment_container, fragment)
                .commit();
    }

    private void showUserSignInDialog(Consumer<AuthUserDto> onSignInAction) {
        UserSignInDialog dialog = new UserSignInDialog();
        dialog.setOnSubmitListener((login, password) -> {
            app.userService.signIn(
                    login,
                    password,
                    user -> {
                        onSignInAction.accept(user);
                        dialog.dismissAllowingStateLoss();
                    },
                    error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show()
            );
        });

        dialog.show(getSupportFragmentManager(), "dialog");
    }

}
