package lt.arminai.repository;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vytautas on 17.6.5.
 */
@Local
public interface OrderRepository {

    void addOrder(List<String> order);

    List<List<String>> getOrders();

    int getOrderCount();
}
