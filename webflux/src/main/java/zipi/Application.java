import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import zipi.LocationHandler;
import zipi.ProductHandler;

import javax.xml.stream.Location;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.util.MimeType.*;





public class Application {

    @Autowired
    ProductHandler productHandler;

    @Autowired
    LocationHandler locationHandler;



    @Configuration
    public class MyRouter {
        // not ideal!
        @Bean
        public RouterFunction<ServerResponse> routes(ProductHandler productHandler, LocationHandler locationHandler) {
            return RouterFunctions.route(GET("/product/{id}").and(accept(APPLICATION_JSON)), productHandler::get)
                    .andRoute(GET("/product").and(RequestPredicates.accept(APPLICATION_JSON)), productHandler::all)
                    .andRoute(POST("/product").and(accept(APPLICATION_JSON)).and(APPLICATION_JSON), productHandler::post)
                    .andRoute(PUT("/product/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), productHandler::put)
                    .andRoute(DELETE("/product/{id}"), productHandler::delete)
                    .andRoute(GET("/product/country/{country}").and(accept(APPLICATION_JSON)), productHandler::getByCountry)
                    .andRoute(GET("/locations/{id}").and(accept(APPLICATION_JSON)), locationHandler::get);
        }

    }
}