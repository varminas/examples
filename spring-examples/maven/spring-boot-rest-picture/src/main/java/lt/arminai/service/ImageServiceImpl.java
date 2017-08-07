package lt.arminai.service;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Service
public class ImageServiceImpl implements ImageService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    @Override
    public byte[] getImage() {
        try {
            InputStream inputStream = new ClassPathResource("images/download.jpg").getInputStream();
            return  IOUtils.toByteArray(inputStream);

        } catch (IOException e) {
            LOGGER.error("File Not found {}", e.getMessage());
        }

        return new byte[0];
    }
}
