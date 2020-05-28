package pl.mzlnk.emergencyspot.ui.dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Consumer;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.model.hospitalstay.HospitalStayDetailsDto;
import pl.mzlnk.emergencyspot.model.hospitalstay.NewHospitalStayDto;
import pl.mzlnk.emergencyspot.model.hospitalward.HospitalWardDetailsDto;
import pl.mzlnk.emergencyspot.service.network.requests.CreateHospitalStayHttpRequestParams;
import pl.mzlnk.emergencyspot.ui.view.button.NoIconButton;

import static pl.mzlnk.emergencyspot.EmergencySpotApplication.app;

public class NewHospitalStayDialog extends BaseDialog {

    private static final String HOSPITAL_DETAILS_PARAM = "d.new.hospital.stay.hospital.details";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private TextView name, wardType, dateFrom, dateTo;
    private NoIconButton submit;

    private HospitalWardDetailsDto hospitalDetails;
    private NewHospitalStayDto newHospitalStay;

    private Consumer<HospitalStayDetailsDto> onSubmitListener;

    public NewHospitalStayDialog() {
        super();
    }

    public static NewHospitalStayDialog newInstance(HospitalWardDetailsDto hospitalDetails) {
        NewHospitalStayDialog dialog = new NewHospitalStayDialog();

        Bundle args = new Bundle();
        args.putSerializable(HOSPITAL_DETAILS_PARAM, hospitalDetails);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    protected int xmlLayoutResId() {
        return R.layout.d_new_hospital_stay;
    }

    @Override
    protected void loadViewsFromXml(View rootView) {
        this.name = rootView.findViewById(R.id.d_new_hospital_stay_text_name);
        this.wardType = rootView.findViewById(R.id.d_new_hospital_stay_text_ward_type);
        this.dateFrom = rootView.findViewById(R.id.d_new_hospital_stay_text_date_from);
        this.dateTo = rootView.findViewById(R.id.d_new_hospital_stay_text_date_to);
        this.submit = rootView.findViewById(R.id.d_new_hospital_stay_submit);
    }

    @Override
    protected void prepareData() {
        this.newHospitalStay = new NewHospitalStayDto();

        this.newHospitalStay.setHospitalWardId(this.hospitalDetails.getId());
        this.newHospitalStay.setPatientId(app.userService.getAuthUser().getPatientId());
    }

    @Override
    protected void prepareViews() {
        this.name.setText(this.hospitalDetails.getHospital().getName());
        this.wardType.setText(this.hospitalDetails.getWardType().getName());
    }



    @Override
    protected void prepareListeners() {
        this.dateFrom.setOnClickListener(view -> {
            DatePickerDialog dialog = new DatePickerDialog(getContext());
            dialog.setOnDateSetListener((v, year, month, day) -> {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);

                this.newHospitalStay.setDateFrom(cal);
                this.dateFrom.setText(formatter.format(cal.getTime()));
            });

            dialog.show();
        });

        this.dateTo.setOnClickListener(view -> {
            DatePickerDialog dialog = new DatePickerDialog(getContext());
            dialog.setOnDateSetListener((v, year, month, day) -> {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);

                this.newHospitalStay.setDateTo(cal);
                this.dateTo.setText(formatter.format(cal.getTime()));
            });

            dialog.show();
        });

        this.submit.setOnClickListener(view -> {
            if(this.newHospitalStay.getDateFrom() == null || this.newHospitalStay.getDateTo() == null) {
                Toast.makeText(getContext(), "Ustaw najpierw czas pobytu", Toast.LENGTH_SHORT).show();
                return;
            }

            app.networkService.makeRequestForObject(
                    new CreateHospitalStayHttpRequestParams(this.newHospitalStay),
                    hospitalStay -> {
                        Toast.makeText(getContext(), "Dodano nowy pobyt", Toast.LENGTH_SHORT).show();

                        if(this.onSubmitListener != null) {
                            this.onSubmitListener.accept(hospitalStay);
                        }
                        dismissAllowingStateLoss();
                    },
                    error -> Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()
            );
        });
    }

    @Override
    protected void readArguments(Bundle arguments) {
        this.hospitalDetails = (HospitalWardDetailsDto) arguments.getSerializable(HOSPITAL_DETAILS_PARAM);
    }

    public void setOnSubmitListener(Consumer<HospitalStayDetailsDto> onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
    }

}
