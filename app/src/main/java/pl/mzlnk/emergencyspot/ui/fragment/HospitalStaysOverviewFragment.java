package pl.mzlnk.emergencyspot.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.model.hospitalstay.HospitalStayDto;
import pl.mzlnk.emergencyspot.service.network.requests.HttpRequestParams;
import pl.mzlnk.emergencyspot.service.network.requests.RetrieveHospitalStaysHttpRequestParams;
import pl.mzlnk.emergencyspot.ui.asynctask.HospitalStaysOverviewLoadViewsTask;
import pl.mzlnk.emergencyspot.ui.asynctask.LoadViewsTask;
import pl.mzlnk.emergencyspot.ui.view.list.HospitalStayListItemView;


public class HospitalStaysOverviewFragment extends BaseNetworkDataLoadViewsReceiverFragment<HospitalStayDto, Long, HospitalStayListItemView> {

    private LinearLayout hospitalStaysContainer;

    @Override
    protected int getInflatedLayoutResId() {
        return R.layout.f_hospital_stays_overview;
    }

    @Override
    protected void loadViewsFromXml(View rootView) {
        this.hospitalStaysContainer = rootView.findViewById(R.id.f_hospital_stays_overview_layout_hospital_stays_container);
    }

    @Override
    public LoadViewsTask<HospitalStayDto> loadViewsTask() {
        return new HospitalStaysOverviewLoadViewsTask(this);
    }

    @Override
    public LinearLayout getContainer() {
        return hospitalStaysContainer;
    }

    @Override
    public HttpRequestParams<HospitalStayDto> httpRequestParams() {
        return new RetrieveHospitalStaysHttpRequestParams();
    }

}
