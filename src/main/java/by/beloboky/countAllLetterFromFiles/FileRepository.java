package by.beloboky.countAllLetterFromFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileRepository {
    private final Path path;

    public FileRepository() {
        this.path = Paths.get("/home/andrey/Desktop/Task/multithreading deadlock/files");
    }

    /**
     * @return - List<String> which consists(includes) name of the 50th files.
     */
    public List<String> findFiles() {
        try (Stream<Path> arr = Files.walk(path)) {
            return arr.filter(Files::isRegularFile).map(Path::toString).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
