## Spring Data Solr

* define a solr Repository and the dao object to be extracted 

`public interface SolrProductRepository extends SolrCrudRepository<Product, String> {`

## Links and Reference 
* [Spring Data Solr Reference ](https://docs.spring.io/spring-data/solr/docs/current/reference/html)


## Schema Support
Schema Support inspects your domain types whenever the applications context is refreshed and create missing fields in your index based on the properties configuration. This requires solr to run in Schemaless Mode.

Use @Indexed to provide additional details like specific solr types to use.

```java
@Configuration
@EnableSolrRepositories(schemaCreationSupport = true)
class Config {

  @Bean
  public SolrClient solrClient() {
    return new HttpSolrClient("http://localhost:8983/solr");
  }
}

@SolrDocument(collection="collection1")
class Product {
  
  @Id String id;
  @Indexed(solrType="text_general") String author;
  @Indexed("cat") List<String> category;

}
```