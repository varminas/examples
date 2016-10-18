package lt.arminai.reactive.using.jdbc;

import lt.arminai.helper.DataGenerator;
import lt.arminai.helper.FileUtils;
import rx.Observable;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vytautas on 2016-10-18.
 */
public class TestDatabase {
    public static void init() {
        File databaseDirectory = new File("./rxJavaTest");
        try {
            FileUtils.deleteDirectory(databaseDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        try {
            Class.forName(driver).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

        Connection c = null;

        try {
            DriverManager.getConnection("jdbc:derby:rxJavaTest;create=true");
            c = DriverManager.getConnection("jdbc:derby:rxJavaTest");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        Statement s = null;

        try {
            s = c.createStatement();
            String sql = "CREATE TABLE GREEK_ALPHABET (ID BIGINT, LETTER VARCHAR(255))";
            s.execute(sql);

            int id = 1;

            for (String nextLetter : DataGenerator.generateGreekAlphabet()) {
                sql = "INSERT INTO GREEK_ALPHABET (ID, LETTER) VALUES (" + (id++) + ", '" + nextLetter + "')";
                s.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException e) {

                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public static Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:derby:rxJavaTest");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static Observable<String> selectGreekAlphabet(ConnectionSubsription connectionSubsription) {
        try {
            Statement s = connectionSubsription.getConnection().createStatement();
            connectionSubsription.registerResourceForClose(s);

            ResultSet rs = s.executeQuery("SELECT LETTER FROM GREEK_ALPHABET");
            connectionSubsription.registerResourceForClose(rs);

            List<String> returnList = new ArrayList<>();
            while(rs.next()) {
                returnList.add(rs.getString("LETTER"));
            }

            return Observable.from(returnList);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
