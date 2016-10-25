package lt.arminai.reactive.dataaccess.customerservice;

/**
 * Created by vytautas on 2016-10-25.
 */
public class CustomerRelatedData {
    private long customerId;

    public CustomerRelatedData() {
    }

    public CustomerRelatedData(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerId() {
        return customerId;
    }
}
