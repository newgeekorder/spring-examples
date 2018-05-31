package zipi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlLogService {
    @Autowired
    static CrawlerRepository crawlerRepository;
}
