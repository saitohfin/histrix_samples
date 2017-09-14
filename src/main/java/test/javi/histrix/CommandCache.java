package test.javi.histrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.net.HttpURLConnection;
import java.net.URL;

public class CommandCache extends HystrixCommand<String> {

    private String key;

    public CommandCache(String key) {
        super(HystrixCommandGroupKey.Factory.asKey(key));
        this.key = key;
    }

    protected String run() throws Exception {
        URL url = new URL("http://www.google.es");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int status = connection.getResponseCode();
        connection.disconnect();
        return "Cache Command" + status;
    }

    @Override
    protected String getCacheKey() {
        return key;
    }
}
