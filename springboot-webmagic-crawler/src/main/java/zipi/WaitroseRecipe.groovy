package zipi

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import org.h2.tools.Server
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.model.OOSpider
import us.codecraft.webmagic.model.annotation.TargetUrl
import us.codecraft.webmagic.pipeline.ConsolePipeline
import us.codecraft.webmagic.processor.PageProcessor

import javax.annotation.PostConstruct
import java.sql.SQLException
import java.util.regex.Pattern

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories("zipi")
@ComponentScan("zipi")
@EntityScan("zipi")
@TargetUrl("https://www.waitrose.com/content/waitrose/en/home/recipes*")
public class WaitroseRecipe implements PageProcessor {
    Logger logger = LoggerFactory.getLogger(WaitroseRecipe.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public static final String URL_POST = "/home/recipes/\\w*.html";
    public static final String URL_Healthy = "/home/recipes/\\w*/\\w*.html";
    public static final String URL_Directory = "/content/waitrose/en/home/recipes/recipe_directory/\\w*/.*";


    @Autowired
    CrawlLogService cls

    @Autowired
    CrawlerRepository crawlerRepository

    CrawlerRepository getCrawlerRepo(){
        return crawlerRepository
    }


    @Autowired
    private Environment env;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        prrintln "insider h2 server "
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

//    @Bean
//    org.h2.tools.Server h2Server() {
//        println( "Starting h2 server ")
//        Server server = new Server();
//        Server.createWebServer().start();
//        try {
//            server.runTool("-tcp");
//            server.runTool("-tcpAllowOthers");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return server;
//
//    }

    static Map extract = [
            "title"       : '//*[@id="content"]/div[2]/div[1]/h1/text()',
            "cookTime"    : '//*[@id="content"]/div[2]/div[1]/div[2]/ul/li[2]/span[2]/text()',
            "prepTime"    : '//*[@id="content"]/div[2]/div[1]/div[2]/ul/li[1]/span[2]/text()',
            "totalTime"   : '//*[@id="content"]/div[2]/div[1]/div[2]/ul/li[3]/span[2]/strong/text()',
            "Energy"      : '//*[@id="content"]/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td/text()',
            "Fat"         : '//*[@id="content"]/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/text()',
            "SaturatedFat": '//*[@id="content"]/div[2]/div[2]/div[2]/div/table/tbody/tr[3]/td/text()',
            "Carbohydrate": '//*[@id="content"]/div[2]/div[2]/div[2]/div/table/tbody/tr[4]/td/text()',
            "Sugars"      : '//*[@id="content"]/div[2]/div[2]/div[2]/div/table/tbody/tr[5]/td/text()',
            "Protein"     : '//*[@id="content"]/div[2]/div[2]/div[2]/div/table/tbody/tr[6]/td/text()',
            "Salt"        : '//*[@id="content"]/div[2]/div[2]/div[2]/div/table/tbody/tr[7]/td/text()',
            "Fibre"       : '//*[@id="content"]/div[2]/div[2]/div[2]/div/table/tbody/tr[8]/td/text()',
            "image"       : '//*[@id="content"]/div[1]/a/img[2]/@src',
            "yield"       : '//*[@id="content"]/div[2]/div[1]/div[3]/p/span/text()'

    ]

    @PostConstruct
    public void startCrawler() throws Exception {
        try {
            Server server = new Server();
            try {
                server.runTool("-tcp");
                server.runTool("-tcpAllowOthers");

            } catch (Exception e) {
                e.printStackTrace();
            }

            println "Starting up .. "
            OOSpider.create(new WaitroseRecipe())
//                    .addUrl("https://www.waitrose.com/content/waitrose/en/home/recipes.html")
                .addUrl("https://www.waitrose.com/content/waitrose/en/home/recipes/recipe_directory/j/jersey-royal-halloumiandredpepperskewerswithpesto.html")
                    .addPipeline(new ConsolePipeline())
                    .run();
            println crawlerRepository.selectHtmlUrl("blah")

        }  catch (Exception e) {
            e.printStackTrace()
        }
    }


    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        try {
            JSONObject extracteData = new JSONObject();
            extracteData.put("id", UUID.randomUUID().toString())
            System.out.println("Page being processed .. " + page.getUrl());
            CrawlLog cl = new CrawlLog()
            cl.setURL(page.getUrl().toString())
            Document doc = Jsoup.parse(page.getHtml().toString());

            extract.each { key, value ->
                String extractedStr = page.getHtml().xpath(value).toString()
                if (extractedStr != null || extractedStr.equals("")) {
                    extracteData.put(key, extractedStr.trim())
                    println key + " : " + extractedStr
                }
            }

            List<String> ingredients = page.getHtml().xpath('//*[@id="content"]/div[2]/div[1]/div[4]/div/div/p//text()').all()
            List<String> method = page.getHtml().xpath('//*[@id="content"]/div[2]/div[1]/div[5]/div/div/p').all()
            //need to sub select this
            cleanMethod(method)

            JSONArray ingredientsJson = new JSONArray(ingredients)
            extracteData.put("ingredients", ingredientsJson)

            JSONArray methodJson = new JSONArray(method)
            extracteData.put("method", methodJson)

            println extracteData.toString()

            System.out.println("Found " + doc.select("a[href]").size() + " links ");

            page.addTargetRequests(page.getHtml().links().regex(URL_POST).all(), 1000);
            page.addTargetRequests(page.getHtml().links().regex(URL_Healthy).all(), 1000);
            page.addTargetRequests(page.getHtml().links().regex(URL_Directory).all(), 1000);

            // post to solr
            if (ingredients.size() > 0) {
                HttpResponse<String> response = Unirest.post("http://localhost:8983/solr/Recipe/update/json/docs?commit=true").header("Content-Type", "application/json").body(extracteData).asString()
                System.out.println("Saved " + response.getStatus() + "response: " + response.getBody())
                if ( response.getStatus() == 200 ){
                    cl.visited = true;
                } else {
                    cl.visited = false
                }

                getCrawlerRepo().save(cl)


            } else {
                println "no ingredeints .. skipping "
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }


    public void cleanMethod(List<String> method) {
        try {
            String cleanRegex = '(<p>(<span.*">)?)(.*)((<\\/span>)?<\\/p>)'
            Pattern p = Pattern.compile(cleanRegex)
            int i = 0;
            method.each { line ->
                def matching = p.matcher(line)
                println matching.groupCount()

                if (matching.matches() && matching.groupCount() == 5) {
                    println matching.group(3)
                    line = matching.group(3)
                    line = line.replaceAll("</span>", "")
                    method.putAt(i, line)
                }

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }




    public static void main(String[] args) {
//        OOSpider.create(Site.me().setSleepTime(1000)
//                , new ConsolePageModelPipeline(), Application.class)
//                .addUrl("https://www.waitrose.com/content/waitrose/en/home/recipes.html").thread(5).run();

//       System.out.println("/home/recipes/healthyeating.html".matches(URL_POST));

//        OOSpider.create(new WaitroseRecipe())
//                .addUrl("https://www.waitrose.com/content/waitrose/en/home/recipes.html")
////                .addUrl("https://www.waitrose.com/content/waitrose/en/home/recipes/recipe_directory/j/jersey-royal-halloumiandredpepperskewerswithpesto.html")
//                .addPipeline(new ConsolePipeline())
//                .run();

        ConfigurableApplicationContext context = SpringApplication.run(WaitroseRecipe.class, "--debug");

    }


}