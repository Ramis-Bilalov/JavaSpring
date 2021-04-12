package ram.bilal.spring.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExampleDAO {

    private Connection connection;
    private PreparedStatement getNicknameSttm;

    public ExampleDAO() {
        // init fields
        try {
            getNicknameSttm = connection.prepareStatement("select name from users where login = '?' and password = '?'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isUserExists(String login) {
        // some logic
        return true;
    }

    public boolean updateUser(String login, String password, String nick) {
        // some logic
        return true;
    }

    public String getNickname(String login, String password) {
        // some logic
        try {
            getNicknameSttm.setString(1, login);
            getNicknameSttm.setString(2, password);
            return getNicknameSttm.executeQuery().getString("NAME");
        } catch (SQLException throwables) {
            throw new RuntimeException("SQL Error ...");
        }
    }

    private boolean updateNick(String newNick, String oldNick) {
        // some logic
        return true;
    }

    private boolean updateLogin(String newLogin, String oldLogin) {
        // some logic
        return true;
    }

    private boolean updatePassword(String newPass, String oldPass) {
        // some logic
        return true;
    }
}
