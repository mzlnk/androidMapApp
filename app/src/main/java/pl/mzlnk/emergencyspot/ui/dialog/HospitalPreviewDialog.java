package pl.mzlnk.emergencyspot.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.model.hospital.HospitalDto;
import pl.mzlnk.emergencyspot.ui.fragment.HospitalDetailsFragment;
import pl.mzlnk.emergencyspot.ui.view.button.NoIconButton;

public class HospitalPreviewDialog extends BaseDialog {

    private static final String HOSPITAL_DETAILS_PARAM = "d.hospital.preview.hospital.details";

    private TextView name, address;
    private NoIconButton details;

    private HospitalDto hospitalDetails;

    public HospitalPreviewDialog() {
        super();
    }

    public static HospitalPreviewDialog newInstance(HospitalDto hospitalDetails) {
        HospitalPreviewDialog dialog = new HospitalPreviewDialog();

        Bundle args = new Bundle();
        args.putSerializable(HOSPITAL_DETAILS_PARAM, hospitalDetails);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    protected int xmlLayoutResId() {
        return R.layout.d_hospital_preview;
    }

    @Override
    protected void readArguments(Bundle arguments) {
        this.hospitalDetails = (HospitalDto) arguments.getSerializable(HOSPITAL_DETAILS_PARAM);
    }

    @Override
    protected void loadViewsFromXml(View rootView) {
        this.name = rootView.findViewById(R.id.d_hospital_preview_text_name);
        this.address = rootView.findViewById(R.id.d_hospital_preview_text_address);
        this.details = rootView.findViewById(R.id.d_hospital_preview_btn_submit);
    }

    @Override
    protected void prepareViews() {
        this.name.setText(this.hospitalDetails.getName());
        this.address.setText(String.format(
                "%s %d,\n%s, %s",
                this.hospitalDetails.getAddress().getStreet(),
                this.hospitalDetails.getAddress().getStreetNumber(),
                this.hospitalDetails.getAddress().getCity(),
                this.hospitalDetails.getAddress().getCountry()
                )
        );
    }

    @Override
    protected void prepareListeners() {
        this.details.setOnClickListener(view -> {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.a_main_fragment_container, HospitalDetailsFragment.newInstance(this.hospitalDetails.getId()))
                    .commit();

            dismissAllowingStateLoss();
        });
    }

}
