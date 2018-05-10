package zipi;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {
    
    public <T extends ServerResponse> Mono<T> get(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> all(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getByCountry(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> post(ServerRequest serverRequest) {
        return null;
    }
}
