package lt.arminai.reactive.dataaccess.productservice;

import lt.arminai.reactive.dataaccess.server.TestDatabaseProcedures;
import lt.arminai.reactive.dataaccess.server.dto.OwnedProduct;
import rx.Observable;

import java.sql.SQLException;

/**
 * Created by vytautas on 2016-10-25.
 */
public class ProductService {
    private final TestDatabaseProcedures producers = new TestDatabaseProcedures();

    public Observable<OwnedProduct> fetchOwnedProduct(long customerId) {
        try {
            return producers.toSelectOwnedProductObservable(customerId);
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
