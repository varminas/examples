package lt.arminai.reactive.dataaccess.addressservice;

import lt.arminai.reactive.dataaccess.server.Address;
import lt.arminai.reactive.dataaccess.server.TestDatabaseProducers;
import rx.Observable;

import java.sql.SQLException;

/**
 * Created by vytautas on 2016-10-25.
 */
public class AddressService {

    private final TestDatabaseProducers producers = new TestDatabaseProducers();

    public Observable<Address> fetchCustomerAddress(long customerId) {
        try {
            return producers.toSelectAddressObservable(customerId);
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
