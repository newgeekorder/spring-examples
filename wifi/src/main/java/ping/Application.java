public class Application {

    @Autowired
    TimeRepository timeRepository;

    public void ping(){
        String host = "http://www.bbc.co.uk";
        int timeOut = 3000;
        long BeforeTime = System.currentTimeMillis();
        reachable =  InetAddress.getByName(host).isReachable(timeOut);
        long AfterTime = System.currentTimeMillis();
        Long TimeDifference = AfterTime - BeforeTime;
    }


    public void loadTestData(){
        ArrayList data = new ArrayList();
        Random rn = new Random();
        data.add(new TimeData(new Date(), rn.nextInt(10) + 1))
        timeRepository.save(getData());
    }

public static void main(String [] args ){
    Application app = new Application();
    app.ping();
}

}