package test.javi.histrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.net.HttpURLConnection;
import java.net.URL;

public class CommandFallback extends HystrixCommand<String> {

    public CommandFallback(String key) {
        super(HystrixCommandGroupKey.Factory.asKey(key));
    }


    protected String run() throws Exception {
        URL url = new URL("http://huhuhahakk");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int status = connection.getResponseCode();
        connection.disconnect();
        return "Fallback Command" + status;
    }

    @Override
    protected String getFallback() {
        return "I am using the fallback";
    }
}
