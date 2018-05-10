package wtr.elastic;

import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

@Document(indexName = "catalog", type = "catalog", shards = 1, replicas = 0, refreshInterval = "-1")
public class CatalogSearch {

    @Autowired
    private JestElasticsearchTemplate elasticsearchTemplate;

    @Id
    private String id;

    public CatalogSearch() {
    }

    public SearchQuery nativeQuery(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
        return searchQuery;
    }



    public SearchQuery lineLookup(){
        String retailStore = "";
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(termsQuery("", ""))
                .withFilter(termsQuery("availableRetailStoreIDs", retailStore))
                .build();
        return searchQuery;
    }



}
