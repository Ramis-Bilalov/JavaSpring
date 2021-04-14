package ram.bilal.spring.chat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    public TextArea output;
    public TextField input;
    private String userName;
    private CharReader reader;
    NetworkService networkService;

    public String getUserName() {
        return userName;
    }

    public void send(ActionEvent actionEvent) throws IOException {
        NetworkService.getInstance()
                .write(Message.of(userName, input.getText()));

        input.clear();
    }

    public void quit(ActionEvent actionEvent) throws IOException {
        Parent chat = FXMLLoader.load(getClass().getResource("auth.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Вход в личный аккаунт");
        stage.setScene(new Scene(chat));
        stage.setResizable(false);
        stage.show();
        input.getScene().getWindow().hide();
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigClass.class);
        try {
        IoHistoryServiceImpl io = context.getBean("ioHistoryServiceImpl", IoHistoryServiceImpl.class);
            io.getHistory(50).forEach(historyLine -> {
                        output.appendText(historyLine + "\n");
                    });
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        userName = NetworkService.getInstance().getUserName();
        reader = new CharReader(output, NetworkService.getInstance().getInputStream());
        output.setWrapText(true);
        reader.start();
    }
}