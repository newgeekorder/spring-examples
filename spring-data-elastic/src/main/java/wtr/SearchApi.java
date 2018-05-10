package wtr;


import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class SearchApi {
    @Autowired
    private CustomerRepository repository;


    @RequestMapping("/")
    public String getCustomer() {
        List <Customer> cust = new ArrayList<>();
        JsonObject response = new JsonObject();
        response.addProperty("time", new Date().toString());
        JsonArray jsonList = new JsonArray();
        for  (Customer customer :  this.repository.findAll() ){
            jsonList.add(customer.toJson());
        }
        response.add("customers", jsonList);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(response);
        return json.toString();
    }
}
