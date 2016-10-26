package lt.arminai.reactive.dataaccess.server.dto;

import lt.arminai.reactive.dataaccess.customerservice.CustomerRelatedData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vytautas on 2016-10-25.
 */
public class Customer extends CustomerRelatedData {
    private String username;

    private final List<Address> addresses = new ArrayList<>();
    private final List<OwnedProduct> ownedProducts = new ArrayList<>();

    public Customer() {
    }

    public Customer(long customerId, String username) {
        super(customerId);

        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void addAddress(Address a) {
        addresses.add(a);
    }

    public List<Address> getAddressList() {
        return Collections.unmodifiableList(addresses);
    }

    public void addOwnedProduct(OwnedProduct op) {
        ownedProducts.add(op);
    }

    public List<OwnedProduct> getOwnedProductList() {
        return Collections.unmodifiableList(ownedProducts);
    }
}
