package zipi;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import us.codecraft.webmagic.MultiPageModel;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.MultiPagePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Collection;
import java.util.List;

@TargetUrl("https://www.waitrose.com/content/waitrose/en/home/recipes*")
public class WaitroseRecipe implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public static final String URL_POST = "/home/recipes/\\w*.html";
    public static final String URL_Healthy = "/home/recipes/\\w*/\\w*.html";
    public static final String URL_Directory = "/content/waitrose/en/home/recipes/recipe_directory/\\w*/.*";


//    @ExtractByUrl("http://news\\.163\\.com/\\d+/\\d+/\\d+/([^_]*).*\\.html")
//    private String pageKey;
//
//    @ExtractByUrl(value = "http://news\\.163\\.com/\\d+/\\d+/\\d+/\\w+_(\\d+)\\.html", notNull = false)
//    private String page;
//
//    @ExtractBy(value = "//div[@class=\"ep-pages\"]//a/regex('http://news\\.163\\.com/\\d+/\\d+/\\d+/\\w+_(\\d+)\\.html',1)"
//            , multi = true, notNull = false)
//    private List<String> otherPage;
//
//    @ExtractBy("//h1[@id=\"h1title\"]/text()")
//    private String title;
//
//    @ExtractBy("//div[@id=\"epContentLeft\"]")
//    private String content;

    @ExtractBy("//*/div[1]/h1")
    private String title;

    @ExtractBy("//*[@id=\"content\"]/div[2]/div[1]/ul/li")
    private List<String> tags;

    @ExtractBy("//*[@id=\"content\"]/div[2]/div[1]/div[4]/div/div")
    private List<String> ingredients;

    @Override
    public Site getSite() {
        return site;
    }

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

    public static void main(String[] args) {
//        OOSpider.create(Site.me().setSleepTime(1000)
//                , new ConsolePageModelPipeline(), Application.class)
//                .addUrl("https://www.waitrose.com/content/waitrose/en/home/recipes.html").thread(5).run();

//       System.out.println("/home/recipes/healthyeating.html".matches(URL_POST));

        OOSpider.create(new WaitroseRecipe())
                .addUrl("https://www.waitrose.com/content/waitrose/en/home/recipes.html")
//                .addPipeline(new ConsolePipeline())
                .run();


    }



}