package lt.arminai.reactive.dataaccess.server;

import lt.arminai.helper.SQLHelper;
import lt.arminai.reactive.dataaccess.server.dto.Address;
import lt.arminai.reactive.dataaccess.server.dto.Customer;
import lt.arminai.reactive.dataaccess.server.dto.OwnedProduct;
import lt.arminai.reactive.using.jdbc.ConnectionSubscription;
import rx.Observable;
import rx.functions.Action1;

import java.sql.SQLException;

/**
 * Created by vytautas on 2016-10-25.
 */
public class TestDatabaseProcedures {

    Action1<ConnectionSubscription> dispose = (connectionSubscription) -> {
        connectionSubscription.unsubscribe();
    };

    public Observable<Customer> toSelectCustomersObservable() throws SQLException {
        return Observable.using(TestDatabase::createSubscription, (subscription) -> {
            try {
                return SQLHelper.executeQuery(subscription, "SELECT ID, USERNAME FROM CUSTOMER", (rs) -> {
                    return new Customer(rs.getLong("ID"), rs.getString("USERNAME"));
                });
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }, dispose);
    }

    public Observable<Customer> toSelectCustomersObservable(long customerId) throws SQLException {
        return Observable.using(TestDatabase::createSubscription, (subscription) -> {
            try {
                System.out.println("Select Customer: " + Thread.currentThread().getName());
                return SQLHelper.executeQuery(subscription, "SELECT ID, USERNAME FROM CUSTOMER WHERE ID =" + customerId, (rs) -> {
                    return new Customer(rs.getLong("ID"), rs.getString("USERNAME"));
                });
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }, dispose);
    }

    public Observable<OwnedProduct> toSelectOwnedProductObservable(long customerId) throws SQLException {
        return Observable.using(TestDatabase::createSubscription, (subscription) -> {
            try {
                System.out.println("Select Products: " + Thread.currentThread().getName());
                return SQLHelper.executeQuery(subscription, "SELECT CUSTOMERID, PRODUCTID, NAME FROM CUSTOMER_PRODUCT CP "
                                + "JOIN PRODUCT P ON P.ID = CP.PRODUCTID WHERE CUSTOMERID = " + customerId,
                        (rs) -> {
                            return new OwnedProduct(rs.getLong("CUSTOMERID"), rs.getLong("PRODUCTID"), rs.getString("NAME"));
                        });
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }, dispose);

    }

    public Observable<Address> toSelectAddressObservable(long customerId) throws SQLException {
        return Observable.using(TestDatabase::createSubscription, (subscription) -> {
            try {
                System.out.println("Select Address: " + Thread.currentThread().getName());
                return SQLHelper.executeQuery(subscription, "SELECT ID, CUSTOMERID, ADDRESS1, ADDRESS2, CITY, STATE, ZIPCODE FROM ADDRESS WHERE CUSTOMERID = " + customerId,
                        (rs) -> {
                            return new Address(
                                    rs.getLong("ID"),
                                    rs.getLong("CUSTOMERID"),
                                    rs.getString("ADDRESS1"),
                                    rs.getString("ADDRESS2"),
                                    rs.getString("CITY"),
                                    rs.getString("STATE"),
                                    rs.getString("ZIPCODE"));
                        });
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }, dispose);
    }
}
