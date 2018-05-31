package zipi;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;

import javax.annotation.Resource;

@Configuration
@EnableSolrRepositories
class ApplicationConfig {

    @Resource
    private Environment env;

    @Bean
    public SolrClient solrClient() {
        return  new HttpSolrClient.Builder(env.getRequiredProperty("solr.host")).build();
    }

    @Bean
    public SolrOperations solrTemplate() {
        return new SolrTemplate(solrClient());
    }
}