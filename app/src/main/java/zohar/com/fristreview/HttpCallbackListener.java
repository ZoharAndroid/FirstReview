package zohar.com.fristreview;

public interface HttpCallbackListener {
    void onFinished(String response);
    void onError(Exception e);
}
