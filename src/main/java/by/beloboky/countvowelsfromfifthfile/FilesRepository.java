package by.beloboky.countvowelsfromfifthfile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FilesRepository {
    private final Path file;

    private final String filePath = "/home/andrey/Desktop/Task/multithreading deadlock/five files";

    public FilesRepository() {
        this.file = Paths.get(this.filePath);
    }

    /**
     * @return The List includes string of text.
     */
    public List<String> readFromFile() {
        try (Stream<Path> text = Files.list(file)) {
            return text.filter(Files::isRegularFile).map(Path::toString).toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
