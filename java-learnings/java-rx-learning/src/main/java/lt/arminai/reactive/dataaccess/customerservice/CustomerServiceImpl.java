package lt.arminai.reactive.dataaccess.customerservice;

import lt.arminai.reactive.dataaccess.addressservice.AddressService;
import lt.arminai.reactive.dataaccess.productservice.ProductService;
import lt.arminai.reactive.dataaccess.server.TestDatabaseProcedures;
import lt.arminai.reactive.dataaccess.server.dto.Address;
import lt.arminai.reactive.dataaccess.server.dto.Customer;
import lt.arminai.reactive.dataaccess.server.dto.CustomerZipAccumulator;
import lt.arminai.reactive.dataaccess.server.dto.OwnedProduct;
import rx.Observable;

import java.sql.SQLException;

/**
 * Created by vytautas on 2016-10-25.
 */
public class CustomerServiceImpl implements CustomerService {

    private final TestDatabaseProcedures procedures = new TestDatabaseProcedures();
    private final AddressService addressService = new AddressService();
    private final ProductService productService = new ProductService();

    public CustomerServiceImpl() {
    }

    @Override
    public Observable<Customer> fetchCustomer(long customerId) {
        try {
            return procedures.toSelectCustomersObservable(customerId);
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    @Override
    public Observable<Customer> fetchCustomerWithAddressAndOwnedProduct(long customerId) {
        Observable<Customer> selectedCustomerObservable = fetchCustomer(customerId);
        Observable<Address> selectedAddressObservable = addressService.fetchCustomerAddress(customerId);
        Observable<OwnedProduct> selectedProductsObservable = productService.fetchOwnedProduct(customerId);

        Observable<CustomerRelatedData> dataStream = Observable.concat(
                selectedCustomerObservable,
                selectedAddressObservable,
                selectedProductsObservable);

//                .observeOn(Schedulers.computation());

        Observable<Observable<CustomerRelatedData>> wrappedDataStream = Observable.just(dataStream);

        CustomerZipAccumulator accum = new CustomerZipAccumulator();

        /**
         * Collapse the customer related data (Customer and Addresses) into a single customer with combined data
         */
        Observable<Customer> finalObservable = Observable
                .zip(wrappedDataStream, accum::collapseCustomerEvents)
                .last();

        return finalObservable;
    }
}
