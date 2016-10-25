package lt.arminai.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by vytautas on 2016-10-25.
 */
public final class SQLHelper {
    public static void executeInsert(Connection c, String sql) {
        Statement statement;
        try {
            statement = c.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
