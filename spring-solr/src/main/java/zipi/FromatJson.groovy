package zipi

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import org.apache.solr.client.solrj.impl.CloudSolrClient
import org.apache.solr.common.SolrInputDocument
import org.json.JSONArray
import org.json.JSONObject

class FromatJson {
    String deleteBody = '''{ "delete-field" : { "name":"item.id" } }'''
    String createBody = '''{ "add-field":{"name":"item.id",
     "type":"string",
     "stored":true }
     }'''
    String updateBody = '''{
"replace-field":{
     "name":"id",
     "type":"string",
     "multiValued":false,
     "stored":true
     "sorted": false }
}'''

    String select ="http://localhost:8983/solr/Products_shard1_replica_n1/select?q=*%3A*"

    public void doSql(){
      CloudSolrClient  client = new CloudSolrClient.Builder().withZkHost("http://localhost:2181").build();


    }

    public void solrjUpdate(){
//        HttpSolrServer solr = new HttpSolrServer(solrIndexPath);
        SolrInputDocument doc = new SolrInputDocument();

    }

    public void updateFields() {
        HttpResponse<String> response = Unirest.post("http://localhost:8983/solr/Products/schema")
                .header("Content-Type", "application/json")
                .body(deleteBody).asString()

        println "Response: " + response.getStatus() + " " + response.getBody()

        response = Unirest.post("http://localhost:8983/solr/Products/schema")
                .header("Content-Type", "application/json")
                .body(createBody).asString()
        println "Response: " + response.getStatus() + " " + response.getBody()



    }

    public void upload(){
        JSONArray outputArray = new JSONArray();
        File fin = new File("/home/richard/Downloads/20180426071748-20180426070918-product.json")
        fin.eachLine { line ->
            HttpResponse<String> response = Unirest.post("http://localhost:8983/solr/Products/update/json/docs?commit=true")
                    .header("Content-Type", "application/json")
                    .body(new JSONObject(line)).asString();
            println "Response: " + response.getStatus() + " " + response.getBody()

        }
    }

    public void convetJson(){
        JSONArray outputArray = new JSONArray();
        File fout = new File("/home/richard/Downloads/output-products.json")
        File fin = new File("/home/richard/Downloads/20180426071748-20180426070918-product.json")
        fin.eachLine { line ->
            outputArray.put(new JSONObject(line));
        }
        fout.write(outputArray.toString())
    }

    public static void main(String [] args ) {
        FromatJson fj = new FromatJson()
//        fj.updateFields()
        fj.upload()
    }
}
