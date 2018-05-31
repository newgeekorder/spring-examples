package zipi;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.*;
import org.springframework.stereotype.Repository;
import zipi.dao.Recipe;

import java.util.Collection;


public interface SolrRecipeRepository extends SolrCrudRepository<Recipe, String> {

    Page<Recipe> findByPopularity(Integer popularity, Pageable page);

    Page<Recipe> findByNameOrDescription(@Boost(2) String name, String description, Pageable page);

    @Highlight
    HighlightPage<Recipe> findByNameIn(Collection<String> name, Page page);

    @Query(value = "name:?0")
    @Facet(fields = { "cat" }, limit=20)
    FacetPage<Recipe> findByNameAndFacetOnCategory(String name, Pageable page);
}