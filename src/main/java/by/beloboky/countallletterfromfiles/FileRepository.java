package by.beloboky.countallletterfromfiles;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileRepository {
    private final String PATH_TO_FILES = "/home/andrey/Desktop/Task/multithreading deadlock/files";
    private final Path path;

    public FileRepository() {
        this.path = Paths.get(PATH_TO_FILES);
    }

    /**
     * @return - List<String> which consists(includes) name of the 50th files.
     */
    public List<Path> findFiles() {
        try (Stream<Path> arr = Files.walk(path)) {
            return arr.filter(Files::isRegularFile).toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
