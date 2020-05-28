package pl.mzlnk.emergencyspot.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.service.network.requests.UserSignInHttpRequestParams;
import pl.mzlnk.emergencyspot.ui.dialog.UserSignInDialog;
import pl.mzlnk.emergencyspot.ui.fragment.AddHospitalPatientFragment;
import pl.mzlnk.emergencyspot.ui.fragment.HospitalStaysOverviewFragment;
import pl.mzlnk.emergencyspot.ui.fragment.HospitalsOverviewFragment;
import pl.mzlnk.emergencyspot.ui.fragment.MapOverviewFragment;
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
        this.menuBar.setHospitalStaysItemOnClickListener(view -> showFragment(new HospitalStaysOverviewFragment()));
        this.menuBar.setMapItemOnClickListener(view -> showFragment(new MapOverviewFragment()));
        this.menuBar.setAddPatientItemOnClickListener(view -> showFragment(new AddHospitalPatientFragment()));

        this.menuBar.setProfileItemOnClickListener(view -> {
            UserSignInDialog dialog = new UserSignInDialog();
            dialog.setOnSubmitListener((login, password) -> {
                app.networkService.makeRequestForObject(
                        new UserSignInHttpRequestParams(login, password),
                        dto -> {
                            Log.d("signin", "token: " + dto.getToken());
                        },
                        error -> {
                            Log.d("signin", "error: " + error.getMessage());
                        }
                );
            });

            dialog.show(getSupportFragmentManager(), "dialog");
        });
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.a_main_fragment_container, fragment)
                .commit();
    }

}
