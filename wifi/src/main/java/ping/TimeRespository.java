package ping;

import model.TimeData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TimeRespository extends ElasticsearchRepository<TimeData, Long> {

}
