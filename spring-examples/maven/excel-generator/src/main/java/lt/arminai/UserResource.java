package lt.arminai;

import org.jxls.template.SimpleExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vytautas on 17.6.7.
 */
@RestController("/")
public class UserResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ExternalUser get() {
        LOGGER.debug("get() request received");

        return new ExternalUser("sample name", 30);
    }

    @GetMapping("export")
    public void export(HttpServletResponse response) {
        LOGGER.debug("export() request received");

        response.addHeader("Content-disposition", "attachment; filename=Users.xlsx");
        response.setContentType("application/vnd.ms-excel");

        List<ExternalUser> users = userService.getUsers();
        userService.generateExcel(users, response);

        try {
            response.flushBuffer();
        } catch (IOException e) {
            LOGGER.error("Exception while exporting ", e.getMessage());
        }
    }
}
