package ping;

import model.TimeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.InetAddress;
import java.net.URL;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class Application  implements CommandLineRunner {

//    @Autowired
    TimeRespository timeRepository;

    @Scheduled(fixedRate = 5000)
    public void ping() {
        try {
            String host = "http://www.bbc.co.uk";
            int timeOut = 3000;
            long BeforeTime = System.currentTimeMillis();
            boolean reachable = InetAddress.getByName(new URL(host).getHost()).isReachable(timeOut);
            long AfterTime = System.currentTimeMillis();
            Long timeDifference = AfterTime - BeforeTime;
            System.out.println("ping " + timeDifference);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loadTestData() {
        Random rn = new Random();
        TimeData data = new TimeData(new Date(), rn.nextInt(10) + 1);
        timeRepository.save(data);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

    }
}