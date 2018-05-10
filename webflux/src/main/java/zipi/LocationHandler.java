package zipi;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class LocationHandler {

    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        return null;
    }
}
