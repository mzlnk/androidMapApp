package pl.mzlnk.emergencyspot.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.model.hospitalward.HospitalWardDetailsDto;
import pl.mzlnk.emergencyspot.service.network.requests.HttpRequestParams;
import pl.mzlnk.emergencyspot.service.network.requests.RetrieveHospitalWardDetailsHttpRequestParams;


public class HospitalWardDetailsFragment extends BaseNetworkDataReceiverFragment<HospitalWardDetailsDto> {

    private final static String HOSPITAL_WARD_ID_PARAM = "f.hospital.ward.details.ward.id";

    private TextView hospitalName, wardType, currentPatients;
    private RatingBar averageRating;

    private Long hospitalWardId;

    public static HospitalWardDetailsFragment newInstance(Long hospitalWardId) {
        HospitalWardDetailsFragment fragment = new HospitalWardDetailsFragment();

        Bundle args = new Bundle();
        args.putLong(HOSPITAL_WARD_ID_PARAM, hospitalWardId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getInflatedLayoutResId() {
        return R.layout.f_hospital_ward_details;
    }

    @Override
    protected void loadViewsFromXml(View rootView) {
        this.hospitalName = rootView.findViewById(R.id.f_hospital_ward_details_text_hospital_name);
        this.wardType = rootView.findViewById(R.id.f_hospital_ward_details_text_ward_type);
        this.currentPatients = rootView.findViewById(R.id.f_hospital_ward_details_text_current_patients);
        this.averageRating = rootView.findViewById(R.id.f_hospital_ward_details_bar_rating);
    }


    @Override
    protected void readArguments(Bundle arguments) {
        this.hospitalWardId = arguments.getLong(HOSPITAL_WARD_ID_PARAM);
    }

    @Override
    public HttpRequestParams<HospitalWardDetailsDto> httpRequestParams() {
        return new RetrieveHospitalWardDetailsHttpRequestParams(this.hospitalWardId);
    }

    @Override
    public void onDataReceivedFromNetwork(HospitalWardDetailsDto data) {
        this.hospitalName.setText(data.getHospital().getName());
        this.wardType.setText(data.getWardType().name());
        this.currentPatients.setText(String.format("%d / %d", data.getCurrentPatients(), data.getCapacity()));
        this.averageRating.setRating(data.getAverageRating().floatValue());
    }

}
