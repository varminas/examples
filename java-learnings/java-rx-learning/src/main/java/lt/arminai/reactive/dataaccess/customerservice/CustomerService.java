package lt.arminai.reactive.dataaccess.customerservice;

import rx.Observable;

/**
 * Created by vytautas on 2016-10-25.
 */
public interface CustomerService {
    Observable<Customer> fetchCustomer(long customerId);

    Observable<Customer> fetchCustomerWithAddressAndOwnedProduct(int i);
}
