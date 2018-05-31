package zipi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.processor.PageProcessor;
import zipi.dao.Recipe;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
//@TargetUrl("https://www.waitrose.com/content/waitrose/en/home/recipes*")
@EnableSolrRepositories("zipi.*")
public class Application implements PageProcessor {

    private @Resource
    Environment env;


//    @Configuration
//    @EnableSolrRepositories(
//            basePackages = "zipi", schemaCreationSupport = true)
//            @ComponentScan
//    public class SolrConfig {
//
//        @Bean
//        public SolrClient solrClient() {
//            System.out.println("Connecting to " + env.getRequiredProperty("solr.host"));
//            return new HttpSolrClient(env.getRequiredProperty("solr.host"));
//        }
//
//        @Bean
//        public SolrTemplate solrTemplate(SolrClient client) throws Exception {
//            return new SolrTemplate(client);
//        }
//    }




    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public static final String URL_POST = "/home/recipes/\\w*.html";
    public static final String URL_Healthy = "/home/recipes/\\w*/\\w*.html";
    public static final String URL_Directory = "/content/waitrose/en/home/recipes/recipe_directory/\\w*/.*";

    @Resource
    private SolrRecipeRepository repository;

    @Transactional
    public void addToIndex(Recipe recipeDocument) {
        System.out.println("Trying to save the Recipe");
        Recipe savedProduct = repository.save(recipeDocument);
        System.out.println("got response " + savedProduct);
    }




    @ExtractBy(value = "//h1[@class='public']/strong/a/text()", notNull = true)
    private String name;

    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
    private String author;

    @ExtractBy("//div[@id='readme']/tidyText()")
    private String readme;

    public void initSpider(){
        System.out.println("Starting .. ");

        Spider.create(this)
                .addUrl("https://www.waitrose.com/content/waitrose/en/home/recipes.html")
//                .addPipeline(new ConsolePipeline())
                .run();
    }

    @Override
    public Site getSite() {
        return site;
    }

    @ExtractBy("//*/div[1]/h1")
    private String title;

    @ExtractBy("//*[@id=\"content\"]/div[2]/div[1]/ul/li")
    private List<String> tags;

    @ExtractBy("//*[@id=\"content\"]/div[2]/div[1]/div[4]/div/div")
    private List<String> ingredients;

    @Override
    public void process(Page page) {
        System.out.println("Page being processed .. " + page.getUrl());
        System.out.println("Title " + title);

        Document doc = Jsoup.parse(page.getHtml().toString());
        System.out.println("Alternate Title " + doc.selectFirst("head > title").text());
//        System.out.println("Body " + page.getHtml());
//        page.putField("author", page.getUrl().xpath("//").toString());

        System.out.println("Found " + doc.select("a[href]").size() +" links ");
        for (Element link : doc.select("a[href]") ){
            System.out.println(link.attr("abs:href"));
        }
        page.addTargetRequests(page.getHtml().links().regex(URL_POST).all(), 1000);
        page.addTargetRequests(page.getHtml().links().regex(URL_Healthy).all(), 1000);
        page.addTargetRequests(page.getHtml().links().regex(URL_Directory).all(), 1000);



    }
//
//    public static void main(String[] args) {
//            SpringApplication.run(Application.class, args);
//    }
}