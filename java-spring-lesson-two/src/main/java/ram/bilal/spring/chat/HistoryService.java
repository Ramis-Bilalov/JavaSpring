package ram.bilal.spring.chat;

import java.io.IOException;
import java.util.List;

public interface HistoryService {

    List<String> getHistory(int limit) throws IOException;

    void saveHistory(List<String> history);

}