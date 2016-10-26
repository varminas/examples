package lt.arminai.reactive.dataaccess.customerservice;

import lt.arminai.reactive.dataaccess.server.dto.Customer;
import rx.Observable;

/**
 * Created by vytautas on 2016-10-25.
 */
public interface CustomerService {
    Observable<Customer> fetchCustomer(long customerId);

    Observable<Customer> fetchCustomerWithAddressAndOwnedProduct(long customerId);
}
