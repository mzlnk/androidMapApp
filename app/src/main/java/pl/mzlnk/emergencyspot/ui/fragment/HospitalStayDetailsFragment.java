package pl.mzlnk.emergencyspot.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.model.hospitalreview.HospitalReviewDetailsDto;
import pl.mzlnk.emergencyspot.model.hospitalstay.HospitalStayDetailsDto;
import pl.mzlnk.emergencyspot.service.network.requests.CreateHospitalStayReviewHttpRequestParams;
import pl.mzlnk.emergencyspot.service.network.requests.HttpRequestParams;
import pl.mzlnk.emergencyspot.service.network.requests.RetrieveHospitalStayDetailsHttpRequestParams;
import pl.mzlnk.emergencyspot.service.network.requests.UpdateHospitalStayReviewHttpRequestParams;
import pl.mzlnk.emergencyspot.ui.view.button.NoIconButton;

import static pl.mzlnk.emergencyspot.EmergencySpotApplication.app;


public class HospitalStayDetailsFragment extends BaseNetworkDataReceiverFragment<HospitalStayDetailsDto> {

    private static final String HOSPITAL_STAY_ID_PARAM = "f.hospital.stay.details.stay.id";

    private TextView hospitalName, wardType, date;
    private RatingBar rating;
    private NoIconButton submitRate;

    private Long hospitalStayId;
    private HospitalStayDetailsDto hospitalStayDetails;

    public static HospitalStayDetailsFragment newInstance(Long hospitalStayId) {
        HospitalStayDetailsFragment fragment = new HospitalStayDetailsFragment();

        Bundle args = new Bundle();
        args.putLong(HOSPITAL_STAY_ID_PARAM, hospitalStayId);
        fragment.setArguments(args);

        return fragment;
    }

    public HospitalStayDetailsFragment() {
        super();
    }

    @Override
    protected int getInflatedLayoutResId() {
        return R.layout.f_hospital_stay_details;
    }



    @Override
    protected void loadViewsFromXml(View rootView) {
        this.hospitalName = rootView.findViewById(R.id.f_hospital_stay_details_text_hospital_name);
        this.wardType = rootView.findViewById(R.id.f_hospital_stay_details_text_ward_type);
        this.date = rootView.findViewById(R.id.f_hospital_stay_details_text_date);
        this.rating = rootView.findViewById(R.id.f_hospital_stay_details_bar_rating);
        this.submitRate = rootView.findViewById(R.id.f_hospital_stay_details_btn_submit_rate);
    }

    @Override
    protected void prepareListeners() {
        this.submitRate.setOnClickListener(view -> {
            HttpRequestParams<HospitalReviewDetailsDto> requestParams;

            if(hospitalStayDetails.reviewExists()) {
                requestParams = new UpdateHospitalStayReviewHttpRequestParams(
                        this.hospitalStayDetails.getReview().getId(),
                        (double) this.rating.getRating()
                );
            } else {
                requestParams = new CreateHospitalStayReviewHttpRequestParams(
                        this.hospitalStayDetails.getId(),
                        this.hospitalStayDetails.getWard().getId(),
                        (double) this.rating.getRating()
                );
            }

            app.networkService.makeAuthorizedRequestForObject(
                    requestParams,
                    newReview -> {
                        this.hospitalStayDetails.setReview(newReview);
                        this.rating.setRating(newReview.getRating().floatValue());
                        Toast.makeText(getContext(), "Ocena została zapisana", Toast.LENGTH_SHORT).show();
                    },
                    error -> Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show(),
                    app.userService.getAuthUser()
            );
        });
    }

    @Override
    protected void readArguments(Bundle arguments) {
        this.hospitalStayId = arguments.getLong(HOSPITAL_STAY_ID_PARAM);
    }

    @Override
    public HttpRequestParams<HospitalStayDetailsDto> httpRequestParams() {
        return new RetrieveHospitalStayDetailsHttpRequestParams(this.hospitalStayId);
    }

    @Override
    public void onDataReceivedFromNetwork(HospitalStayDetailsDto data) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        this.hospitalStayDetails = data;

        this.hospitalName.setText(data.getWard().getHospital().getName());
        this.wardType.setText(data.getWard().getWardType().getName());

        Log.d("network", "date from: " + data.getDateFrom().getTime());
        Log.d("network", "date to: " + data.getDateTo().getTime());

        this.date.setText(String.format(
                "%s - %s",
                formatter.format(data.getDateFrom().getTime()),
                formatter.format(data.getDateTo().getTime())
        ));

        if(data.reviewExists()) {
            this.rating.setRating(data.getReview().getRating().floatValue());
        }
    }
}
