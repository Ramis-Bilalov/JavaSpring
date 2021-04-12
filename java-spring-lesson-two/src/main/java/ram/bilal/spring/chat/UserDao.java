package ram.bilal.spring.chat;

import java.sql.SQLException;

public interface UserDao {

    boolean userExists(String login, String password) throws SQLException;

    void updateUser(String login, String password, String nickname, String email) throws SQLException;

    String getNickname(String login, String password);

}
