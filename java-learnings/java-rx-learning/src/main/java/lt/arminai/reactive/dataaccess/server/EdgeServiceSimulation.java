package lt.arminai.reactive.dataaccess.server;

import lt.arminai.reactive.dataaccess.customerservice.Customer;
import lt.arminai.reactive.dataaccess.customerservice.CustomerService;
import lt.arminai.reactive.dataaccess.customerservice.CustomerServiceImpl;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by vytautas on 2016-10-25.
 */
public class EdgeServiceSimulation {
    public static void main(String[] args) {
        try {
            TestDatabase.init();

            CustomerService customerService = new CustomerServiceImpl();

            Object monitorObject = new Object();
            synchronized (monitorObject) {

                Observable<Customer> customerData = customerService.fetchCustomerWithAddressAndOwnedProduct(1);

                customerData
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(
                                (customer) -> {
                                    System.out.println("----------------------------------------");
                                    System.out.println(customer.getCustomerId() + " " + customer.getUserName());
                                    System.out.println();


                                    for (Address address : customer.getAddressList()) {
                                        System.out.println("    " + address.getAddress1());
                                        System.out.println("    " + address.getCity() + ", " + address.getState());
                                        System.out.println();
                                    }

                                    for (OwnedProduct product : customer.getOwnedProcutList()) {
                                        System.out.println("    Product: " + product.getName());
                                    }
                                    System.out.println();
                                    System.out.println("-----------------------------------------");
                                },
                                (t) -> {
                                    t.printStackTrace();
                                },
                                () -> {
                                    synchronized (monitorObject) {
                                        monitorObject.notify();
                                    }
                                }
                        );
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
