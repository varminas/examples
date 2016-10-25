package lt.arminai.reactive.dataaccess.server;

import lt.arminai.helper.SQLHelper;
import lt.arminai.reactive.dataaccess.customerservice.Customer;
import rx.Observable;

import java.sql.SQLException;

/**
 * Created by vytautas on 2016-10-25.
 */
public class TestDatabaseProducers {

    public Observable<Customer> toSelectCustomersObservable() throws SQLException {
        return Observable.using(TestDatabase::createSubscription, (subscription) -> {
            try {
                return SQLHelper.executeQuery(subscription, "SELECT ID, USERNAME FROm CUSTOMER", (rs) -> {
                    return new Customer(rs.getLong("ID"), rs.getString("USERNAME"));
                });
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });
    }

    public Observable<Customer> toSelectCustomersObservable(long customerId) throws SQLException {
        return null;
    }
}
