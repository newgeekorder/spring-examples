package hello;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleRestControllerDocsTests {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    private static final String REST_REQUEST_PATH = "/process";
    private static final String PREFIX = "restdocs/";

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    private RequestSpecification documentationSpec;

    @Before
    public void setUp() {
        this.documentationSpec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation))
                .build();
    }

    private void initRestAssured(final int localPort) {
        RestAssured.port = 80;
    }

    @Test
    @Parameters({
            "request-valid.json, process-valid, 200",
            "request-invalid.json, process-invalid, 200"
    })
    public void testProcess(final String requestResourceLocation, final String snippetFolderPrefix,
                            final int expectedStatus) {
        given(this.documentationSpec).relaxedHTTPSValidation()
                .accept(APPLICATION_JSON.toString())
                .contentType(APPLICATION_JSON.toString())
                .filter(document("target/snippets/" + "-request"))
                .filter(document("target/snippets/" + "-response"))
                .when()
                .get("https://www.waitrose.com/api/search-prod/v3/lookup?product_ids=074749-37922-37923")
                .then()
                .assertThat().statusCode(is(200));
    }

    private RequestFieldsSnippet documentRequestFields() {


        return requestFields(
                fieldWithPath("id").description("Id of the input that is bigger than 0"),
                fieldWithPath("name").description("Name of the input")
        );
    }

    private ResponseFieldsSnippet documentResponseFields() {
        return responseFields(
                fieldWithPath("valid").description("Indicated whether input message was valid"),
                fieldWithPath("message").description("Some message about the request processing")
        );
    }
}