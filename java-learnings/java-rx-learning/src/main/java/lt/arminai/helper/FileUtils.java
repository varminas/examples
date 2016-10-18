package lt.arminai.helper;

import java.io.File;
import java.io.IOException;

/**
 * Created by vytautas on 2016-10-18.
 */
public class FileUtils {
    public static void deleteDirectory(File file) throws IOException{
        //to end the recursive loop
        if (!file.exists())
            return;

        //if directory, go inside and call recursively
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                deleteDirectory(f);
            }
        }
        //call delete to delete files and empty directory
        file.delete();
        System.out.println("Deleted file/folder: "+file.getAbsolutePath());
    }
}
