package ram.bilal.spring.chat;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IoHistoryServiceImpl implements HistoryService {

    private static final String path = "history.txt";
//    private static IoHistoryServiceImpl instance;

    public IoHistoryServiceImpl() {
    }

//    public static IoHistoryServiceImpl getInstance() {
//        if (instance == null) {
//            instance = new IoHistoryServiceImpl();
//        }
//        return instance;
//    }

    @Override
    public List<String> getHistory(int limit) throws IOException {
        InputStream is = new FileInputStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        int counter = 0;
        String r;
        List<String> chatList = new ArrayList<>();
        List<String> endList = new ArrayList<>();
        while((r = reader.readLine()) != null) {
            chatList.add(counter, r);
            counter++;
        }
        if(chatList.size() > limit) {
            for (int i = chatList.size() - limit, j = 0; i < chatList.size(); i++, j++) {
                endList.add(j, chatList.get(i));
            }
        } else if(chatList.size() < limit) {
            endList = chatList;
        }
        return endList;
    }

    @Override
    public void saveHistory(List<String> history) {
        try {
            Files.write(Paths.get(path), history, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getSavedHistory() {
        try {
            return Files.newBufferedReader(Paths.get(path))
                    .lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }
}