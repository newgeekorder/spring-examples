package model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

@Document(indexName = "pingData", type = "pingData", shards = 1)
public class TimeData {

    @Field
    String Id;

    @Field
    Date entryTime;
    @Field
    long latency;

    public TimeData(Date entryTime, long latency){
        this.entryTime = entryTime;
        this.latency = latency;
    }

}