package pl.mzlnk.emergencyspot.ui.asynctask;

public interface LoadViewsTask<T> {

    void cancelTask(boolean mayInterruptIfRunning);
    boolean isTaskCancelled();
    void executeTask(T data);

}
