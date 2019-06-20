package utility;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class Base {
private long executionTime;

    public String getFileName(String path){
        Path p = Paths.get(path);
        String name = p.getFileName().toString();
        String fileName = name.replaceFirst("[.][^.]+$", "");
        return fileName;
    }

}
