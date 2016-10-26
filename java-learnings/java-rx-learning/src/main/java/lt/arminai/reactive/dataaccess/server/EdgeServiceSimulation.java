package lt.arminai.reactive.dataaccess.server;

import lt.arminai.reactive.dataaccess.customerservice.CustomerService;
import lt.arminai.reactive.dataaccess.customerservice.CustomerServiceImpl;
import lt.arminai.reactive.dataaccess.server.dto.Address;
import lt.arminai.reactive.dataaccess.server.dto.Customer;
import lt.arminai.reactive.dataaccess.server.dto.OwnedProduct;
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

            /**
             * Create a monitor so that we don't exit the application too soon
             */
            Object waitMonitor = new Object();
            synchronized (waitMonitor) {

                Observable<Customer> customerData = customerService.fetchCustomerWithAddressAndOwnedProduct(1);

                customerData
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(
                                (customer) -> {
                                    System.out.println("----------------------------------------");
                                    System.out.println(customer.getCustomerId() + " " + customer.getUsername());
                                    System.out.println();


                                    for (Address address : customer.getAddressList()) {
                                        System.out.println("    " + address.getAddress1());
                                        System.out.println("    " + address.getCity() + ", " + address.getState() + " " + address.getZipCode());
                                        System.out.println();
                                    }

                                    for (OwnedProduct product : customer.getOwnedProductList()) {
                                        System.out.println("    Product: " + product.getName());
                                    }
                                    System.out.println();
                                    System.out.println("-----------------------------------------");
                                },
                                (t) -> {
                                    t.printStackTrace();
                                },
                                () -> {
                                    synchronized (waitMonitor) {
                                        waitMonitor.notify();
                                    }
                                }
                        );
                waitMonitor.wait();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
