package lt.arminai.reactive.dataaccess.server;

import lt.arminai.helper.FileUtils;
import lt.arminai.helper.SQLHelper;
import lt.arminai.reactive.using.jdbc.ConnectionSubscription;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by vytautas on 2016-10-18.
 */
public class TestDatabase {
    private final static String DB_NAME = "myTest_m5";
    public static void init() {
        File databaseDirectory = new File("./" + DB_NAME);
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

        Connection c;

        try {
            DriverManager.getConnection("jdbc:derby:" + DB_NAME + ";create=true");
            c = DriverManager.getConnection("jdbc:derby:" + DB_NAME);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        Statement s = null;

        try {
            SQLHelper.executeInsert(c, "CREATE TABLE CUSTOMER (ID BIGINT, USERNAME VARCHAR(20))");
            SQLHelper.executeInsert(c, "CREATE TABLE PRODUCT (ID BIGINT, NAME VARCHAR(40))");
            SQLHelper.executeInsert(c, "CREATE TABLE CUSTOMER_PRODUCT (ID BIGINT, CUSTOMERID BIGINT, PRODUCTID BIGINT)");
            SQLHelper.executeInsert(c, "CREATE TABLE ADDRESS (ID BIGINT, CUSTOMERID BIGINT, ADDRESS1 VARCHAR(60), ADDRESS2 VARCHAR(60), CITY VARCHAR(60), STATE VARCHAR(60), ZIPCODE VARCHAR(60))");

            SQLHelper.executeInsert(c, "INSERT INTO CUSTOMER (ID, USERNAME) VALUES (1, 'relledge')");
            SQLHelper.executeInsert(c, "INSERT INTO CUSTOMER (ID, USERNAME) VALUES (2, 'dvader')");
            SQLHelper.executeInsert(c, "INSERT INTO CUSTOMER (ID, USERNAME) VALUES (3, 'lskyskimmer')");

            SQLHelper.executeInsert(c, "INSERT INTO PRODUCT (ID, NAME) VALUES (1, 'Rubber Baseball Bat')");
            SQLHelper.executeInsert(c, "INSERT INTO PRODUCT (ID, NAME) VALUES (2, '+1 Fish Sward Witherin')");
            SQLHelper.executeInsert(c, "INSERT INTO PRODUCT (ID, NAME) VALUES (3, 'Armband Floaties of Displair')");
            SQLHelper.executeInsert(c, "INSERT INTO PRODUCT (ID, NAME) VALUES (4, 'Flowpot of Juiciness')");

            SQLHelper.executeInsert(c, "INSERT INTO CUSTOMER_PRODUCT (ID, CUSTOMERID, PRODUCTID) VALUES (1, 1, 2)");
            SQLHelper.executeInsert(c, "INSERT INTO CUSTOMER_PRODUCT (ID, CUSTOMERID, PRODUCTID) VALUES (2, 1, 3)");

            SQLHelper.executeInsert(c, "INSERT INTO CUSTOMER_PRODUCT (ID, CUSTOMERID, PRODUCTID) VALUES (3, 2, 1)");
            SQLHelper.executeInsert(c, "INSERT INTO CUSTOMER_PRODUCT (ID, CUSTOMERID, PRODUCTID) VALUES (4, 2, 2)");
            SQLHelper.executeInsert(c, "INSERT INTO CUSTOMER_PRODUCT (ID, CUSTOMERID, PRODUCTID) VALUES (5, 2, 3)");

            SQLHelper.executeInsert(c, "INSERT INTO CUSTOMER_PRODUCT (ID, CUSTOMERID, PRODUCTID) VALUES (6, 3, 3)");
            SQLHelper.executeInsert(c, "INSERT INTO CUSTOMER_PRODUCT (ID, CUSTOMERID, PRODUCTID) VALUES (7, 3, 4)");

            SQLHelper.executeInsert(c, "INSERT INTO ADDRESS (ID, CUSTOMERID, ADDRESS1, ADDRESS2, CITY, STATE, ZIPCODE) VALUES "
                    + "(1, 1, '123 South North Street', NULL, 'Forth Wayne', 'TX', '76222')");
            SQLHelper.executeInsert(c, "INSERT INTO ADDRESS (ID, CUSTOMERID, ADDRESS1, ADDRESS2, CITY, STATE, ZIPCODE) VALUES "
                    + "(2, 1, '7788 Fourth Blvd', NULL, 'Carpentry', 'TX', '76122')");

            SQLHelper.executeInsert(c, "INSERT INTO ADDRESS (ID, CUSTOMERID, ADDRESS1, ADDRESS2, CITY, STATE, ZIPCODE) VALUES "
                    + "(3, 2, '1313 Planet of Doom Rd', 'App #33', 'Aldeberon', 'OH', '54332')");
            SQLHelper.executeInsert(c, "INSERT INTO ADDRESS (ID, CUSTOMERID, ADDRESS1, ADDRESS2, CITY, STATE, ZIPCODE) VALUES "
                    + "(4, 2, '4433 Sandy Hills Road', NULL, 'Tatto', 'VA', '94231')");

            SQLHelper.executeInsert(c, "INSERT INTO ADDRESS (ID, CUSTOMERID, ADDRESS1, ADDRESS2, CITY, STATE, ZIPCODE) VALUES "
                    + "(5, 3, '91122 Dirt Pile Way', NULL, 'Tatto', 'VA', '94231')");
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

    public static ConnectionSubscription createSubscription() {
        return new ConnectionSubscription(createConnection());
    }

    public static Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:derby:myTest_m5");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
