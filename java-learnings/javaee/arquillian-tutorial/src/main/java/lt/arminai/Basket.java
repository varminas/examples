package lt.arminai;

import lt.arminai.repository.OrderRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vytautas on 17.6.5.
 */
@SessionScoped
public class Basket implements Serializable {
    private List<String> items;

    @EJB
    private OrderRepository repo;

    public void addItem(String item) {
        items.add(item);
    }

    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int getItemCount() {
        return items.size();
    }

    public void placeOrder() {
        repo.addOrder(items);
        items.clear();
    }

    @PostConstruct
    void initialize() {
        items = new ArrayList<>();
    }
}
