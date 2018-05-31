package zipi;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zipi.CrawlLog;

import java.util.Optional;

@Repository
public interface CrawlerRepository extends CrudRepository<CrawlLog, Long> {



    Optional<CrawlLog> findById(Long id);

    @Query( nativeQuery = true, value =  "SELECT count(url) where url like ?1 ")
    int  selectHtmlUrl(String url );

}


