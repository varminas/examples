package lt.arminai.ws.streamingoutput;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    public List<Record> getFiles() {
        List<Record> records = new ArrayList<>();

        List<String> fileNames = getFileNames();

        fileNames.forEach(name -> {
            records.add(new Record(name, name.getBytes()));
        });

        return records;
    }

    private List<String> getFileNames() {
        try {
            return Files.list(Paths.get("/Users/vytautas/Downloads"))
                    .filter(Files::isRegularFile)
                    .map(path -> path.toString())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
