package lt.arminai.reactive.dataaccess.server;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vytautas on 2016-10-25.
 */
@FunctionalInterface
public interface RowMapper<T> {
    public T call(ResultSet rs) throws SQLException;
}
