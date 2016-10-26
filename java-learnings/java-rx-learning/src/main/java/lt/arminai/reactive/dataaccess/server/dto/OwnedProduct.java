package lt.arminai.reactive.dataaccess.server.dto;

import lt.arminai.reactive.dataaccess.customerservice.CustomerRelatedData;

/**
 * Created by vytautas on 2016-10-25.
 */
public class OwnedProduct extends CustomerRelatedData {
    private long productId;
    private String name;

    public OwnedProduct() {
    }

    public OwnedProduct(long customerId, long productId, String name) {
        super(customerId);

        this.productId = productId;
        this.name = name;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }
}
