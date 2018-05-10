package hello;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;


import java.io.File;


@RunWith(SpringRunner.class)
public class WebTest {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    private WebTestClient webTestClient;


    @Autowired
    ApplicationContext context;


    @Before
    public void setUp() {
        this.webTestClient = WebTestClient.bindToServer()
                .baseUrl("https://www.waitrose.com")
                .filter(documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void snippet(){
        this.webTestClient.get().uri("/").exchange()
                .expectStatus().isOk().expectBody()
                .consumeWith(document("sample"));
    }

    @Test
    public void authenticaiton(){
        this.webTestClient.get()
    }

    @Test
    public void search(){

    }

    @Test
    public void custSearch(){


    }



    public void defaultSnippetGeneration() {
        File outputDir = new File("target/snippets/bbc");

//        FileSystemUtils.deleteRecursively(outputDir);
        this.webTestClient.get()
                .uri("/")
                .exchange().expectStatus().isOk().expectBody()
                .consumeWith(document("default-snippets"));
    }

}
