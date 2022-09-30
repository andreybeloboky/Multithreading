package by.beloboky.countNumbersShFiles;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileRepository {

    private final Path path;
    private final String DIRECTORY = "/home";

    public FileRepository() {
        this.path = Paths.get(DIRECTORY);
    }

    /**
     * @return - List<String> with all home fields (files).
     */
    public List<String> listFiles() {
        try (Stream<Path> arr = Files.walk(this.path)) {
            return arr.filter(Files::isRegularFile).map(Path::toString).toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
