package pl.mzlnk.emergencyspot.ui.dialog;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.model.hospital.HospitalParamsDto;
import pl.mzlnk.emergencyspot.model.hospitalward.HospitalWardEnum;
import pl.mzlnk.emergencyspot.ui.view.button.NoIconButton;
import pl.mzlnk.emergencyspot.ui.view.filter.FilterItem;

public class HospitalsFilterDialog extends BaseDialog {

    private EditText city, country;
    private LinearLayout filtersContainer;
    private NoIconButton submit;

    private List<HospitalWardEnum> selectedWards = new ArrayList<>();

    private Consumer<HospitalParamsDto> onSubmitListener;

    @Override
    protected int xmlLayoutResId() {
        return R.layout.d_hospitals_filter;
    }

    @Override
    protected void loadViewsFromXml(View rootView) {
        this.city = rootView.findViewById(R.id.d_hospitals_filter_input_city);
        this.country = rootView.findViewById(R.id.d_hospitals_filter_input_country);
        this.filtersContainer = rootView.findViewById(R.id.d_hospitals_filters_layout_filter_container);
        this.submit = rootView.findViewById(R.id.d_hospitals_filter_btn_submit);
    }

    @Override
    protected void prepareViews() {
        Stream.of(HospitalWardEnum.values())
                .map(ward -> {
                    FilterItem filterItem = new FilterItem(getContext());

                    filterItem.setIcon(ward.getIconResId());
                    filterItem.setLabel(ward.getName());

                    filterItem.setOnClickListener(view -> {
                        if(this.selectedWards.contains(ward)) {
                            this.selectedWards.remove(ward);
                            filterItem.setItemSelected(false);
                        } else {
                            this.selectedWards.add(ward);
                            filterItem.setItemSelected(true);
                        }
                    });

                    return filterItem;
                })
                .forEach(filtersContainer::addView);
    }

    @Override
    protected void prepareListeners() {
        this.submit.setOnClickListener(view -> {
            if(this.onSubmitListener != null) {
                String city = this.city.getText().toString();
                String country = this.country.getText().toString();

                HospitalParamsDto params = HospitalParamsDto
                        .builder()
                        .city(!city.isEmpty() ? city : null)
                        .country(!country.isEmpty() ? country : null)
                        .wards(!selectedWards.isEmpty() ? selectedWards : null)
                        .build();

                this.onSubmitListener.accept(params);
            }

            dismissAllowingStateLoss();
        });
    }

    public void setOnSubmitListener(Consumer<HospitalParamsDto> onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
    }

}
