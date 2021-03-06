package ram.bilal.spring.chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent auth = FXMLLoader.load(getClass().getResource("auth.fxml"));
        primaryStage.setTitle("Вход в аккаунт");
        primaryStage.setScene(new Scene(auth));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}