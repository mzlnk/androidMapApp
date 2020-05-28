package pl.mzlnk.emergencyspot.ui.asynctask;

import pl.mzlnk.emergencyspot.model.hospitalstay.HospitalStayDto;
import pl.mzlnk.emergencyspot.ui.view.list.HospitalStayListItemView;
import pl.mzlnk.emergencyspot.ui.viewprovider.HospitalStaysOverviewViewProvider;
import pl.mzlnk.emergencyspot.ui.viewprovider.ViewProvider;

public class HospitalStaysOverviewLoadViewsTask extends BaseLoadViewsTask<HospitalStayDto, Long, HospitalStayListItemView> {

    public HospitalStaysOverviewLoadViewsTask(LoadViewsTaskReceiver<HospitalStayDto, Long, HospitalStayListItemView> receiver) {
        super(receiver);
    }

    @Override
    protected ViewProvider<HospitalStayDto, Long, HospitalStayListItemView> viewProvider() {
        return new HospitalStaysOverviewViewProvider();
    }

}
