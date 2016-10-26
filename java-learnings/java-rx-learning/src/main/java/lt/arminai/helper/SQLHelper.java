package lt.arminai.helper;

import lt.arminai.reactive.dataaccess.server.RowMapper;
import lt.arminai.reactive.using.jdbc.ConnectionSubscription;
import rx.Observable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by vytautas on 2016-10-25.
 */
public final class SQLHelper {
    public static void executeInsert(Connection c, String sql) {
        Statement statement = null;
        try {
            statement = c.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public static <T> Observable<T> executeQuery(ConnectionSubscription subscription, String sqlString, RowMapper<T> rowMapper) throws SQLException {
        ArrayList<T> workList = new ArrayList<>();

        Statement s = subscription.getConnection().createStatement();
        subscription.registerResourceForClose(s);

        ResultSet rs = s.executeQuery(sqlString);
        subscription.registerResourceForClose(rs);

        while (rs.next()) {
            workList.add(rowMapper.call(rs));
        }

        return Observable.from(workList);
    }
}
