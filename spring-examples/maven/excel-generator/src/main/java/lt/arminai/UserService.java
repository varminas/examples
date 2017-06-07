package lt.arminai;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by vytautas on 17.6.7.
 */
@Service
public class UserService {
    public void generateExcel(List<ExternalUser> users, HttpServletResponse response) {

        try (InputStream is = new ClassPathResource("object_collection_template.xls").getInputStream();) {

            Context context = new Context();
            context.putVar("users", users);
            JxlsHelper.getInstance().processTemplate(is, response.getOutputStream(), context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ExternalUser> getUsers() {
        List<ExternalUser> users = new ArrayList<>();
        users.add(new ExternalUser("user1", 12));
        users.add(new ExternalUser("user2", 55));

        return users;
    }
}
