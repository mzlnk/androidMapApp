package pl.mzlnk.emergencyspot.ui.fragment;

import android.view.View;
import android.widget.Toast;

import pl.mzlnk.emergencyspot.service.network.requests.HttpRequestParams;
import pl.mzlnk.emergencyspot.ui.asynctask.LoadViewsTask;
import pl.mzlnk.emergencyspot.ui.asynctask.LoadViewsTaskReceiver;
import pl.mzlnk.emergencyspot.ui.asynctask.NetworkDataReceiver;

import static pl.mzlnk.emergencyspot.EmergencySpotApplication.app;

public abstract class BaseNetworkDataLoadViewsReceiverFragment<T, K, V> extends BaseFragment implements LoadViewsTaskReceiver<T, K, V>, NetworkDataReceiver<T> {

    private LoadViewsTask<T> task;

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        cancelTask();
    }

    @Override
    protected void prepareLayout(View rootView) {
        super.prepareLayout(rootView);

        LoadViewsTask<T> task = loadViewsTask();
        startTask(task);
    }

    @Override
    public void onDataReceivedFromNetwork(T data) {

    }

    protected final void startTask(LoadViewsTask<T> task) {
        cancelTask();

        HttpRequestParams params = httpRequestParams();
        if (params.isAuthorized()) {
            app.networkService.makeAuthorizedRequest(
                    params,
                    receivedDataClass(),
                    data -> {
                        this.task = task;
                        runTask(data);
                        this.onDataReceivedFromNetwork(data);
                    },
                    error -> Toast.makeText(getContext(), "Could not retrieve data from server", Toast.LENGTH_SHORT).show(),
                    app.userService.getAuthUser()
            );
        } else {
            app.networkService.makeRequest(
                    params,
                    receivedDataClass(),
                    data -> {
                        this.task = task;
                        runTask(data);
                        this.onDataReceivedFromNetwork(data);
                    },
                    error -> Toast.makeText(getContext(), "Could not retrieve data from server,", Toast.LENGTH_SHORT).show()
            );
        }
    }

    private void runTask(T data) {
        if (this.task != null) {
            this.task.executeTask(data);
        }
    }

    private void cancelTask() {
        if (this.task != null) {
            this.task.cancelTask(true);
        }
    }


}
