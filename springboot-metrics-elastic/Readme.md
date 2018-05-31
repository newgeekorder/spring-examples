Spring Boot provides actuator provides statistics on the monitoring and operation of the spring boot application
 
 
 The instrumentation data may be captured with 
 * http endpoints 
 * log files 
 
 In this project we show both approaches and how the data can be captured and shipped to elasticsearch to monitr the applications in kibana 
 
 Add the actuator to your build file 
 
 _Note: springboot 2 changes a lot of metrics health .. this needs ot be updated for springboot 2_
 
```java 
compile 'org.springframework.boot:spring-boot-starter-actuator'

```

To simplify access we add to the application.properties (or yml)
```$yml
server.port=8099
```

## Shipping with HTTP Beats 

Elastic provides a range of shipper tools to ship data to the Elastic instance. In this section we use Httpbeat. Httpbeat is a tool to call  HTTP endpoints and ship the result to the configured injest tools e.g Elastic ingest node, Logstash etc. 


The Sprinboot application exposes endpoints:
* /actuator/health  
* /actuator/metrics

Beats has a yml config file (httpbeat.yml) that allows us to specify the endpoints:

 ```yaml
############################## Httpbeat ########################################
      httpbeat:

        hosts:
          # Each - Host endpoints to call. Below are the host endpoint specific configurations
          -
            schedule: "@every 30s"
            url: http://localhost:8080/health
            method: get
            headers:
              Accept: application/json
            output_format: json
            json_dot_mode: replace

          -
            schedule: "@every 30s"
            url: http://localhost:8080/metrics
            method: get
            headers:
              Accept: application/json
            output_format: json
            json_dot_mode: replace

      #================================ General =====================================
      fields:
        app_id: test_app

      #----------------------------- Logstash output --------------------------------
      output.elasticsearch:
        hosts: ["localhost:9200"]
        index: "httpbeat-%{+yyyy.MM.dd}"
```

## Links and Reference 
Spring boot 2 
https://spring.io/blog/2018/03/16/micrometer-spring-boot-2-s-new-application-metrics-collector


* https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-metrics.html#production-ready-metrics-export-jmx
* https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-endpoints-exposing-endpoints
* http://www.baeldung.com/spring-boot-actuators