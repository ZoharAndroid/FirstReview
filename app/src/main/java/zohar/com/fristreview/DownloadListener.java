package zohar.com.fristreview;

public interface DownloadListener {
    void onSuccess();
    void onFiald();
    void onCancel();
    void onPause();
    void getProcess(Integer process);
}
