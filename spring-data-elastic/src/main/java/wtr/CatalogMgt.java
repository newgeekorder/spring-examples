package wtr;

import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import wtr.elastic.CatalogSearch;

import java.util.List;

public class CatalogMgt {
    @Autowired
    private JestElasticsearchTemplate elasticsearchTemplate;
    private static final String INDEX_ALIAS_NAME = "test-catalog-alias";
    private static final String INDEX_1_NAME = "catalog-index-1";


    public void deleteIndexOnAlias() {
        elasticsearchTemplate.deleteIndex(INDEX_ALIAS_NAME);
    }

    public void deleteIndex(String index) {
        elasticsearchTemplate.deleteIndex(INDEX_1_NAME);
    }

    public boolean createIndex(String index) {
        boolean created = elasticsearchTemplate.createIndex(index);
        return created;
    }

    public boolean createCatalogIndex() {
        boolean created = elasticsearchTemplate.createIndex(CatalogSearch.class);
        return created;
//        assertThat(setting.get("index.number_of_shards"), Matchers.is("1"));
//        assertThat(setting.get("index.number_of_replicas"), Matchers.is("0"));
    }

    public void bulkLoad(List<IndexQuery> entities) {
        // when
        elasticsearchTemplate.bulkIndex(entities);
        elasticsearchTemplate.refresh(CatalogSearch.class);
    }

    public void createMapping() throws Exception {
        // given
        elasticsearchTemplate.createIndex(INDEX_1_NAME);

        XContentBuilder xContentBuilder = JsonXContent.contentBuilder()
                .startObject()
                .field("properties")
                .startObject()
                  .field("message")
                   .startObject()
                     .field("type", "text")
                .field("index", false)
                .field("store", true)
                .field("analyzer", "standard")
                .endObject()
                .endObject()
                .endObject();

        // when
//            assertThat(elasticsearchTemplate.putMapping(INDEX_1_NAME, "mapping", xContentBuilder), is(true));
    }

}

