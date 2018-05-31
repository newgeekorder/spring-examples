package com.waitrose.ecomm.services.saleforceetl;

import org.json.JSONObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "salesforce_etl_status")
public class DataMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;


    @Transient
    public JSONObject searchData;
    @Transient
    public JSONObject dynamoData;
    @Transient
    public String handlebarsData;
    @Transient
    public String salesforceData;

    public boolean searchSucces;
    public boolean dynamoSuccess;
    public boolean handlebarsSucess;
    public boolean saleforceSuccess;

    @Column(length = 500)
    public String sequenceId;

    @Column(length = 500)
    String errorReason;

    String lineId;
    Date shardTime;
    String shardId;

    Date startedTime = new Date();

    public JSONObject mergedData(){
        JSONObject mergeData = searchData;
        for ( String key : dynamoData.keySet()){
            mergeData.put(key, dynamoData.get(key));
        }
        return mergeData;
    }
}
