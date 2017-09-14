package test.javi.histrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.net.HttpURLConnection;
import java.net.URL;

public class CommandGood extends HystrixCommand<String> {


    public CommandGood(String key) {
        super(HystrixCommandGroupKey.Factory.asKey(key));
    }

    protected String run() throws Exception {
        URL url = new URL("http://www.vandal.net");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int status = connection.getResponseCode();
        connection.disconnect();
        return "" + status;
    }
}
