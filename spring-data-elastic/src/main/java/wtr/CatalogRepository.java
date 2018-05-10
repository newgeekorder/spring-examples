package wtr;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import wtr.elastic.CatalogSearch;

public interface CatalogRepository extends ElasticsearchRepository<CatalogSearch, String> {
}
