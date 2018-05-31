package com.waitrose.ecomm.services.saleforceetl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EtlRepository extends CrudRepository<DataMessage, Long> {



    @Query( nativeQuery = true, value =  "SELECT SEQUENCE_ID from SALESFORCE_ETL_STATUS ses1  where ses1.SEQUENCE_ID = (  select SEQUENCE_ID , max(shard_time)  FROM SALESFORCE_ETL_STATUS ses2  ) ")
    String findMaxSequenceId();


}


