package zipi;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.*;

import java.util.Collection;

public interface SolrProductRepository extends SolrCrudRepository<Product, String> {

    Page<Product> findByPopularity(Integer popularity, Pageable page);

    Page<Product> findByNameOrDescription(@Boost(2) String name, String description, Pageable page);

    @Highlight
    HighlightPage<Product> findByNameIn(Collection<String> name, Page page);

    @Query(value = "name:?0")
    @Facet(fields = { "cat" }, limit=20)
    FacetPage<Product> findByNameAndFacetOnCategory(String name, Pageable page);
}