package lt.arminai.reactive.using.jdbc;

import rx.Subscription;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Created by vytautas on 2016-10-18.
 */
public class ConnectionSubsription implements Subscription {

    private final Connection connection;
    private final HashSet<Statement> statements = new HashSet<>();
    private final HashSet<ResultSet> resultSets = new HashSet<>();

    public ConnectionSubsription(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void unsubscribe() {
        System.out.println("Unsubscribe called!");

        for (ResultSet rs : resultSets) {
            System.out.println(" Closing ResultSet -> " + rs.toString());
            try {rs.close();} catch (SQLException t) {}
        }

        for (Statement s : statements) {
            System.out.println(" Closing Statement -> " + s.toString());
            try {s.close();} catch (SQLException t) {}
        }

        if (connection != null) {
            System.out.println(" Closing Connection -> " + connection.toString());
            try {connection.close();} catch (SQLException e) {}
        }
    }

    @Override
    public boolean isUnsubscribed() {
        return false;
    }

    public Connection getConnection() {
        return connection;
    }

    public void registerResourceForClose(Statement s) {
        statements.add(s);
    }

    public void registerResourceForClose(ResultSet rs) {
        resultSets.add(rs);
    }
}
