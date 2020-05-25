package com.example.hostpitalsMap.main;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Spinner;

import com.example.hostpitalsMap.R;
import com.example.hostpitalsMap.models.Hospital;
import com.example.hostpitalsMap.models.HospitalKind;
import com.example.hostpitalsMap.models.checkBoxes;
import com.example.hostpitalsMap.services.DBservice;
import com.example.hostpitalsMap.utils.DropDownAdapter;
import com.example.hostpitalsMap.utils.PermissionUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;
    private DBservice dBservice = new DBservice();
    private Marker tmpMarkerToRate;

    private List<List<Marker>> all_markers = new ArrayList<>();
    private HashMap<String, Hospital> onMap = new HashMap<>();

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setPadding(0,100,0,0);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(50,20)));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 5.0f ) );
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();

        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<checkBoxes> listCheckBoxes = new ArrayList<>();

        for(HospitalKind kind : HospitalKind.values()) {
            checkBoxes checkBoxes = new checkBoxes();
            checkBoxes.setTitle(kind.getDisplayName());
            checkBoxes.setSelected(false);
            listCheckBoxes.add(checkBoxes);
            all_markers.add(new ArrayList<Marker>());
        }
        DropDownAdapter dropDownAdapter = new DropDownAdapter(this, MapsActivity.this, 0, listCheckBoxes);
        spinner.setAdapter(dropDownAdapter);
    }

    private void enableMyLocation() {
        // [START maps_check_location_permission]
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
        // [END maps_check_location_permission]
    }

    @Override
    public boolean onMyLocationButtonClick() {
//        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
//        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    // [START maps_check_location_permission_result]
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Permission was denied. Display an error message
            // [START_EXCLUDE]
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
            // [END_EXCLUDE]
        }
    }
    // [END maps_check_location_permission_result]

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    public void displayOrNot (HospitalKind kind, boolean display){

        if(display){
            ArrayList<Hospital> HospitalList = dBservice.getHospitalList(kind);
            for(Hospital hospital : HospitalList){
                LatLng position = new LatLng(hospital.getX(), hospital.getY());
                Marker marker = mMap.addMarker(new MarkerOptions().position(position).title(hospital.getName()).snippet("Kliknij po więcej..."));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(position));

                all_markers.get(kind.getValue()).add(marker);
                onMap.put(marker.getId(), hospital);
            }
        }
        else{
            for(Marker marker : all_markers.get(kind.getValue())){
                    marker.remove();
                    onMap.remove(marker.getId());
            }
            all_markers.get(kind.getValue()).clear();
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        tmpMarkerToRate = marker;
        Hospital hospital = onMap.get(marker.getId());

        Intent intent = new Intent(this, DetailsScreen.class);
        intent.putExtra("HOSPITAL", hospital);
        startActivityForResult(intent, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            int mark = data.getIntExtra("mark", 0);
            Objects.requireNonNull(onMap.get(tmpMarkerToRate.getId())).addMark(mark);
            dBservice.addMark(Objects.requireNonNull(onMap.get(tmpMarkerToRate.getId())).getId(), mark);
        }
    }

}
