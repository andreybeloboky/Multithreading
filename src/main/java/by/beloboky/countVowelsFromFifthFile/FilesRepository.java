package by.beloboky.countVowelsFromFifthFile;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class FilesRepository {
    private final File file;

    /**
     * @param str - file path;
     */
    public FilesRepository(String str) {
        this.file = new File(str);
    }

    /**
     * @return The List includes string of text.
     */
    public List<String> readFromFile() {
        try (Stream<String> text = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            return text.toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
