package lt.arminai.ws.streamingoutput;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Path("/files")
@Component
public class FileResource {

    private final FileService fileService;
    private final StorageService storageService;

    private static final String fileName = "download.zip";

    public FileResource(FileService fileService, StorageService storageService) {
        this.fileService = fileService;
        this.storageService = storageService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFiles() {
        List<String> fileNames = fileService.getFiles().stream().map(Record::getName).collect(Collectors.toList());
        StreamingOutput stream = storageService.getStreamingOutput(fileNames);

        Response.ResponseBuilder builder = Response.ok(stream);
        builder.header("Content-Disposition", "attachment; filename=\""+ fileName +"\"");

        return builder.build();
    }
}
