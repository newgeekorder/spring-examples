
@Document(indexName = "pingData", type = "pingData", shards = 1)
public class TimeData {
    Date entryTime;
    long latency;

    TimeData(Date entryTime, long latency){c
        this.entryTime = entryTime;
        this.latency = latency;
    }

}