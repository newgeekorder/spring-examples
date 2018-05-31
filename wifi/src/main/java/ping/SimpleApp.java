package ping;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

public class SimpleApp {

    public void ping() {
        try {
            String host = "http://www.bbc.co.uk";
            int timeOut = 3000;
            long BeforeTime = System.currentTimeMillis();
             new URL(host).openConnection().connect();
//            boolean reachable = InetAddress.getByName((new URL(host)).getHost()).isReachable(timeOut);
            long AfterTime = System.currentTimeMillis();
            Long timeDifference = AfterTime - BeforeTime;
            System.out.println("ping " + timeDifference);
//            HttpResponse<String> response = Unirest.get(host).asString();
//            System.out.println("response code " + response.getStatus());
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    public static void main(String [] args )throws Exception{
        System.out.println("Starting .. ");
        SimpleApp sa = new SimpleApp();
        for ( int i = 0 ; i < 5; i++){
            sa.ping();
            Thread.sleep(1000);
        }

    }

}
