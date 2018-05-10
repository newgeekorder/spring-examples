package wtr.elastic;
import org.apache.commons.lang3.StringUtils;

public class SearchHelper {

    static String getCommaSperatedItems(String list ){
        return StringUtils.join(list, ',');
    }
}
