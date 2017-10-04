package lt.arminai.ws.streamingoutput;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class StorageService {


    public StreamingOutput getStreamingOutput(List<String> fileNames) {
        return output -> {
            ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(output));

            InputStream in = null;

            try {
                for (String file: fileNames) {
                    in = getFileContent(file);

                    if (in != null) {
                        zip.putNextEntry(new ZipEntry(file));

                        IOUtils.copy(in, zip);
                        zip.closeEntry();
                        in.close();
                    }
                }

                zip.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Flush output
                if( output!=null ) {
                    output.flush();
                    output.close();
                }
                // Close input
                if( in !=null )
                    in.close();
            }
        };
    }

    private InputStream getFileContent(String name) {
        File file = new File(name);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
