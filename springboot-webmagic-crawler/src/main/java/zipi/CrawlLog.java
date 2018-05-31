package zipi;

import javax.persistence.*;

@Entity
@Table(name = "crawl_log")
public class CrawlLog {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;

    @Column(length = 500)
    String URL;
    boolean visited;



    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
